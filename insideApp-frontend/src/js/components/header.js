import AppActions from "actions/app";
import Messages from "stores/messages";

let icon = require("!file!assets/images/ts-icon-placeholder.svg");

export default React.createClass({
  mixins: [
    Reflux.connect(Messages, "messages"),
  ],

  home() {
    AppActions.changeRoute("/");
  },

  render() {

    return (
      <div id="header"
           className="flr fjc bb-calmgrey">
        <div className="flr fac page-width color-grey t5">
          <a ref="icon"
             className="cursor-pointer"
             style={{marginTop: 7, marginLeft: 7}}
             onClick={this.home}>
            <img src={icon}/>
          </a>
          <div className="mrm flex">
            <a ref="title"
               className="t6 tf-head-bold color-charcoal-grey no-decoration mlxs cursor-pointer"
               onClick={this.home}
               data-nav-dialogue-receptions="">
              {this.state.messages["common.appName"]}
            </a>
          </div>
        </div>
      </div>
    );
  }
});
