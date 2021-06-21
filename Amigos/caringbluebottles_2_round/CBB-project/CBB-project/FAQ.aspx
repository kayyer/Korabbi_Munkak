<%@ Page Title="Gyakori kérdések" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="FAQ.aspx.cs" Inherits="CBB_project.FAQ" %>
<%@ MasterType virtualpath="~/Site.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent"  runat="server">
        <div class="myPage">
            <% if (dataActive)
                { %>

                <%=getFAQs() %>

            <%}
            else
            {%>

                Sajnos nincs elérhető adat

            <%} %>
               
        </div>
</asp:Content>
