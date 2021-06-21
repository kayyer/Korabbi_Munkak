using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;
namespace CBB_project
{
    public partial class Uploader : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
           // this.Form.Enctype = "multipart/form-data";
            //this.Form.Method = "post";
            //this.Form.Action = "Uploader.aspx";
        }

        protected void btnUpload_Click(object sender, EventArgs e)
        {
            Console.WriteLine("MOst baszdmeg lefutott");
            string strFileName;
            string strFilePath;
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
        }
    }
}