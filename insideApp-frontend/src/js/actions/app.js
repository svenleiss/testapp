import LocationHelper from "insideApp/location_helper";

const AppActions = Reflux.createActions([
  "bootstrap",
  "changeRoute",
  "logout"
]);

AppActions.logout.listen(() => {
  LocationHelper.setLocation("/logout");
});

export default AppActions;

