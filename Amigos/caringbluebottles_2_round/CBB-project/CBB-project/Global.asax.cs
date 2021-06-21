using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Optimization;
using System.Web.Routing;
using System.Web.Security;
using System.Web.SessionState;

namespace CBB_project
{
    public class Global : HttpApplication
    {
        void Application_Start(object sender, EventArgs e)
        {
            RouteTable.Routes.MapPageRoute("",
                "FAQ/{DocID}",
                "~/FAQ.aspx");

            RouteTable.Routes.MapPageRoute("",
                "Messages/{DocID}",
                "~/Messages.aspx");

            RouteTable.Routes.MapPageRoute("",
                "SetUser/{DocID}",
                "~/SetUser.aspx");

            RouteTable.Routes.MapPageRoute("",
                "Events/{DocID}",
                "~/Events.aspx");

            RouteTable.Routes.MapPageRoute("",
                "SetUsergroups/{DocID}",
                "~/SetUsergroups.aspx");

            // Code that runs on application startup
            RouteConfig.RegisterRoutes(RouteTable.Routes);
            BundleConfig.RegisterBundles(BundleTable.Bundles);
        }
    }
}