<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Uploader.aspx.cs" Inherits="CBB_project.Uploader" %>
<%@ MasterType virtualpath="~/Site.Master" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
            <div class="myPage">

                   <input id="oFile" type="file" runat="server" name="oFile" />
                    <asp:Button id="btnUpload"  Text="Feltoltes" runat="server" OnClick="btnUpload_Click"></asp:button>
                    <asp:Panel ID="frmConfirmation" Visible="False" Runat="server">
                        <asp:Label id="lblUploadResult" Runat="server"></asp:Label>
                    </asp:Panel>

            </div>
</asp:Content>
