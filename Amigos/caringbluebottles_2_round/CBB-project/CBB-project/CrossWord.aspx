<%@ Page Title="Kereszrejtvény" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="CrossWord.aspx.cs" Inherits="CBB_project.CrossWord" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <link href="/Content/crossers.css" rel="stylesheet" type="text/css">
    <%: Scripts.Render("~/bundles/crossers") %>

    <div class="myPage">
            <div class="m-box row">

            <div id="11" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="12" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="13" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="14" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="15" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="16" class="empty unvisible"><p class="myInput">A</p></div>

            <div id="21" class="empty unvisible"><p class="myInput">N</p></div>
            <div id="22" class="empty unvisible"><p class="myInput">O</p></div>
            <div id="23" class="filled-good numbered"><p class="myInput">N</p></div>
            <div id="24" class="filled-good"><p class="myInput">O</p></div>
            <div id="25" class="filled-good"><p class="myInput">S</p></div>
            <div id="26" class="filled-good numbered"><p class="myInput">E</p></div>

            <div id="31" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="32" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="33" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="34" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="35" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="36" class="empty"><p class="myInput" contenteditable="true"></p></div>

            <div id="41" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="42" class="filled-good numbered"><p class="myInput">K</p></div>
            <div id="43" class="filled-good"><p class="myInput">A</p></div>
            <div id="44" class="filled-good"><p class="myInput">C</p></div>
            <div id="45" class="filled-good"><p class="myInput">S</p></div>
            <div id="46" class="filled-good"><p class="myInput">A</p></div>
            
            <div id="51" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="52" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="53" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="54" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="55" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="56" class="empty"><p class="myInput" contenteditable="true"></p></div>
                        
            <div id="61" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="62" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="63" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="64" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="65" class="empty unvisible"><p class="myInput">A</p></div>
            <div id="66" class="empty unvisible"><p class="myInput">A</p></div>

        </div>

        <div class="row rounded" style="background-color:#993166; color:white; text-align:center; margin-top:40px;">
            <div class="col-md-12">
                <p>1 - Orr angolul</p>
                <p>2 - A boltban az árus ezt teszi, amikor vásárolsz tőle.</p>
                <p>3 - Hápog és úszik a vízen.</p>
            </div>
        </div>
    </div>

</asp:Content>
