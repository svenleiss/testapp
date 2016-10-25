import AppActions from "actions/app";
import Messages from "stores/messages";
import StandardLayout from "components/standard_layout";
import notFoundImage from "!file!assets/images/500.svg";
import ReloadHelper from "insideApp/reload_helper";

export default React.createClass({
  displayName: "ServerErrorPage",

  mixins: [
    Reflux.connect(Messages, "messages")
  ],

  onTryAgainButtonClick() {
    ReloadHelper.reload();
  },

  onDashboardClick() {
    AppActions.changeRoute("/");
  },

  render() {
    return (
      <StandardLayout>
        <div id="not-found-page"
             className="flex flr fjc">
          <div className="page-width text-center mvxl">
            <div className="mtxl">
              <img src={notFoundImage}/>
            </div>
            <div className="tf-head-bold mvm"
                 style={{fontSize: 40}}>
              {this.state.messages["serverErrorPage.headline"]}
            </div>
            <div className="flex flr fjc t3 tf-text-bold mvm">
              <div>{this.state.messages["serverErrorPage.messageFirst"]}</div>
              <div ref="goToDashboard"
                   className="color-cerulean cursor-pointer"
                   onClick={this.onDashboardClick}>
                &nbsp;{this.state.messages["serverErrorPage.dashboard"]}&nbsp;
              </div>
              <div>{this.state.messages["serverErrorPage.messageSecond"]}</div>
            </div>
            <div className="mvm">
              <button ref="tryAgainButton"
                      className="ts-button-primary"
                      onClick={this.onTryAgainButtonClick}>
                {this.state.messages["serverErrorPage.tryAgainButton"]}
              </button>
            </div>
          </div>
        </div>
      </StandardLayout>
    );
  }
});
