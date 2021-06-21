<%@ Page Title="Felhasználók keresése" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="ListUsers.aspx.cs" Inherits="CBB_project.ListUsers" %>
<%@ MasterType virtualpath="~/Site.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="myPage">
        <asp:TextBox ID="usernameTb" CssClass="form-control" PlaceHolder="Felhasználó keresése" runat="server"></asp:TextBox>
        <asp:Button ID="searchBut" CssClass="btn btn-primary mt-4" Text="Keresés" OnClick="searchBut_Click" runat="server" />

        <asp:Panel ID="dataPanel" CssClass="mt-5" runat="server"></asp:Panel>
    </div>
</asp:Content>
