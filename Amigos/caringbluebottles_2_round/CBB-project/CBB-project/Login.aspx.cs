using System;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using CBB_project.Classes;

namespace CBB_project
{
    public partial class Login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                name.BackColor = Color.White;
                psw.BackColor = Color.White;

                Response.Cookies["CBB-SID"].Expires = DateTime.Now.AddDays(-1);
            }
        }

        protected void loginClick(object sender, EventArgs e)
        {
            if(name.Text.Length < 1)
            {
                name.BackColor = Color.Red;
                return;
            }
            if (psw.Text.Length < 1)
            {
                psw.BackColor = Color.Red;
                return;
            }

            string username = name.Text;
            string hsd = Hasher.createHash(psw.Text);

            if(hsd == null || hsd.Equals(""))
            {
                //nem jó
                return;
            }

            SQLIF sqlif = new SQLIF(System.Configuration.ConfigurationManager.ConnectionStrings["CBBDB"].ConnectionString);
            DataTable dt = null;
            try
            {
                sqlif.AddVarcharParameter("@username", username, 32);
                sqlif.AddCharParameter("@psw", hsd, 256);
                dt = sqlif.getDataTable("authorize");
            }
            catch(Exception exc)
            {
                Response.Redirect(ResolveUrl("/Login"));
                return;
            }
            
            if(dt != null && dt.Rows != null && dt.Rows.Count > 0)
            {
                HttpCookie userInfo = new HttpCookie("CBB-SID");
                userInfo["data"] = dt.Rows[0]["SesID"].ToString();
                string[] data = dt.Rows[0]["Until"].ToString().Split(' ');
                string y = data[0].Split('.')[0];
                string m = data[1].Split('.')[0];
                string d = data[2].Split('.')[0];
                string[] hms = data[3].Split(':');
                userInfo.Expires = new DateTime(Int32.Parse(y), Int32.Parse(m), Int32.Parse(d), Int32.Parse(hms[0]), Int32.Parse(hms[1]), Int32.Parse(hms[2]));
                DateTime exp = userInfo.Expires;
                Response.Cookies.Add(userInfo);

                userInfo = new HttpCookie("CBB-UNA");
                userInfo["data"] = dt.Rows[0]["UserName"].ToString();
                userInfo.Expires = exp.AddDays(1);
                Response.Cookies.Add(userInfo);

                Response.Redirect(ResolveUrl("~/MainSite"));
                return;
            }
    }

        protected void forgottenClick(object sender, EventArgs e)
        {
            if (name.Text.Length < 1)
            {
                name.BackColor = Color.Red;
                return;
            }

        }
    }
}