import Axios from "axios";

const GreetingActions = Reflux.createActions({
  "create": {asyncResult: true}
});

GreetingActions.create.listen((greeting) => {

  Axios.patch("/api/greetings", greeting)
    .then(function (response) {
      GreetingActions.create.(response.data);
    }).catch(function (response) {
      GreetingActions.create.failed(response);
    });

});

export default GreetingActions;
