var path = require("path");
var webpack = require("webpack");

var insideApp_ROOT = path.join(__dirname, "..");
var insideApplications_ROOT = path.join(__dirname, "../src");

var config = {
  plugins: [
    // used by react to silence warnings
    new webpack.DefinePlugin({
      "process.env": {
        "NODE_ENV": JSON.stringify(process.env.NODE_ENV || "development")
      }
    }),

    new webpack.ProvidePlugin({
      $: "jquery",
      _: "lodash",
      React: "react",
      Reflux: "reflux",
      ReactDOM: "react-dom",
      TestUtils: "react-addons-test-utils"
    })
  ],

  entry: path.join(insideApp_ROOT, "src/main.js"),

  output: {
    path: path.join(insideApp_ROOT, "build"),
    publicPath: "/",
    filename: "app.js"
  },

  devtool: (process.env.NODE_ENV === "production" ? "" : "inline-source-map"),

  devServer: {
    host: "0.0.0.0",
    port: 8081,
    proxy: {
      "/api/*": {
        target: "http://localhost:8080"
      },

      "/src": {
        bypass: function() {
          return "/index.html";
        }
      },

      "/404": {
        bypass: function() {
          return "/index.html";
        }
      },

      "/webSocketHandler*": {
        bypass: function() {
          return "/index.html";
        }
      }
    }
  },

  resolve: {
    root: path.resolve(insideApp_ROOT),
    extensions: ["", ".js"],

    alias: {
      "assets": path.join(insideApp_ROOT, "assets"),

      "components": path.join(insideApp_ROOT, "src/js/components"),
      "actions": path.join(insideApp_ROOT, "src/js/actions"),
      "stores": path.join(insideApp_ROOT, "src/js/stores"),
      "models": path.join(insideApp_ROOT, "src/js/models"),

      "insideApp": path.join(insideApp_ROOT, "src/js"),
      "insideApplications": path.join(insideApplications_ROOT, "src/js")
    }
  },

  module: {
    loaders: [
      {
        loaders: ["babel-loader"],
        test: /\.js$/,
        exclude: /node_modules/
      },

      {
        test: /\.scss$/,
        loaders: ["style", "css", "sass", "resolve-url"],
        include: path.join(insideApp_ROOT, "assets")
      },

      {
        test: /\.(eot|ttf|woff|woff2)$/,
        loader: "url-loader",
        include: path.join(insideApp_ROOT, "assets")
      },

      {
        test: /\.(jpe?g|png|gif|svg)$/i,
        loader: "url-loader?name=assets/[name].[ext]",
        include: path.join(insideApp_ROOT, "assets")
      },

      {
        test: /\.(jpe?g|png|gif|svg|xml|ico|json)$/i,
        loader: "file?name=[name].[ext]",
        include: path.join(insideApp_ROOT, "assets/favicons")
      },

      {
        test: require.resolve("material-design-lite/material"),
        loader: "exports?componentHandler"
      }
    ]
  },

  sassLoader: {
    includePaths: [
      path.join(insideApp_ROOT, "node_modules/material-design-lite/src")
    ]
  }
};

if(process.env.NODE_ENV === "production") {
  var uglifyPlugin = new webpack.optimize.UglifyJsPlugin({
    compress: {
      warnings: false
    }
  });

  config.plugins.push(uglifyPlugin);
}

module.exports = config;