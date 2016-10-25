import mdl from "material-design-lite/material";
import Messages from "insideApp/stores/messages";
import insideApplicationsActions from "actions/insideApplications";
import insideApplicationsStore from "stores/order_extension";
import OrderExtensionItem from "components/insideApp/order_extension_item";
import Spinner from "components/spinner";
import AppContext from "stores/app_context";

var SockJS = require("sockjs-client");
var sockConnection = SockJS("/webSocketHandler");

export default React.createClass({
  displayName: "insideApplicationsTab",

  mixins: [
    Reflux.connect(Messages, "messages"),
    Reflux.connect(insideApplicationsStore, "insideApplications"),
    Reflux.connect(AppContext, "appContext")
  ],

  componentDidMount() {
    mdl.upgradeDom();
    insideApplicationsActions.get(this.getContract());
    this.updateinsideApplications(this.state.appContext);
  },


  componentWillUnmount() {
    sockConnection.onclose = this.onWebSocketConnectionClose();
  },

  onMessageReceive: function (that) {
    return function (e) {
      var jsonReturned = JSON.parse(e.data);
      that.setState({insideApplications: jsonReturned});
    };
  },

  updateinsideApplications(data) {
    var that = this;
    sockConnection.onopen = this.onOpenSocketConnection(data);
    sockConnection.onmessage = this.onMessageReceive(that);

  },

  onWebSocketConnectionClose: function () {
    return function () {
      // Need to investigate if required
    };
  },

  getSocketListenerData: function (data) {
    var send = {
      contract: data.contract
    };
    return send;
  },

  sendMessage: function (send) {
    sockConnection.send(JSON.stringify(send));
  },

  onOpenSocketConnection: function (data) {
    var that = this;
    return function () {
      var send = that.getSocketListenerData(data);
      that.sendMessage(send);
    };
  },

  getContract() {
    let match = document.cookie.match(/selected-contract=([^;]*)/);
    var contract = "";
    if (match !== null && match.length > 1) {
      contract = match[1];
    }
    return contract;
  },


  getContent() {
    if (this.state.insideApplications.length > 0) {
      return this.getOrderExtensionItems();
    } else {
      return this.getEmptyContent();
    }
  },

  getEmptyContent() {
    return (
      <div className="flr fjc pvl">
        <div className="flc fac fjc color-bg-white mtxl mhs"
             style={{height: 385, width: 460}}>
          <div className="t2 tf-head-bold color-charcoal-grey">
            {this.state.messages["insideApplications.emptyMessage"]}
          </div>
        </div>
      </div>
    );
  },

  isActionRequired(orderExtension) {
    return orderExtension.status == "NEW"
      || orderExtension.status == "VIEWED_BY_SERVICE_ADVISOR"
      || orderExtension.status == "VIEWED_BY_CUSTOMER"
      || orderExtension.status == "ANSWERED_BY_CUSTOMER";
  },

  isOrderUnread(orderExtension) {
    return orderExtension.status == "NEW"
      || orderExtension.status == "VIEWED_BY_CUSTOMER"
      || orderExtension.status == "ANSWERED_BY_CUSTOMER";
  },

  getOrderExtensionItems() {
    var id = 0;
    var insideApplications = _.map(this.state.insideApplications, (orderExtension) => {
      id++;
      return (
        <OrderExtensionItem ref={"item" + id}
                            key={"item" + id}
                            id={orderExtension.id}
                            licensePlate={orderExtension.licensePlate}
                            firstName={orderExtension.customer.firstName}
                            lastName={orderExtension.customer.lastName}
                            notificationIcon={this.isActionRequired(orderExtension)}
                            bold={this.isOrderUnread(orderExtension)}
        />
      );
    });

    return (
      <div className="mbxl"
           data-order-extensions=""
           id="order-extensions">
        <div className="page-width color-bg-white mdl-shadow--2dp mvs">
          <div className="flr phm pvxs t4 tf-head-bold color-charcoal-grey mdl-card--border bb2-calmgrey">
            <div className="color-warmgrey ts-icon-notification-header">
            </div>
            <div className="flex phm color-warmgrey">
              {this.state.messages["dashboard.label.licensePlate"]}
            </div>
            <div className="flex phm color-warmgrey">
              {this.state.messages["dashboard.label.dmsOrderNumber"]}
            </div>
            <div className="flex phm color-warmgrey">
              {this.state.messages["dashboard.label.customerName"]}
            </div>
          </div>
          {insideApplications}
        </div>
      </div>
    );
  },

  render() {
    if (this.state.insideApplications === undefined) {
      return <Spinner/>;
    }

    return (
      <div className="flr fjc pvl"
           data-order-extensions-tab="">
        <div className="pbl page-width">
          {this.getContent()}
        </div>
      </div>
    );
  }
});
