import Header from "components/header";

export default React.createClass({
  displayName: "StandardLayout",

  propTypes: {
    children: React.PropTypes.node.isRequired
  },

  render() {
    return (
      <div>
        <Header />
        <div id="content"
             className="full-height">
          {this.props.children}
        </div>
      </div>
    );
  }
});