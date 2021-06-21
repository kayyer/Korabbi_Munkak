<%@ Page Title="Események" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Events.aspx.cs" Inherits="CBB_project.Events" %>
<%@ MasterType virtualpath="~/Site.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="myPage">

            <% if (dataActive)
            { %>

                <%=getEvents() %>

            <%}
            else
            {%>

                Sajnos nincs elérhető adat

            <%} %>

    </div>
</asp:Content>
