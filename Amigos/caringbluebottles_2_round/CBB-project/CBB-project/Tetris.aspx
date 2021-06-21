<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Tetris.aspx.cs" Inherits="CBB_project.Tetris1" %>
<%@ MasterType virtualpath="~/Site.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <link rel="stylesheet" href="Scripts/Game/blockrain.css">
    <script src="Scripts/Game/blockrain.js"></script>

    <div class="myPage">
        <div class="game mx-auto" style="width:40%; height:600px;"></div>
    </div>

    <script>
        $('.game').blockrain();
    </script>

</asp:Content>
