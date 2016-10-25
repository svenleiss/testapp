import insideApplicationsActions from "actions/insideApplications";

export default Reflux.createStore({
  init() {
    this.listenTo(insideApplicationsActions.getById.serverError, "onServerError");
  },

  onServerError() {
    this.trigger();
  }
});
