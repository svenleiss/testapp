import Messages from "stores/messages";
import {ModalContainer, ModalDialog} from "react-modal-dialog";

export default React.createClass({


  propTypes: {
    closeModalDialog: React.PropTypes.func,
    isError: React.PropTypes.bool
  },
  mixins: [
    Reflux.connect(Messages, "messages")
  ],

  getConfirmationToastDiv(){
    return (
      <div id="confirmationToastDiv"
           className="flr fjc">
        <div className="ts-section-confirmation-toast">
          <div className="ts-mdl-button checkmark-style ts-icon-checkmark"></div>
          <div className=" order-confrimation-toast-message">
            {this.state.messages["orderConfirmationPage.confirmation.toast"]}
          </div>
        </div>
      </div>);

  },

  getErrorMessageDiv(){

    return (
      <div id="errorToastDiv"
           className="flr fjc">
        <div>
          <div className="ts-mdl-button attentionmark-style ts-icon-error-notification"></div>
          <div className="order-confrimation-error1-toast-message">
            {this.state.messages["orderConfirmationPage.error1.toast"]}
          </div>
          <div className=" order-confrimation-error2-toast-message">
            {this.state.messages["orderConfirmationPage.error2.toast"]}
          </div>
          <div className=" order-confrimation-error3-toast-message">
            {this.state.messages["orderConfirmationPage.error3.toast"]}
          </div>
        </div>
      </div>);

  },
  getMessageDiv(){
    if (this.props.isError) {
      return this.getErrorMessageDiv();
    } else {
      return this.getConfirmationToastDiv();
    }
  },

  getModalContainerClass(){
    return this.props.isError ?
      "ts-section-confirmation-error-toast" :
      "ts-section-confirmation-toast-dialog";
  },


  render() {
    return (
      <div>
        <ModalContainer onClose={this.props.closeModalDialog}>
          <ModalDialog className={this.getModalContainerClass()}
                       onClose={this.props.closeModalDialog}>
            {this.getMessageDiv()}
          </ModalDialog>
        </ModalContainer>
      </div>
    );
  }
});
