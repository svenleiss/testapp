import Messages from "stores/messages";
import StandardLayout from "components/standard_layout";
import AppContext from "stores/app_context";
import insideApplicationsTab from "components/insideApp/insideApplications_tab";

export default React.createClass({
    displayName: "DashboardPage",

    propTypes: {
        navigateTo: React.PropTypes.string
    },

    mixins: [
        Reflux.connect(Messages, "messages"),
        Reflux.listenTo(AppContext, "updateAppContext", "updateAppContext"),
    ],

    getInitialState() {
        return {
            activeTab: "insideApplications-tab",
            navigateTo: ""
        };
    },

    componentWillMount() {
        if ("" != this.props.navigateTo) {
            this.setState({activeTab: this.props.navigateTo});
        }
    },

    updateAppContext(data) {
        this.setState({insideApplicationsEnabled: data.insideApplicationsEnabled});
    },

    changeTab(event) {
        this.setState({activeTab: event.target.id});
    },

    getinsideApplicationsTab() {
        if (this.state.insideApplicationsEnabled) {
            return (
                <div ref="insideApplicationsTab"
                     id="order-extensions-tab"
                     className={"ts-tab" + this.activeTabStyle("insideApplications-Tab")}
                     onClick={this.changeTab}>
                    {this.state.messages["dashboard.insideApplicationsTab"]}
                </div>
            );
        }
    },

    login() {
    },
    createUser() {
    },

    activeTabStyle(tab) {
        return this.state.activeTab == tab ? " ts-tab-active" : "";
    },

    getActiveTabContent() {
        return (
            <insideApplicationsTab ref="insideApplicationsTabContent"/>

        );
    },

    render() {
        return (
            <StandardLayout>
                <form>
                    <div className="color-bg-white flex flr fjc">
                        <div className="page-width flr mtm">
                            <input type="text"
                                   valueLink={this.linkState('Benutzername')}
                                   placeholder="Username"/>
                            <input type="password"
                                   valueLink={this.linkState()}
                                   placeholder="Password"/>
                        </div>
                    </div>
                    <button onClick={this.login}>Anmelden</button>
                    <button onClick={this.createUser()}>Registrieren</button>
                </form>
            </StandardLayout>
        );
    }
});
