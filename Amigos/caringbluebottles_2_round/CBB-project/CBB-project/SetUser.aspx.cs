using CBB_project.Classes;
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
    public partial class SetUser : System.Web.UI.Page
    {

        public int docID
        {
            get
            {
                int did = -1;
                try
                {
                    if (Page.RouteData.Values["DocID"] != null)
                    {
                        did = Int32.Parse(Page.RouteData.Values["DocID"].ToString());
                    }
                }
                catch (Exception exc)
                {
                    did = -1;
                }
                return did;
            }
        }

        public DataRow user;

        protected void Page_Init(object sender, EventArgs e)
        {
            Master.SqlIF.ResetParameters();
            if(docID >= 0)
                Master.SqlIF.AddIntParameter("@UID", docID);
            else if (!Master.myuser.isAdmin)
            {
                Response.Redirect(ResolveUrl("/SetUser/" + Master.myuser.userID));
                return;
            }
            else
            {
                return;
            }
            Master.SqlIF.AddCharParameter("@sessionid", Master.myuser.sessionID, 36);
            Master.SqlIF.AddIntParameter("@senderID", Master.myuser.userID);
            DataTable dt = Master.SqlIF.getDataTable("getUsers");
            if(dt != null && dt.Rows != null && dt.Rows.Count > 0)
            {
                user = dt.Rows[0];
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if(docID >= 0 && user != null && !IsPostBack)
            {
                usernameTb.Text = user["UserName"].ToString();
                fullnameTb.Text = user["FullName"].ToString();
                descTb.Text = user["Description"].ToString();

                bool same = (Master.myuser.userID == docID);

                usernameTb.Enabled = same;
                fullnameTb.Enabled = same;
                descTb.Enabled = same;
                birthdateCal.Enabled = same;
                pswTb.Visible = same;
                repswTb.Visible = same;

                string[] data = user["BirthDate"].ToString().Split(' ');
                if (data.Length >= 3)
                {
                    string y = data[0].Split('.')[0];
                    string m = data[1].Split('.')[0];
                    string d = data[2].Split('.')[0];
                    birthdateCal.Text = new DateTime(Int32.Parse(y), Int32.Parse(m), Int32.Parse(d), 0, 0, 1).ToString();
                }
                else
                    birthdateCal.Text = DateTime.Now.ToString();
            }
        }

        protected void saveBut_Click(object sender, EventArgs e)
        {
            Master.SqlIF.ResetParameters();
            if (docID >= 0)
                Master.SqlIF.AddIntParameter("@UID", docID);
            Master.SqlIF.AddCharParameter("@sessionid", Master.myuser.sessionID, 36);
            Master.SqlIF.AddIntParameter("@senderID", Master.myuser.userID);
            Master.SqlIF.AddVarcharParameter("@username", usernameTb.Text, 32);
            Master.SqlIF.AddVarcharParameter("@fullname", fullnameTb.Text, 64);
            Master.SqlIF.AddVarcharParameter("@description", descTb.Text, 256);
            //birthday felküldése
            if(pswTb.Text.Length > 0)
            {
                if(pswTb.Text.Equals(repswTb.Text) && pswTb.Text.Length >= 5 && !pswTb.Text.Equals(usernameTb.Text))
                {
                    Master.SqlIF.AddCharParameter("@psw", Hasher.createHash(pswTb.Text), 256);
                }
            }
            Master.SqlIF.runSp("setUsers");
        }

        protected void delBut_Click(object sender, EventArgs e)
        {
            Master.SqlIF.ResetParameters();
            Master.SqlIF.AddIntParameter("@UID", docID);
            Master.SqlIF.AddCharParameter("@sessionid", Master.myuser.sessionID, 36);
            Master.SqlIF.AddIntParameter("@senderID", Master.myuser.userID);
            Master.SqlIF.AddIntParameter("@delete", 1);
            Master.SqlIF.runSp("setUsers");
        }
    
        protected string getGroups()
        {
            StringBuilder sb = new StringBuilder();

            sb.AppendLine("");

            return sb.ToString();
        }
    }
}