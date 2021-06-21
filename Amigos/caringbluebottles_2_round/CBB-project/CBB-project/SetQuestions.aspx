<%@ Page Title="Kérdések szerkesztése" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="SetQuestions.aspx.cs" Inherits="CBB_project.SetQuestions" %>
<%@ MasterType virtualpath="~/Site.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="myPage">

        <input id="oFile" type="file" class="btn btn-success d-inline" runat="server" name="oFile" />
        <asp:DropDownList ID="groupDdl" CssClass="form-control d-inline-block h-100" runat="server"></asp:DropDownList>
        <asp:DropDownList ID="taskDdl" CssClass="form-control d-inline-block h-100" runat="server"></asp:DropDownList>

        <asp:Button id="btnUpload"  Text="Feltöltés" CssClass="btn btn-success mt-4 d-block" runat="server" OnClick="btnUpload_Click"></asp:button>
        <asp:Panel ID="frmConfirmation" Visible="False" Runat="server">
            <asp:Label id="lblUploadResult" Runat="server"></asp:Label>
        </asp:Panel>

    </div>
</asp:Content>