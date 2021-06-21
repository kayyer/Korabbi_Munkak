<%@ Page Title="Üzenetek" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Messages.aspx.cs" Inherits="CBB_project.Messages" %>
<%@ MasterType virtualpath="~/Site.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <link href="/Content/messages.css" rel="stylesheet" type="text/css">
    <div class="myPage">
        <% if (docID.Equals(""))
            { %>

                <div class="row mx-auto" style="height: 30px; width:80%; text-align: center;">
                    <div class="col-sm-3" style="height:100%">
                        <asp:ImageButton ID="refreshBut" CssClass="btn btn-warning h-100 d-inline-block" ImageUrl="~/Pictures/refresh_icon.png" OnClick="refreshBut_Click" runat="server"/>
                    </div>
                    <div class="col-sm-auto">
                        <asp:TextBox ID="search" CssClass="form-control" runat="server"/>
                    </div>
                    <div class="col-sm-2" style="height:100%">
                        <asp:ImageButton ID="searchBut" CssClass="btn btn-primary h-100 d-inline-block" ImageUrl="~/Pictures/search.png" AlternateText="Keresés" runat="server" />
                    </div>
                    <div class="col-sm-1" style="height:100%">
                        <asp:ImageButton ID="newB" CssClass="btn btn-success h-100 d-inline-block" ImageUrl="~/Pictures/plus.png" AlternateText="Új" runat="server" OnClick="newClick"/>
                    </div>
                </div>

                <div class="row mx-auto" style="margin-top: 30px; width:80%">
                    <%= getMessages() %>
                </div>

        <%}
        else if (docID.Equals("new"))
        { %>

            <div class="row mx-auto" style="height: 30px; width:80%; text-align: center;">
                <div class="col-sm-3" style="height:100%">
                    <asp:ImageButton ID="refrB" CssClass="btn btn-warning h-100 d-inline-block refresh" ImageUrl="~/Pictures/refresh_icon.png" OnClick="refreshBut_Click" runat="server"/>
                </div>

                <%if (Master.myuser.isAdmin)
                    { %>
                        <div class="col-sm-2">
                            <input type="button" class="btn btn-warning" value="Csoportok" />
                        </div>
                <% } %>
            </div>
            <div class="row mx-auto" style="width:80%">
                <div class="col-sm-12">
                    <asp:TextBox ID="TextBox3" CssClass="form-control name" runat="server" PlaceHolder="címzett"/>
                </div>
                <div class="col-sm-auto">
                    <asp:TextBox ID="subjectTb" CssClass="form-control subject" runat="server" PlaceHolder="tárgy"/>
                </div>
            </div>
            <div class="row mx-auto" style="width:80%">
                <%=newMessage() %>
            </div>
            <div class="row mx-auto" style="width:80%">
                <div class="col-sm-6">
                    <asp:TextBox ID="newtextTb" CssClass="newmessage" PlaceHolder="Új üzenet" runat="server" TextMode="MultiLine"></asp:TextBox>
                </div>
                <div class="col-sm-4">
                    <asp:ImageButton ID="sendBut" ImageUrl="Pictures/send.png" CssClass="btn btn-success d-inline-block" Style="width:60px; height:40px; position:absolute; bottom:0; right:600;" AlternateText="Küldés" OnClick="sendBut_Click" runat="server"/>
                </div>
            </div>

        <%}
        else
        { %>

            <div class="row mx-auto" style="height: 30px; width:80%; text-align: center;">
                <div class="col-sm-3" style="height:100%">                    
                    <asp:ImageButton ID="rfBtn" CssClass="btn btn-warning h-100 d-inline-block" ImageUrl="~/Pictures/refresh_icon.png" OnClick="refreshBut_Click" runat="server"/>
                </div>
                <div class="col-sm-9">
                    <asp:TextBox ID="TextBox1" CssClass="form-control" runat="server"/>
                </div>
            </div>
            <div class="row mx-auto" style="width: 80%">
                <div class="col-sm-12">
                    <asp:TextBox ID="TextBox2" CssClass="form-control subject_2"  runat="server"/>
                </div>
            </div>
            <div class="row mx-auto" style="width:80%">
                <%=getMessage() %>
            </div>
            <div class="row mx-auto" style="height: 30px; width:80%">
                <div class="col-sm-8">
                    <asp:TextBox ID="textTb" CssClass="newmessage answer" TextMode="MultiLine" Style="margin-left: 75px;" PlaceHolder="Új üzenet" runat="server"></asp:TextBox>
                </div>
                <div class="col-sm-2" style="">
                    <asp:ImageButton ID="sendBut_2" ImageUrl="/Pictures/send.png" CssClass="btn btn-success d-inline-block" Style="width:50px; height:30px; position:absolute; bottom:0; right:600;" AlternateText="Küldés" OnClick="Send_Click" runat="server"/>
                </div>
            </div>


        <%} %>
    </div>
</asp:Content>
