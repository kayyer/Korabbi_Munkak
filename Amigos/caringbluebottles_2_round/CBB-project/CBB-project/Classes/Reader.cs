using System;
using System.Collections.Generic;
using System.Data;
using System.Data.OleDb;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Web;

namespace CBB_project.Classes
{
    public class Reader
    {

        public Reader()
        {
        }

        public static void setQAData(string filePath, bool isFirstRowHeader, SQLIF sqlif, string ugid, string tid)
        {
            string header = isFirstRowHeader ? "Yes" : "No";

            string pathOnly = Path.GetDirectoryName(filePath);
            string fileName = Path.GetFileName(filePath);

            string sql = @"SELECT * FROM [" + fileName + "]";

            using (OleDbConnection connection = new OleDbConnection(
                      @"Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + pathOnly +
                      ";Extended Properties=\"Text;HDR=" + header + "\""))
            using (OleDbCommand command = new OleDbCommand(sql, connection))
            using (OleDbDataAdapter adapter = new OleDbDataAdapter(command))
            {
                DataTable dataTable = new DataTable();
                dataTable.Locale = CultureInfo.CurrentCulture;
                adapter.Fill(dataTable);
                foreach(DataRow dr in dataTable.Rows)
                {
                    string[] datas = dr[0].ToString().Split(';');
                    sqlif.ResetParameters();
                    sqlif.AddVarcharParameter("@question", datas[0]);
                    sqlif.AddVarcharParameter("@answer", datas[1]);
                    sqlif.AddIntParameter("@UGID", Int32.Parse(ugid));
                    sqlif.AddIntParameter("@TID", Int32.Parse(tid));
                    sqlif.runSp("setQA");
                }
            }
        }
    }
}