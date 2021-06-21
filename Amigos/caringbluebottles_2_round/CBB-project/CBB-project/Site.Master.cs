using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using CBB_project.Classes;


namespace CBB_project
{
    public partial class SiteMaster : MasterPage
    {
        /// <summary>
        /// SQL eléréséhez szükséges publikus interfész
        /// </summary>
        public SQLIF SqlIF;

        /// <summary>
        /// Felhasználó adatai tárolva
        /// </summary>
        public myUser myuser;

        protected void Page_Init(object sender, EventArgs e)
        {
            authenticate();
            linkToUser.NavigateUrl = ResolveUrl("/SetUser/"+myuser.userID);
            linkToUser.Text = myuser.UserName;
            //myuser = new myUser("Felhasználónév", "Teljes Név", false, 4);
        }

        protected void Page_Load(object sender, EventArgs e)
        {

        }

        /// <summary>
        /// Authentikáció/Authorizáció
        /// </summary>
        private void authenticate()
        {
            HttpCookie usercookie = Request.Cookies["CBB-UNA"];
            HttpCookie sessioncookie = Request.Cookies["CBB-SID"];
            if (usercookie != null && sessioncookie != null)
            {
                if(SqlIF == null || !SqlIF.testConnection())
                    SqlIF = new SQLIF(System.Configuration.ConfigurationManager.ConnectionStrings["CBBDB"].ConnectionString);
                string username = usercookie.Value.ToString().Split('=')[1];
                string sessionid = sessioncookie.Value.ToString().Split('=')[1];
                SqlIF.AddVarcharParameter("@username", username, 32);
                SqlIF.AddCharParameter("@sessionid", sessionid, 36);
                DataTable dt = SqlIF.getDataTable("authorize");
                myuser = new myUser(dt.Rows[0]["username"].ToString(), dt.Rows[0]["FullName"].ToString(), dt.Rows[0]["isAdmin"].ToString().Equals("True"), Int32.Parse(dt.Rows[0]["UID"].ToString()), sessionid);
            }
            else
            {
                Response.Redirect(ResolveUrl("/Login"));
                return;
            }
        }
    }
}