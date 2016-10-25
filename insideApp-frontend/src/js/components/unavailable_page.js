import Messages from "stores/messages";
import StandardLayout from "components/standard_layout";
import unavailableImage from "!file!assets/images/500.svg";

export default React.createClass({
  displayName: "UnavailablePage",

  mixins: [
    Reflux.connect(Messages, "messages")
  ],

  render() {
    return (
      <StandardLayout>
        <div id="unavailable-page"
             className="flex flr fjc">
          <div className="page-width text-center mvxl">
            <div className="mtxl">
              <img src={unavailableImage}/>
            </div>
            <div className="tf-head-bold mvm"
                 style={{fontSize: 40}}>
              {this.state.messages["unavailablePage.headline"]}
            </div>
            <div className="t3 tf-text-bold mvm">
              {this.state.messages["unavailablePage.message"]}
            </div>
          </div>
        </div>
      </StandardLayout>
    );
  }
});
