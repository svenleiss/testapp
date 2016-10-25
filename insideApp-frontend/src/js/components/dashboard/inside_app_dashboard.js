import Messages from "stores/messages";
import Spinner from "components/spinner";

export default React.createClass({
    displayName: "insideApp",

    mixins: [
        Reflux.connect(Messages, "messages"),
    ],


    render() {
        if (this.state.insideApp == undefined) {
            return (
                <div>
                    <Spinner />
                </div>
            );
        }

        return (
            <div>
                <div className="flr fjc"
                     id="InsideApp">
                    <div className="page-width">
                        hallo
                    </div>
                </div>
            </div>

        );
    }
});
