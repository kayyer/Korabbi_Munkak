<%@ Page Title="Felh. csoportok kezelése" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="SetUsergroups.aspx.cs" Inherits="CBB_project.SetUsergroups" %>
<%@ MasterType virtualpath="~/Site.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="myPage">

        <% if (docID.Equals("")){ %>
            <asp:Button ID="newBut" Text="Új felvétele" OnClick="newBut_Click" CssClass="btn btn-success mb-4" runat="server" />
            <%= getUGroups() %>
        <%}
        else { %>
            <div class="row">
                <div class="col-sm-6 form-group">
                    <asp:TextBox ID="groupnameTb" CssClass="form-control" Placeholder="Felhasználónév" runat="server"></asp:TextBox>
                </div>
            </div>
            <div class="row" style="margin-top:40px">
                <div class="col-md-12 pl-auto form-group">
                    <asp:Button ID="saveBut" CssClass="btn btn-success" Text="Mentés" OnClick="saveBut_Click" runat="server"/>
                </div>
            </div>
        <%} %>


    </div>
</asp:Content>
