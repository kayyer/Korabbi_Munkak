<%@ Page Title="Főoldal" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="MainSite.aspx.cs" Inherits="CBB_project.MainSite" %>
<%@ MasterType virtualpath="~/Site.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <% if (Master.myuser.isAdmin)
    { %>

        <div class="row menuClass myPage">
            <div class="col-sm-6 col-md-6">
                <div class="row myrow menurow">
                    <a href="/SetQuestions" class="mx-auto"><img src="/Pictures/Questiongear.png" class="myImg" style="width: 8rem;"/></a>
                    <p>Kérdések</p>
                </div>
                <div class="row myrow menurow">
                    <a href="/SetUsergroups" class="mx-auto"><img src="/Pictures/crowd.png" class="myImg" style="width: 8rem;"/></a>
                    <p>Csoportok</p> 
                </div>
                <div class="row myrow menurow">
                    <a href="/ListUsers" class="mx-auto"><img src="/Pictures/shortlist.png" class="myImg" style="width: 8rem;"/></a>
                    <p>Felhasználók</p>
                </div>
                </div>
             <div class="col-sm-6 col-md-6">
                <div class="row myrow menurow">
                    <a href="#" class="mx-auto"><img src="/Pictures/FAQgear.png" class="myImg" style="width: 8rem; opacity: 0.30;"/></a>
                    <p>FAQ</p>
                </div>
                <div class="row myrow menurow">
                    <a href="#" class="mx-auto"><img src="/Pictures/event-management.png" class="myImg" style="width: 8rem; opacity: 0.30;"/></a>
                    <p>Események</p>
                </div>

                <div class="row myrow menurow">
                    <a href="#" class="mx-auto"><img src="/Pictures/folder.png" class="myImg" style="width: 8rem; opacity: 0.30;"/></a>
                    <p>Kérvények</p>
                </div>
                 </div>
            </div>
        
    <%}
    else
    { %>
        <div class="row menuClass myPage">

            <div class="mcol col-sm-3 col-md-2" style="width: 10rem;">
                <img src="/Pictures/amigos2.png" class="myImg" />
            </div>
            <div class="mcol col-sm-9 col-md-10">
                <div class="row myrow menurow">
                    <a href="/Tasks" class="ml-auto"><img src="/Pictures/schedule.png" class="myImg" style="width: 8rem;"/></a>
                    <p>Feladatok</p>
                </div>
                <div class="row myrow menurow">
                    <a href="/Events" class="ml-auto"><img src="/Pictures/events.png" class="myImg" style="width: 8rem;"/></a>
                    <p>Események</p>
                </div>

                <div class="row myrow menurow">
                    <a href="/Tetris" onclick="return readST('linkToUser', 'hu-HU')" class="ml-auto"><img src="/Pictures/joystick.png" class="myImg" style="width: 8rem;"/></a>

                     <p>Játékok</p>
                </div>
                <div class="row myrow menurow">
                    <a href="/FAQ" class="ml-auto"><img src="/Pictures/conversation.png" class="myImg" style="width: 8rem;"/></a>
                     <p>Kérdések</p>
                </div>
            </div>
        </div>
    <%} %>

</asp:Content>
