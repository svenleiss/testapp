require("file?name=index.html!./index.html");
require("!style!css!sass!./css/app.scss");

require.context("assets/fonts", false, /\.(eot|ttf|woff|woff2)$/i);
require.context("assets/favicons", false, /\.(jpe?g|png|gif|svg|xml|ico|json)$/i);

require("es6-promise").polyfill();


import LoadLocale from "./js/actions/load_locale";
import AppContainer from "./js/components/app_container";

function run() {
  LoadLocale(navigator.language);
  // $.ajax({
  //   url: "/api/bootstrap",
  //   method: "GET",
  //   dataType: "json"
  // }).done(function (data) {
  //   AppActions.bootstrap(data);
  //
  //   if (data.user.length > 0) {
      ReactDOM.render(<AppContainer />, document.getElementById("app"));
 //   } else {
 //      ReactDOM.render(<UnauthorizedPage />, document.getElementById("app"));
 //    }
 //  }).fail(function () {
 //    ReactDOM.render(<UnavailablePage />, document.getElementById("app"));
 //  });
}

if (document.readyState !== "loading") {
  run();
} else {
  document.addEventListener("DOMContentLoaded", run);
}
