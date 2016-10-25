import AppActions from "actions/app";

export default Reflux.createStore({
  init() {
    this.appContext = {
      contract: this.getContract()
    };

    this.listenTo(AppActions.bootstrap, this.onBootstrap);
  },

  getInitialState() {
    return this.appContext;
  },

  getContract() {
    let match = document.cookie.match(/selected-contract=([^;]*)/);
    var contract = "";
    if(match !== null && match.length > 1) {
      contract = match[1];
    }
    return contract;
  },

  onBootstrap(data) {
    this.appContext = _.merge(this.appContext, data);
    this.trigger(this.appContext);
  }
});
