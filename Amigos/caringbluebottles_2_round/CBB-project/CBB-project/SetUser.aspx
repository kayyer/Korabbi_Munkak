<%@ Page Title="Fiók beállítások" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="SetUser.aspx.cs" Inherits="CBB_project.SetUser" %>
<%@ MasterType virtualpath="~/Site.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="myPage">
        <div class="row">
            <div class="col-sm-6 form-group">
                <asp:TextBox ID="usernameTb" CssClass="form-control" Placeholder="Felhasználónév" runat="server"></asp:TextBox>
            </div>
            <div class="col-sm-6 form-group">
                <asp:TextBox ID="fullnameTb" CssClass="form-control" Placeholder="Teljes név" runat="server"></asp:TextBox>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6 form-group">
                <asp:TextBox ID="pswTb" TextMode="Password" CssClass="form-control" Placeholder="Jelszó" runat="server"></asp:TextBox>
            </div>
            <div class="col-sm-6 form-group">
                <asp:TextBox ID="repswTb" TextMode="Password" CssClass="form-control" Placeholder="Jelszó újra" runat="server"></asp:TextBox>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6 form-group">
                <asp:TextBox TextMode="Date" ID="birthdateCal" CssClass="form-control" runat="server"></asp:TextBox>
            </div>
            <div class="col-sm-6 form-group">
                <asp:TextBox ID="descTb" CssClass="form-control" Placeholder="Rövid leírás magadról" runat="server"></asp:TextBox>
            </div>
        </div>
        <div class="row" style="margin-top:30px">
            <div class="col-md-12 pl-auto form-group">
                <%if (docID == -1 || Master.myuser.userID == docID)
                { %>
                    <asp:Button ID="saveBut" CssClass="btn btn-success" Text="Mentés" OnClick="saveBut_Click" runat="server"/>
                <% }
                else
                {%>
                    <asp:Button ID="delBut" CssClass="btn btn-danger" Text="Törlés" OnClick="delBut_Click" runat="server"/>
                <%} %>
            </div>
        </div>

        <div class="row" style="margin-top:20px">
            <%= getGroups() %>
        </div>

        <!-- <input type="checkbox" id="g1" value="Group1">
        <label for="vehicle1">Group1</label><br>
        <input type="checkbox" id="g2" value="Group2">
        <label for="vehicle2">Group2</label><br>
        <input type="checkbox" id="g3" value="Group3">
        <label for="vehicle3">Group3</label><br> -->

    </div>
</asp:Content>
