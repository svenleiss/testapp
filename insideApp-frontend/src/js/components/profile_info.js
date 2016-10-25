import Messages from "stores/messages";
import AppActions from "actions/app";

const ProfileInfo = React.createClass({
  displayName: "ProfileInfo",

  propTypes: {
    logoutOnly: React.PropTypes.bool,
    username: React.PropTypes.string
  },

  mixins: [Reflux.connect(Messages, "messages")],

  getInitialState() {
    return {
      showMenu: false
    };
  },

  toggleShowMenu() {
    this.setState({showMenu: !this.state.showMenu});
  },

  handleBlur() {
    this.setState({showMenu: false});
  },

  logout() {
    AppActions.logout();
  },

  profile() {
    AppActions.changeRoute("/profile");
  },

  upload() {
    AppActions.changeRoute("/upload");
  },

  help() {
    AppActions.changeRoute("/help");
  },

  renderLinks() {
    var links = [];
    if (! this.props.logoutOnly) {
      links.push(
        <div ref="profile-link"
             key="profile-link"
             className="ts-menu-item"
             onMouseDown={this.profile}>
          <a id="profile-link">
            {this.state.messages["header.profile"]}
          </a>
        </div>);
      links.push(
        <div ref="upload-link"
             key="upload-link"
             className="ts-menu-item"
             onMouseDown={this.upload}>
          <a id="upload-link">
            {this.state.messages["header.upload"]}
          </a>
        </div>);
      links.push(
        <div ref="help-link"
             key="help-link"
             className="ts-menu-item"
             onMouseDown={this.help}>
          <a id="help-link">
            {this.state.messages["header.help"]}
          </a>
        </div>);
    }
    links.push(
      <div ref="logout-link"
           key="logout-link"
           className="ts-menu-item"
           onMouseDown={this.logout}>
        <a id="logout-link">
          {this.state.messages["header.logout"]}
        </a>
      </div>
    );

    return links;
  },

  getOverlayDisplayStyle() {
    return this.state.showMenu ? "block" : "none";
  },

  renderDropdownMenu() {
    if (this.state.showMenu) {
      return (
        <div className="ts-menu-list"
             ref="dropdown"
             style={{width: this.refs["menu"].clientWidth - 29}}>
          {this.renderLinks()}
        </div>
      );
    }
  },

  render() {
    return (
      <div tabIndex="0"
           onBlur={this.handleBlur}>
        <div id="menuOverlay" 
             className="menu_overlay" 
             style={{display: this.getOverlayDisplayStyle()}}></div>
        <div id="profile-info"
             ref="menu"
             className="ts-menu"
             onClick={this.toggleShowMenu}>
          <div>{this.props.username}</div>
          <i className="ts-icon-hamburger mls"
             style={{fontSize: "1.2em", marginRight: 7}}/>
        </div>
        {this.renderDropdownMenu()}
      </div>
    );
  }
});

export default ProfileInfo;
