import AppActions from "actions/app";
import Messages from "stores/messages";
import StandardLayout from "components/standard_layout";
import notFoundImage from "!file!assets/images/404.svg";

export default React.createClass({
  displayName: "NotFoundPage",

  mixins: [
    Reflux.connect(Messages, "messages")
  ],

  onHomeButtonClick() {
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
              {this.state.messages["notFoundPage.headline"]}
            </div>
            <div className="t3 tf-text-bold mvm">
              {this.state.messages["notFoundPage.message"]}
            </div>
            <div className="mvm">
              <button ref="homeButton"
                      className="ts-button-primary"
                      onClick={this.onHomeButtonClick}>
                {this.state.messages["notFoundPage.returnToDashboardButton"]}
              </button>
            </div>
          </div>
        </div>
      </StandardLayout>
    );
  }
});
