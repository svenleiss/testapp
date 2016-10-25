import AppActions from "actions/app";

export default Reflux.createStore({
  init() {
    this.listenTo(AppActions.changeRoute, this.onChangeRoute);
  },

  getInitialState() {
    return window.location.pathname;
  },

  onChangeRoute(route) {
    window.history.pushState(null, null, route);
    this.trigger(route);
  },

  onBack(route) {
    this.trigger(route);
  }
});
