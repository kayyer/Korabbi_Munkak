using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CBB_project
{
    public partial class ListUsers : System.Web.UI.Page
    {

        protected void Page_Load(object sender, EventArgs e)
        {
            
        }

        protected void getUsers()
        {
            StringBuilder sb = new StringBuilder();
            if (usernameTb.Text.Length > 0)
            {
                Master.SqlIF.ResetParameters();
                Master.SqlIF.AddVarcharParameter("@username", usernameTb.Text, 32);
                Master.SqlIF.AddCharParameter("@sessionid", Master.myuser.sessionID, 36);
                Master.SqlIF.AddIntParameter("@senderID", Master.myuser.userID);
                DataTable dt = Master.SqlIF.getDataTable("getUsers");
                bool even = true;

                sb.AppendLine("<div class='list-group'>");
                foreach (DataRow dr in dt.Rows)
                {
                    if (even)
                    {
                        sb.AppendLine("<a href = '/SetUser/" + dr["UID"].ToString() + "' class='list-group-item list-group-item-action list-group-item-success'>" + dr["UserName"].ToString() + "</a>");
                        even = false;
                    }
                    else
                    {
                        sb.AppendLine("<a href = '/SetUser/" + dr["UID"].ToString() + "' class='list-group-item list-group-item-action list-group-item-warning'>" + dr["UserName"].ToString() + "</a>");
                        even = true;
                    }
                }
                sb.AppendLine("</div>");

            }

            Literal a = new Literal();
            a.Text = sb.ToString();
            dataPanel.Controls.Add(a);
        }

        protected void searchBut_Click(object sender, EventArgs e)
        {
            getUsers();
        }
    }
}