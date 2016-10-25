import LoadLocale from "actions/load_locale";

export default Reflux.createStore({
  init() {
    this.messages = {};
    this.listenTo(LoadLocale.completed, "updateMessages");
  },

  getInitialState() {
    return this.messages;
  },

  updateMessages(messages) {
    this.messages = messages;
    this.trigger(messages);
  }
});
