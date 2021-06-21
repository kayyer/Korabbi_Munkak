using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using System.Data;

namespace CBB_project.Classes
{
    public class SQLIF
    {
        /// <summary>
        /// Az SQL kapcsolat
        /// </summary>
        private SqlConnection sqlConnection;

        /// <summary>
        /// SQL parancs
        /// </summary>
        private SqlCommand sqlCommand;

        /// <summary>
        /// SQL Paraméterek listája
        /// </summary>
        private List<SqlParameter> sqlParameters;

        /// <summary>
        /// Nyitott-e már az SQL kapcsolat
        /// </summary>
        private bool isOpen
        {
            get
            {
                return sqlConnection != null && sqlConnection.State == ConnectionState.Open;
            }
        }

        /// <summary>
        /// Konstruktor, létrehoz
        /// </summary>
        /// <param name="connectionString"></param>
        public SQLIF(string connectionString)
        {
            sqlConnection = new SqlConnection(connectionString);
            sqlCommand = null;
            sqlParameters = new List<SqlParameter>();
        }

        /// <summary>
        /// Teszteli az objektumot
        /// </summary>
        /// <returns>False - nem jó; True - jó</returns>
        public bool testConnection()
        {
            try
            {
                if(sqlConnection == null)
                {
                    return false;
                }

                sqlConnection.Open();
                if(sqlConnection.State != ConnectionState.Open)
                {
                    return false;
                }
                else
                {
                    sqlConnection.Close();
                }

                if(sqlParameters == null)
                {
                    return false;
                }

                return true;
            }
            catch(Exception exc)
            {
                return false;
            }
        }

        /// <summary>
        /// Minden eddig megadott paramétert töröl
        /// </summary>
        public void ResetParameters()
        {
            sqlParameters.Clear();
        }

        /// <summary>
        /// Létrehoz egy SQL parancsot
        /// </summary>
        /// <param name="name"></param>
        public void addSpCommand(string name)
        {
            sqlCommand = new SqlCommand(name, sqlConnection);
            sqlCommand.CommandType = CommandType.StoredProcedure;
            foreach (SqlParameter sp in sqlParameters)
            {
                sqlCommand.Parameters.Add(sp);
            }
        }

        /// <summary>
        /// Visszatérési érték nélkül csak futtat egy SP-t
        /// </summary>
        /// <param name="spName">Sp neve</param>
        public void runSp(string spName)
        {
            try
            {
                if (!isOpen)
                    sqlConnection.Open();
                addSpCommand(spName);
                sqlCommand.ExecuteNonQuery();
            }
            finally
            {
                sqlConnection.Close();
            }
        }

        /// <summary>
        /// DataTable értéket ad vissza
        /// </summary>
        /// <param name="spName">SP neve</param>
        /// <returns>DataTable objektumban a visszatért érték</returns>
        public DataTable getDataTable(string spName)
        {
            DataTable retVal = new DataTable();
            try
            {
                if(!isOpen)
                    sqlConnection.Open();
                addSpCommand(spName);
                SqlDataReader r = sqlCommand.ExecuteReader();
                retVal.Load(r);
            }
            finally
            {
                sqlConnection.Close();
            }
            return retVal;
        }

        /// <summary>
        /// Egész INT paramétert ad az SP paraméter listához
        /// </summary>
        /// <param name="ParameterName">Paraméter neve (pl. @ParamName)</param>
        /// <param name="Value">Értéke</param>
        public void AddIntParameter(string ParameterName, int Value)
        {
            SqlParameter s = new SqlParameter(ParameterName, SqlDbType.Int);
            s.Value = Value;
            sqlParameters.Add(s);
        }

        /// <summary>
        /// VARCHAR paramétert ad az SP paraméter listához
        /// </summary>
        /// <param name="ParameterName">Paraméter neve (pl. @ParamName)</param>
        /// <param name="Value">Értéke</param>
        /// <param name="Length">Varchar hossza</param>
        public void AddVarcharParameter(string ParameterName, string Value, int Length = -1)
        {
            SqlParameter s = new SqlParameter(ParameterName, SqlDbType.VarChar, Length);
            s.Value = Value;
            sqlParameters.Add(s);
        }

        /// <summary>
        /// CHAR paramétert ad az SP paraméter listához
        /// </summary>
        /// <param name="ParameterName">Paraméter neve (pl. @ParamName)</param>
        /// <param name="Value">Értéke</param>
        public void AddCharParameter(string ParameterName, string Value, int Length = -1)
        {
            SqlParameter s = new SqlParameter(ParameterName, SqlDbType.Char, Length);
            s.Value = Value;
            sqlParameters.Add(s);
        }

    }
}