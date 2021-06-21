using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CBB_project.Classes
{
    public class myUser
    {
        public string UserName;

        public bool isAdmin;

        public string FullName;

        public string sessionID;

        public int userID;

        public myUser(string username, string fullname, bool isadmin, int userid, string sessionid = "")
        {
            UserName = username;
            FullName = fullname;
            isAdmin = isadmin;
            sessionID = sessionid;
            userID = userid;
        }
    }
}