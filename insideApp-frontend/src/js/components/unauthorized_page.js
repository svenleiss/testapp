import Messages from "stores/messages";
import StandardLayout from "components/standard_layout";
import unauthorizedImage from "!file!assets/images/401.svg";

export default React.createClass({
  displayName: "UnauthorizedPage",

  mixins: [
    Reflux.connect(Messages, "messages")
  ],

  render() {
    return (
      <StandardLayout>
        <div id="not-authorized-page"
             className="flex flr fjc">
          <div className="page-width text-center mvxl">
            <div className="mtxl">
              <img src={unauthorizedImage}/>
            </div>
            <div className="tf-head-bold mvm"
                 style={{fontSize: 40}}>
              {this.state.messages["unauthorizedPage.headline"]}
            </div>
            <div className="t3 tf-text-bold mvm">
              <div>
                {this.state.messages["unauthorizedPage.messageLine1"]}
              </div>
              <div>
                {this.state.messages["unauthorizedPage.messageLine2"]}
              </div>
            </div>
          </div>
        </div>
      </StandardLayout>
    );
  }
});
