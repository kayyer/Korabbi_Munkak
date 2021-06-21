<%@ Page Title="Szókereső" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="WordSearcher.aspx.cs" Inherits="CBB_project.WordSearcher" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <link href="/Content/crossers.css" rel="stylesheet" type="text/css">
    <%: Scripts.Render("~/bundles/crossers") %>

    <div class="myPage">

        <div class="m-box row">

            <div id="11" class="empty"><p class="myInput" onclick="return crosserClick(11)">I</p></div>
            <div id="12" class="filled-good"><p class="myInput" onclick="return crosserClick(12)">K</p></div>
            <div id="13" class="filled-good"><p class="myInput" onclick="return crosserClick(13)">U</p></div>
            <div id="14" class="filled-good"><p class="myInput" onclick="return crosserClick(14)">T</p></div>
            <div id="15" class="filled-good"><p class="myInput" onclick="return crosserClick(15)">Y</p></div>
            <div id="16" class="filled-good"><p class="myInput" onclick="return crosserClick(16)">A</p></div>

            <div id="21" class="empty"><p class="myInput" onclick="return crosserClick(21)">E</p></div>
            <div id="22" class="empty"><p class="myInput" onclick="return crosserClick(22)">R</p></div>
            <div id="23" class="empty"><p class="myInput" onclick="return crosserClick(23)">K</p></div>
            <div id="24" class="empty"><p class="myInput" onclick="return crosserClick(24)">C</p></div>
            <div id="25" class="empty"><p class="myInput" onclick="return crosserClick(25)">B</p></div>
            <div id="26" class="empty"><p class="myInput" onclick="return crosserClick(26)">J</p></div>

            <div id="31" class="filled-good"><p class="myInput" onclick="return crosserClick(31)">S</p></div>
            <div id="32" class="empty"><p class="myInput" onclick="return crosserClick(32)">G</p></div>
            <div id="33" class="empty"><p class="myInput" onclick="return crosserClick(33)">L</p></div>
            <div id="34" class="empty"><p class="myInput" onclick="return crosserClick(34)">O</p></div>
            <div id="35" class="empty"><p class="myInput" onclick="return crosserClick(35)">Q</p></div>
            <div id="36" class="empty"><p class="myInput" onclick="return crosserClick(36)">Y</p></div>

            <div id="41" class="filled-good"><p class="myInput" onclick="return crosserClick(41)">A</p></div>
            <div id="42" class="empty"><p class="myInput" onclick="return crosserClick(42)">Q</p></div>
            <div id="43" class="empty"><p class="myInput" onclick="return crosserClick(43)">W</p></div>
            <div id="44" class="empty"><p class="myInput" onclick="return crosserClick(44)">V</p></div>
            <div id="45" class="empty"><p class="myInput" onclick="return crosserClick(45)">I</p></div>
            <div id="46" class="empty"><p class="myInput" onclick="return crosserClick(46)">A</p></div>
            
            <div id="51" class="filled-good"><p class="myInput" onclick="return crosserClick(51)">J</p></div>
            <div id="52" class="empty"><p class="myInput" onclick="return crosserClick(52)">K</p></div>
            <div id="53" class="empty"><p class="myInput" onclick="return crosserClick(53)">I</p></div>
            <div id="54" class="empty"><p class="myInput" onclick="return crosserClick(54)">L</p></div>
            <div id="55" class="empty"><p class="myInput" onclick="return crosserClick(55)">E</p></div>
            <div id="56" class="empty"><p class="myInput" onclick="return crosserClick(56)">R</p></div>
                        
            <div id="61" class="filled-good"><p class="myInput" onclick="return crosserClick(61)">T</p></div>
            <div id="62" class="empty"><p class="myInput" onclick="return crosserClick(62)">N</p></div>
            <div id="63" class="empty"><p class="myInput" onclick="return crosserClick(63)">Y</p></div>
            <div id="64" class="empty"><p class="myInput" onclick="return crosserClick(64)">O</p></div>
            <div id="65" class="empty"><p class="myInput" onclick="return crosserClick(65)">L</p></div>
            <div id="66" class="empty"><p class="myInput" onclick="return crosserClick(66)">C</p></div>

        </div>

        <div class="row rounded" style="background-color:#993166; color:white; text-align:center; margin-top:40px;">
            <div class="col-md-12">
                <p>1 - Ugat, négy lába van</p>
                <p>2 - Cheese magyarul</p>
                <p>3 - 6+2 eredménye</p>
            </div>
        </div>

    </div>
</asp:Content>
