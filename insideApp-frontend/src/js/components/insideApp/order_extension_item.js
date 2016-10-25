import AppActions from "actions/app";

export default React.createClass({
  displayName: "OrderExtensionItem",

  propTypes: {
    id: React.PropTypes.number.isRequired,
    licensePlate: React.PropTypes.string.isRequired,
    firstName: React.PropTypes.string,
    lastName: React.PropTypes.string,
    notificationIcon: React.PropTypes.bool,
    bold: React.PropTypes.bool
  },

  handleClick() {
    AppActions.changeRoute(`/order-extensions/${this.props.id}`);
  },

  getNotificationClass() {
    return this.props.notificationIcon ?
      "ts-icon-notification" : "ts-icon-notification-header";
  },

  getOrderRowClass() {
    return this.props.bold ?
      "color-bg-white tf-text-bold" : "color-bg-skygrey";
  },

  render() {
    return (
      <div className={this.getOrderRowClass()}
           onClick={this.handleClick}
           style={{cursor: "pointer"}}
           id="order-entry">
        <div className="flr phm pvs t4 bt-black hover-lightblue"
             data-order-extension-row="">
          <div id="notification-icon"
               className={this.getNotificationClass()}
               data-notification-icon="">
          </div>
          <div className="flex phm pvs color-black"
               data-license-plate="">
            {this.props.licensePlate}
          </div>
          <div className="flex phm pvs color-black">
            {"-"}
          </div>
          <div className="flex phm pvs color-black"
               data-name="">
            {(this.props.lastName === "" && this.props.firstName === "") ? "-" :
              (this.props.lastName === "" && this.props.firstName != "" ? this.props.firstName :
                (this.props.lastName != "" && this.props.firstName === "" ? this.props.lastName :
                this.props.lastName + ", " + this.props.firstName))
            }
          </div>
        </div>
      </div>
    );
  }
});
