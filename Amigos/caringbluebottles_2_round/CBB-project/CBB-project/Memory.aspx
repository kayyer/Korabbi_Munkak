<%@ Page Title="Memória" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Memory.aspx.cs" Inherits="CBB_project.Memory" %>
<%@ MasterType virtualpath="~/Site.Master" %>


<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <%: Scripts.Render("~/bundles/memory") %>
    <link href="/Content/memory.css" rel="stylesheet" type="text/css">
    <div class="myPage">
    <!--HTML-->

    <p class="results">Szia! Itt a feladatod az egymáshoz kapcsolódó kártyapárok megtalálása. A kártyák lefordítva vannak, egyszerre csak egyet tudsz megnézni, mi van rajta, ezért meg kell jegyezned, melyik kártyán mi szerepelt. </p>

    <div>
        <img class="memoryImg memoryImg00" src="/Pictures/amigosCard.png" alt="Kártyalap" id="picture00" onclick='return clickPicture("picture00")'>
        <img class="memoryImg memoryImg01" src="/Pictures/amigosCard.png" alt="Kártyalap" id="picture01" onclick='return clickPicture("picture01")'>
        <img class="memoryImg memoryImg02" src="/Pictures/amigosCard.png" alt="Kártyalap" id="picture02" onclick='return clickPicture("picture02")'>
        <img class="memoryImg memoryImg03" src="/Pictures/amigosCard.png" alt="Kártyalap" id="picture03" onclick='return clickPicture("picture03")'>
        <img class="memoryImg memoryImg04" src="/Pictures/amigosCard.png" alt="Kártyalap" id="picture04" onclick='return clickPicture("picture04")'>
        <img class="memoryImg memoryImg05" src="/Pictures/amigosCard.png" alt="Kártyalap" id="picture05" onclick='return clickPicture("picture05")'>
        <img class="memoryImg memoryImg06" src="/Pictures/amigosCard.png" alt="Kártyalap" id="picture06" onclick='return clickPicture("picture06")'>
        <img class="memoryImg memoryImg07" src="/Pictures/amigosCard.png" alt="Kártyalap" id="picture07" onclick='return clickPicture("picture07")'>
        <img class="memoryImg memoryImg08" src="/Pictures/amigosCard.png" alt="Kártyalap" id="picture08" onclick='return clickPicture("picture08")'>
        <img class="memoryImg memoryImg09" src="/Pictures/amigosCard.png" alt="Kártyalap" id="picture09" onclick='return clickPicture("picture09")'>
    </div> 
        <img class="gif" id="hiddenimageid">
    <div style="margin-top:600px;">
        <asp:Label CssClass="results" ID="label1" runat="server" Text="Eredmény: "></asp:Label>
        <asp:Label CssClass="results" ID="label2" runat="server" Text="még nincs találat"></asp:Label>
    </div>
    <!-- HTML vége -->
    </div>
</asp:Content>
