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
    public partial class SetUsergroups : System.Web.UI.Page
    {
        public string docID
        {
            get
            {
                string did = "";
                try
                {
                    if (Page.RouteData.Values["DocID"] != null)
                    {
                        did = Page.RouteData.Values["DocID"].ToString();
                    }
                }
                catch (Exception exc)
                {
                    did = "";
                }
                return did;
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if ((docID.Length > 0 && !docID.Equals("new")) && !IsPostBack)
            {
                Master.SqlIF.ResetParameters();
                Master.SqlIF.AddIntParameter("@UGID", Int32.Parse(docID));
                DataTable dt = Master.SqlIF.getDataTable("getUserGroups");
                groupnameTb.Text = dt.Rows[0]["GroupName"].ToString();
            }
        }

        protected void saveBut_Click(object sender, EventArgs e)
        {
            if (!docID.Equals(""))
            {
                Master.SqlIF.ResetParameters();
                Master.SqlIF.AddVarcharParameter("@UserGroupName", groupnameTb.Text, 32);
                if (!docID.Equals("new"))
                {
                    Master.SqlIF.AddIntParameter("@UGID", Int32.Parse(docID));
                }
                Master.SqlIF.AddIntParameter("@senderID", Master.myuser.userID);
                Master.SqlIF.runSp("setUserGroups");
            }
            Response.Redirect(ResolveUrl("/SetUsergroups"));
        }

        protected string getUGroups()
        {
            StringBuilder sb = new StringBuilder();

            Master.SqlIF.ResetParameters();
            DataTable dt = Master.SqlIF.getDataTable("getUserGroups");
            bool even = true;

            sb.AppendLine("<div class='list-group'>");
            foreach (DataRow dr in dt.Rows)
            {
                if (even)
                {
                    sb.AppendLine("<a href = '/SetUsergroups/" + dr["UGID"].ToString() + "' class='list-group-item list-group-item-action list-group-item-success'>" + dr["GroupName"].ToString() + "</a>");
                    even = false;
                }
                else
                {
                    sb.AppendLine("<a href = '/SetUsergroups/" + dr["UGID"].ToString() + "' class='list-group-item list-group-item-action list-group-item-warning'>" + dr["GroupName"].ToString() + "</a>");
                    even = true;
                }
            }
            sb.AppendLine("</div>");

            return sb.ToString();
        }

        protected void newBut_Click(object sender, EventArgs e)
        {
            Response.Redirect(ResolveUrl("/SetUsergroups/new"));
            return;
        }
    }
}