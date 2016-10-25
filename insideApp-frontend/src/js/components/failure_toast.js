import mdl from "material-design-lite/material";
import FailureToastStore from "stores/failure_toast_store";

export default React.createClass({
  mixins: [
    Reflux.listenTo(FailureToastStore, "onChange")
  ],

  componentDidMount() {
    mdl.upgradeElement(this.refs.snackbar);
  },

  FOREVER_TIMEOUT: 99999999,

  onChange(snackbarOptions) {
    let message = _.get(snackbarOptions, "message", "");
    let snackbar = this.refs.snackbar.MaterialSnackbar;
    let timeout = snackbarOptions.visible ? this.FOREVER_TIMEOUT : 0;

    let isShowing = $(this.refs.snackbar).hasClass("mdl-snackbar--active");

    if (snackbarOptions.visible) {
      if(!isShowing) {
        snackbar.showSnackbar({
          message: message,
          timeout: timeout
        });
      }
    } else {
      snackbar.cleanup_();
    }
  },

  render() {
    return (
      <div ref="snackbar"
           className="mdl-js-snackbar mdl-snackbar">
        <div className="mdl-snackbar__text"></div>
        <button className="mdl-snackbar__action"
                type="button"/>
      </div>
    );
  }
});
