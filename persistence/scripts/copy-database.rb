#!/usr/bin/env ruby
require "json"
require "socket"
require "pty"
require "tempfile"

def run(cmd)
  puts "running cmd: #{cmd}"
  output = `#{cmd}`

  raise $?.to_s unless $?.success?

  output
end

def ssh_to(space_name)
  run("cf t -s #{space_name}")

  # discover app name
  app_name = run("cf apps | grep started | cut -f 1 -d ' '").strip

  credentials = get_credentials(app_name)

  server = TCPServer.new("127.0.0.1", 0)
  free_ephemeral_port = server.addr[1]
  server.close

  cmd = <<-SH.gsub("\n", " ")
cf ssh #{app_name}
-L #{free_ephemeral_port}:#{credentials["hostname"]}:#{credentials["port"]}
  SH

  PTY.spawn(cmd) do |ssh_stdout, _ssh_stdin, pid|
    puts pid
    puts "waiting for ssh"
    puts ssh_stdout.getc
    puts "read shell prompt"

    yield(credentials, free_ephemeral_port)
  end
end

def mysql_dump_cmd(credentials, free_ephemeral_port)
  p <<-SH.gsub("\n", " ")
  mysqldump --port=#{free_ephemeral_port}
  --host=127.0.0.1
  --user=#{credentials["username"]}
  --password=#{credentials["password"]}
  #{credentials["name"]}
  SH
end

def mysql_db_cmd(credentials, port)
  p <<-SH.gsub("\n", " ")
  mysql --port=#{port}
  --host=127.0.0.1
  --user=#{credentials["username"]}
  --password=#{credentials["password"]}
  #{credentials["name"]}
  SH
end

def mysql_cmd(credentials, port)
  p <<-SH.gsub("\n", " ")
  mysql --port=#{port}
  --host=127.0.0.1
  --user=#{credentials["username"]}
  --password=#{credentials["password"]}
  SH
end

def get_credentials(app_name)
  app_guid = run("cf app #{app_name} -guid").strip
  env_json = run("cf curl /v2/apps/#{app_guid}/env")
  parsed = JSON.parse(env_json)

  insideApp_db = nil
  parsed["system_env_json"]["VCAP_SERVICES"].select do |_key, list|
    list.select do |n|
      if n["name"] == "insideApp_db"
        insideApp_db = n;
      end
    end
  end

  if insideApp_db != nil
    insideApp_db["credentials"]
  else
    raise "did not find credentials for insideApp_db bound to #{app_name}"
  end
end


if ARGV.length < 2
  puts "usage: copy-database.rb <source space> <destination space>"
  exit 1
end

src_env = ARGV[0]
dst_env = ARGV[1]

if dst_env == "sandbox" || dst_env == "production"
  puts "you are attempting to overwrite production"
  puts "I cant do that dave"
  exit 1
end

Tempfile.open("#{src_env}.sql") do |f|

  ssh_to(src_env) do |credentials, port|
    run("#{mysql_dump_cmd(credentials, port)} > #{f.path}")
  end

  ssh_to(dst_env) do |credentials, port|
    run("#{mysql_cmd(credentials, port)} -e \"drop database if exists #{credentials["name"]};\"")
    run("#{mysql_cmd(credentials, port)} -e \"create database #{credentials["name"]};\"")
    run("#{mysql_db_cmd(credentials, port)} < #{f.path}")
  end
end