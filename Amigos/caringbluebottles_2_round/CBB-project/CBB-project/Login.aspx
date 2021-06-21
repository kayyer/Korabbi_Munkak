<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="CBB_project.Login" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml" class="loginClass">
<head runat="server">
    <title>Belépés - CBB</title>

    <link href="/Pictures/amigos_tr.png" rel="shortcut icon" type="image/x-icon" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <webopt:bundlereference runat="server" path="~/Content/css" />

</head>
<body style="background-color:#8BBF58;">
    <form id="form1" runat="server">
        <div class="navbar navbar-inverse navbar-fixed-top" style="background-color:#8BBF58;">
                <img style="height:90px; width:auto; padding:5px;" src="Pictures/amigos_tr.png">
        </div>

        <div class="row" id="mid" class="align-items-center">
            <div class="col-2 col-md-4"></div>
            <div class="col-8 col-md-4" style="margin-top: 80px;">
                <div class="card">
                    <img class="card-img-top mx-auto" style="height: 15rem; width: auto;" src="Pictures/amigos1.png" alt="Card image cap">
                    <div class="card-body" style="text-align:center;">
                        <h3 class="card-title">Belépés</h3>
                        <p class="card-text">Lépj be!</p>
                        <div class="row">
                            <asp:TextBox CssClass="form-control w-75 mx-auto" MaxLength="32" Placeholder="Felhasználónév" ID="name" runat="server"></asp:TextBox>
                        </div>
                        <div class="row">                   
                            <asp:TextBox TextMode="Password" CssClass="form-control w-75 mx-auto" Placeholder="Jelszó" ID="psw" runat="server"></asp:TextBox>
                        </div>
                        <asp:Button ID="loginBut" CssClass="btn btn-success mt-3 p-2" Text="Mehet!" OnClick="loginClick" runat="server" />
                        <asp:LinkButton ID="forgottenPsw" CssClass="d-block mt-4" Text="Elfelejtett jelszó" runat="server" OnClick="forgottenClick"></asp:LinkButton>
                    </div>
                </div>
            </div>
            <div class="col-2 col-md-4"></div>
        </div>
        <div class="row" id="down" style="position:fixed; bottom:0; width: 100%; text-align: center;">
             <p style="width:100%">Powered by CBB - 2021</p>
        </div>
    </form>
</body>
</html>
