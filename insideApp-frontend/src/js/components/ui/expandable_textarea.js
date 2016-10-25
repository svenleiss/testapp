export default React.createClass({
  displayName: "ExpandableTextArea",

  propTypes: {
    id: React.PropTypes.string,
    onChange: React.PropTypes.func,
    placeholder: React.PropTypes.string,
    value: React.PropTypes.string,
    readOnly: React.PropTypes.bool,
    visibility: React.PropTypes.string,
    borderStyle: React.PropTypes.number
  },

  getInitialState() {
    return {
      height: 30,
      value: this.props.value
    };
  },

  componentDidMount() {
    // disable eslint because we want to rerender with current height
    this.setState({ // eslint-disable-line react/no-did-mount-set-state
      height: this.getNeededHeight(this.state.value)
    });
  },

  handleChange(e) {
    var neededHeight = this.getNeededHeight(e.target.value);
    this.setState({
      value: e.target.value,
      height: neededHeight
    });

    if (this.props.onChange) {
      this.props.onChange(e);
    }
  },

  getNeededHeight(newValue) {
    const shadow = this.refs.shadow;

    shadow.value = newValue;

    return shadow.scrollHeight;
  },

  render() {

    let placeHolder = "";

    if (this.props.placeholder) {
      placeHolder =
        (
          <div>
            <label className="mdl-textfield__label t3"
                   htmlFor={this.props.id}>
              {this.props.placeholder}
            </label>
            <label className="mdl-textfield__label t3 force-visibility-for-IE"
                   htmlFor={this.props.id}/>
          </div>
        );
    }

    return (
      <div className="mdl-textfield mdl-js-textfield flex">
        <textarea type="text"
                  id={this.props.id}
                  className="mdl-textfield__input t3"
                  style={{
                    height: this.state.height ? this.state.height : "inherit",
                    visibility: this.props.visibility,
                    borderBottom: this.props.borderStyle
                  }}
                  value={this.state.value}
                  onChange={this.handleChange}
                  readOnly={this.props.readOnly}
                  visibility={this.props.visibility}/>
        <textarea ref="shadow"
                  className="t3"
                  style={{
                    border: 0,
                    margin: 0,
                    padding: 0,
                    resize: "none",
                    overflow: "hidden",
                    visibility: "hidden",
                    position: "absolute",
                    width: "100%"
                  }}
                  rows="1"
                  tabIndex="-1"
                  value={this.state.value}
                  readOnly={true}/>
        {placeHolder}
      </div>
    );
  }
});
