<%@ Page Title="Szókihúzó" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="WordCrosser.aspx.cs" Inherits="CBB_project.WordCrosser" %>
<%@ MasterType virtualpath="~/Site.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <link href="/Content/crossers.css" rel="stylesheet" type="text/css">
    <%: Scripts.Render("~/bundles/crossers") %>

    <div class="myPage">
        <div class="s-box row">

            <div id="11" class="filled-good"><p class="myInput" onclick="return crosserClick(11)">A</p></div>
            <div id="12" class="filled-good"><p class="myInput" onclick="return crosserClick(12)">K</p></div>
            <div id="13" class="filled-good"><p class="myInput" onclick="return crosserClick(13)">Ú</p></div>
            <div id="14" class="filled-good"><p class="myInput" onclick="return crosserClick(14)">T</p></div>

            <div id="21" class="filled-good"><p class="myInput" onclick="return crosserClick(21)">J</p></div>
            <div id="22" class="empty"><p class="myInput" onclick="return crosserClick(22)">H</p></div>
            <div id="23" class="empty"><p class="myInput" onclick="return crosserClick(23)">J</p></div>
            <div id="24" class="empty"><p class="myInput" onclick="return crosserClick(24)">O</p></div>

            <div id="31" class="filled-good"><p class="myInput" onclick="return crosserClick(31)">T</p></div>
            <div id="32" class="empty"><p class="myInput" onclick="return crosserClick(32)">É</p></div>
            <div id="33" class="empty"><p class="myInput" onclick="return crosserClick(33)">B</p></div>
            <div id="34" class="empty"><p class="myInput" onclick="return crosserClick(34)">B</p></div>

            <div id="41" class="filled-good"><p class="myInput" onclick="return crosserClick(41)">Ó</p></div>
            <div id="42" class="empty"><p class="myInput" onclick="return crosserClick(42)">T</p></div>
            <div id="43" class="empty"><p class="myInput" onclick="return crosserClick(43)">A</p></div>
            <div id="44" class="empty"><p class="myInput" onclick="return crosserClick(44)">N</p></div>

        </div>

        <div class="row rounded" style="background-color:#993166; color:white; text-align:center; margin-top:40px;">
            <div class="col-md-12">
                <p>1 - Door magyarul</p>
                <p>2 - Addig jár a korsó a _____ra</p>
                <p>3 - Ebből négy, az egy hónap</p>

                <p style="font-style:italic">Együtt erősebbek és ügyesebbek vagyunk, ha együtt vagyunk minden ______ megy!</p>
            </div>
        </div>

    </div>
</asp:Content>
