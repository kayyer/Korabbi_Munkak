using CBB_project.Classes;
using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CBB_project
{
    public partial class SetQuestions : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                Master.SqlIF.ResetParameters();
                DataTable dt = Master.SqlIF.getDataTable("getUserGroups");
                foreach(DataRow dr in dt.Rows)
                {
                    groupDdl.Items.Add(new ListItem(dr["GroupName"].ToString(), dr["UGID"].ToString()));
                }

                Master.SqlIF.ResetParameters();
                Master.SqlIF.AddIntParameter("@all", 1);
                dt = Master.SqlIF.getDataTable("getTasks");
                foreach (DataRow dr in dt.Rows)
                {
                    taskDdl.Items.Add(new ListItem(dr["Name"].ToString(), dr["TID"].ToString()));
                }
            }
        }

        protected void btnUpload_Click(object sender, EventArgs e)
        {
            string strFileName;
            string strFilePath = "";
            string strFolder;
            strFolder = Server.MapPath("./Uploaded_Files/");
            // Retrieve the name of the file that is posted.
            strFileName = oFile.PostedFile.FileName;
            strFileName = Path.GetFileName(strFileName);
            if (oFile.Value != "")
            {
                // Create the folder if it does not exist.
                if (!Directory.Exists(strFolder))
                {
                    Directory.CreateDirectory(strFolder);
                }
                // Save the uploaded file to the server.
                strFilePath = strFolder + strFileName;
                if (File.Exists(strFilePath))
                {
                    lblUploadResult.Text = strFileName + " already exists on the server!";
                }
                else
                {
                    oFile.PostedFile.SaveAs(strFilePath);
                    lblUploadResult.Text = strFileName + " has been successfully uploaded.";
                }
            }
            else
            {
                lblUploadResult.Text = "Click 'Browse' to select the file to upload. Katt";
            }
            // Display the result of the upload.
            frmConfirmation.Visible = true;

            Reader.setQAData(strFilePath, false, Master.SqlIF, groupDdl.SelectedValue, taskDdl.SelectedValue);
            File.Delete(strFilePath);
            lblUploadResult.Text = "Kérdések sikeresen feltöltve.";
        }
    }
}