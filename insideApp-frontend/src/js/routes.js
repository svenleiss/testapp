import {routeMatcher} from "route-matcher";
import InsideAppDashboard from "components/dashboard/inside_app_dashboard";
import NotFoundPage from "components/not_found_page";
import ServerErrorPage from "components/server_error_page";


const routes = [
  {
    path: "/",
    component: InsideAppDashboard
  },
  {
    path: "/404",
    component: NotFoundPage
  },
  {
    path: "/500",
    component: ServerErrorPage
  },
  {
    path: "/inside_app_dashboard",
    component: InsideAppDashboard
  },
  {
    path: "/dashboardPage/:navigateTo",
    component: InsideAppDashboard,
    key: "navigateTo"
  },
];

export default {
  page(path) {
    for (var index in routes) {
      var route = routes[index];
      var match = routeMatcher(route.path).parse(path);

      if (match) {
        var Component = route.component;
        if (route.key) {
          match["key"] = match[route.key];
        }

        return <Component {...match} />;
      }
    }

    return <NotFoundPage />;
  }
};
