import insideApplicationsActions from "actions/insideApplications";

export default Reflux.createStore({
  init() {
    this.listenTo(insideApplicationsActions.getById.notFound, "onNotFound");
  },

  onNotFound() {
    this.trigger();
  }
});
