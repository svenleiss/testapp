import Routes from "insideApp/routes";
import AppActions from "actions/app";
import AppStore from "stores/app";
import NotFound from "stores/not_found";
import ServerError from "stores/server_error";
import NotFoundPage from "components/not_found_page";
import ServerErrorPage from "components/server_error_page";

export default React.createClass({
  displayName: "AppContainer",

  mixins: [
    Reflux.listenTo(AppStore, "onRouteChange"),
    Reflux.listenTo(NotFound, "onNotFound"),
    Reflux.listenTo(ServerError, "onServerError")
  ],

  getInitialState() {
    return {
      page: Routes.page(window.location.pathname)
    };
  },

  componentDidMount() {
    window.addEventListener("popstate", this.onPopState);
  },

  componentWillUnmount() {
    window.removeEventListener("popstate", this.onPopState);
  },

  onRouteChange(route) {
    this.setState({page: Routes.page(route)});
  },

  onNotFound() {
    this.setState({page: (<NotFoundPage/>)});
  },

  onServerError() {
    this.setState({page: (<ServerErrorPage/>)});  
  },
  
  onPopState(e) {
    AppActions.back(e.target.location.pathname);
  },

  render() {
    return this.state.page;
  }
});