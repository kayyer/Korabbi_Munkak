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
    public partial class FAQ : System.Web.UI.Page
    {
        private DataTable datas;

        public bool dataActive = false;

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
                catch(Exception exc)
                {
                    did = -1;
                }
                return did;
            }
        }

        protected void Page_Init(object sender, EventArgs e)
        {
            try
            {
                Master.SqlIF.ResetParameters();         //ha megáll itt, csak nyomj még egy F5-t (tovább fog menni)
                if (docID >= 0)
                {
                    Master.SqlIF.AddIntParameter("@FID", docID);
                }
                datas = Master.SqlIF.getDataTable("getFAQ");
            }
            catch (Exception exc)
            {
                dataActive = false;
                datas = null;
                return;
            }

            if(datas == null || datas.Rows == null || datas.Rows.Count == 0)
            {
                dataActive = false;
            }
            else
            {
                dataActive = true;
            }
        }


        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected string getFAQs()
        {
            StringBuilder sb = new StringBuilder();
            if (docID < 0)
            {
                foreach (DataRow dr in datas.Rows)
                {
                    sb.AppendLine("<div class='card rounded' style='background-color:#8BBF58; color:white; text-align: center; margin: 10px;'>");
                    sb.AppendLine(" <div class='card-header' style='font-size: 1.6em;'>");
                    sb.AppendLine(dr["Title"].ToString());
                    sb.AppendLine(" </div>");
                    sb.AppendLine(" <a href = '/FAQ/" + dr["FID"].ToString() + "' style='text-decoration:none; color:white;'>");
                    sb.AppendLine("     <div class='card-body'>");
                    sb.AppendLine("         <p class='card-text'>");
                    sb.AppendLine(dr["ShortD"].ToString());
                    sb.AppendLine("         </p>");
                    sb.AppendLine("     </div>");
                    sb.AppendLine(" </a>");
                    sb.AppendLine("</div>");
                }
            }
            else
            {
                DataRow dr = datas.Rows[0];
                sb.AppendLine("<div class='card rounded' style='background-color:#8BBF58; color:white; text-align: center;'>");
                sb.AppendLine(" <div class='card-header' style='font-size: 1.6em;'>");
                sb.AppendLine(dr["Title"].ToString());
                sb.AppendLine(" </div>");
                sb.AppendLine(" <div class='card-body'>");
                sb.AppendLine("     <p class='card-text'>");
                //sb.AppendLine(dr["ShortD"].ToString());
                sb.AppendLine(dr["LongD"].ToString());
                sb.AppendLine("     </p>");
                sb.AppendLine(" </div>");
                sb.AppendLine("</div>");
            }
            return sb.ToString();
        }
    }
}