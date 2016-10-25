import Reflux from "reflux";
import Axios from "axios";

const insideApplicationsActions = Reflux.createActions({
  "create": {asyncResult: true}
});

insideApplicationsActions.create.listen((userName, message) => {
  var path = "/api/insideApplications";
  Axios.get(path)
    .then(function (response) {
      insideApplicationsActions.get.completed(response.data);
    })
    .catch(function (response) {
      insideApplicationsActions.get.failed(response);
    });
});



insideApplicationsActions.getById.listen((id,userRole) => {

  var config = {
    headers: {"X-VW-USER-ROLE": userRole}
  };

  Axios.get(`/api/insideApplications/${id}`,config)
    .then(function (response) {
      insideApplicationsActions.getById.completed(response.data);
    }).catch(function (response) {
      if (response.status > 399 && response.status < 500) {
        insideApplicationsActions.getById.notFound();
      }
      if (response.status === 500) {
        insideApplicationsActions.getById.serverError();
      }
    });
});

insideApplicationsActions.sendMail.listen((custMailId, id, serviceAdvisor) => {
  var path = "/api/insideApplications/sendEmail?customerEmail=" + custMailId +
    "&id=" + id + "&serviceAdvisor=" + serviceAdvisor;

  Axios.get(path)
    .then(function (response) {
      insideApplicationsActions.sendMail.completed(response.data);
    })
    .catch(function (response) {
      insideApplicationsActions.sendMail.failed(response);
    });
});

insideApplicationsActions.updateOrderItemDetails.listen((orderItemId, price,
                                                      description, urgency,itemName) => {
  Axios.patch("/api/insideApplications/updateOrderItemDetails",{ id:orderItemId, price:price ,
    description: description, urgency:urgency, itemName:itemName}
      )
      .then(function (response) {
        insideApplicationsActions.updateOrderItemDetails.completed(response.data);
      })
      .catch(function (response) {
        insideApplicationsActions.updateOrderItemDetails.failed(response);
      });
});

insideApplicationsActions.deleteOrderItem.listen((orderItemId) => {
  Axios.get("/api/insideApplications/deleteOrderItem?orderItemId=" + orderItemId)
    .then(function (response) {
      insideApplicationsActions.deleteOrderItem.completed(response.data);
    })
    .catch(function () {
      insideApplicationsActions.deleteOrderItem.failed();
    });
});

insideApplicationsActions.updateOrderItem.listen((orderItemId, customerAction) => {
  Axios.patch("/api/insideApplications/updateCustomerAction", {id: orderItemId, actionByCustomer: customerAction})
    .then(function (response) {
      insideApplicationsActions.updateOrderItem.completed(response.data);
    }).catch(function () {
      insideApplicationsActions.updateOrderItem.failed();
    });
});


insideApplicationsActions.confirmOrder.listen((order) => {
  Axios.patch("/api/insideApplications/confirmOrder", {id: order.id, orderItems: order.orderItems})
    .then(function (response) {
      insideApplicationsActions.confirmOrder.completed(response.data);
    }).catch(function (response) {
      insideApplicationsActions.confirmOrder.failed(response);
    });
});

insideApplicationsActions.createServiceAdvisorOrderItem.listen((contract, licensePlate) => {
  Axios.post("/api/insideApplications/createServiceAdvisorOrderItem", {contract: contract, licensePlate: licensePlate})
    .then(function (response) {
      insideApplicationsActions.createServiceAdvisorOrderItem.completed(response.data);
    }).catch(function () {
      insideApplicationsActions.createServiceAdvisorOrderItem.failed();
    });
});

insideApplicationsActions.updateCustomerMessage.listen((order) => {
  Axios.patch("/api/insideApplications/updateCustomerMessage", {id: order.id, customerMessage: order.customerMessage})
    .then(function (response) {
      insideApplicationsActions.updateCustomerMessage.completed(response.data);
    }).catch(function () {
      insideApplicationsActions.updateCustomerMessage.failed();
    });
});




export default insideApplicationsActions;
