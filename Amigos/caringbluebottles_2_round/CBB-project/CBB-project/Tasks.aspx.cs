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
    public partial class Games : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected string getTasks()
        {
            Master.SqlIF.ResetParameters();
            Master.SqlIF.AddIntParameter("@all", 1);
            DataTable all = Master.SqlIF.getDataTable("getTasks");

            Master.SqlIF.ResetParameters();
            Master.SqlIF.AddIntParameter("@UID", Master.myuser.userID);
            DataTable dt = Master.SqlIF.getDataTable("getTasks");

            StringBuilder sb = new StringBuilder();

            foreach (DataRow dr in all.Rows)
            {
                string myclass = "inactive";
                foreach(DataRow r in dt.Rows)
                {
                    if (r["Type"].ToString().Equals(dr["Type"].ToString()))
                    {
                        myclass = "";
                        break;
                    }
                }

                sb.AppendLine("<div class='col-sm-6 col-md-4 mycentered'>");
                sb.AppendLine("<div class='card mycard "+myclass+"' style='width: 18rem;'>");
                sb.AppendLine("<a href = '/"+dr["Type"].ToString()+"'><img class='card-img-top myImg' src='/Pictures/"+dr["Type"].ToString()+"_icon.png' alt='Card image cap'></a>");
                sb.AppendLine("<div class='card-body'>");
                sb.AppendLine("<h4 class='card-title'>"+dr["Name"].ToString()+"</h4>");
                sb.AppendLine("<p class='card-text'>" + (myclass.Equals("") ? "Van " : "Nincs " ) + dr["Name"].ToString() + " játékkal kapcsolatos feladatod.</p>");
                sb.AppendLine("</div>");
                sb.AppendLine("</div>");
                sb.AppendLine("</div>");
                
            }

            return sb.ToString();
        }
    }
}