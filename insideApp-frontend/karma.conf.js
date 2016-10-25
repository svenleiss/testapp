var _ = require("lodash");
var path = require("path");
var webpackConfig = require("./webpack/webpack.config.js");

module.exports = function (configSetter) {
  var baseConfig = {
    basePath: "",
    port: 8888,
    frameworks: ["jasmine"],
    reporters: ["kjhtml"],
    browsers: ["Chrome"],
    autoWatch: true,
    singleRun: false,
    concurrency: Infinity,
    colors: true,
    logLevel: configSetter.LOG_INFO,

    files: [
      "spec/main.js"
    ],

    customLaunchers: {
      Chrome_with_debugger_open: {
        base: "Chrome",
        flags: ["--auto-open-devtools-for-tabs"]
      }
    },

    exclude: [],

    preprocessors: {
      "spec/**/*.js": ["webpack", "sourcemap"]
    }
  };

  webpackConfig.resolve.alias["helpers"] = path.join(__dirname, "/spec/helpers");

  var finalConfig = _.merge(baseConfig, {webpack: webpackConfig});
  configSetter.set(finalConfig);
};
