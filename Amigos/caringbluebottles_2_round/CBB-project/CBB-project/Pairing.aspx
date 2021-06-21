<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Pairing.aspx.cs" Inherits="CBB_project.WebForm1" %>
<%@ MasterType virtualpath="~/Site.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <%: Scripts.Render("~/bundles/pairing") %>
    <div class="myPage">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

           <table id="terms_container" frame="box" ondrop="drop(event)" ondragover="allowDrop(event)" >
          <tr ><td style="text-align:center;">Húzd az itteni elemeket a megfelelő helyre.</td></tr>
          <tr><td class="box"><hr>
    
            <term id="drag1" class="j123" draggable="true" ondragstart="drag(event)">Kacsa</term>
            <term id="drag2" class="c1" draggable="true" ondragstart="drag(event)">Hal</term>
            <term id="drag3" class="j13" draggable="true" ondragstart="drag(event)">Kutya</term>
            <term id="drag4" class="c3" draggable="true" ondragstart="drag(event)">Cica</term>
            <term id="drag5" class="c2" draggable="true" ondragstart="drag(event)">Lepke</term>
            <term id="drag6" class="c3" draggable="true" ondragstart="drag(event)">Csibe</term>
            <term id="drag7" class="c2" draggable="true" ondragstart="drag(event)">Sas</term>
     
            </td></tr>
        </table><psy id="win"></psy>
        <span id="replay"><button data-label="restart" onclick="restartgame();">restart</button></span>


     
        <div id="venn">
          <div id="c1" class="circle","ans1","collection" ondrop="drop(event)" ondragover="allowDrop(event)" >
            <p id="c1Header">Uszik</p>
            <div></div>
          </div>

          <div id="c2" class="circle",   "ans2","collection" ondrop="drop(event)" ondragover="allowDrop(event)" >
            <p id="c2Header">Repul</p>
            <div></div>
          </div>

          <div id="c3" class="circle" >
            <p id="c3Header">Jar</p>
            <div class="collection" ondrop="drop(event)" ondragover="allowDrop(event)" class="ans3"></div>
          </div>



          <div id="j12" class="joined">
            <div class="collection","both" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
          </div>
            
          <div id="j13" class="joined">
            <div class="collection" ondrop="drop(event)" ondragover="allowDrop(event)" class="both"></div>
          </div>
            
          <div id="j23" class="joined">
            <div class="collection" ondrop="drop(event)" ondragover="allowDrop(event)" class="both"></div>
          </div>

          <div id="j123" class="joined">
            <div class="collection" ondrop="drop(event)" ondragover="allowDrop(event)" class="both"></div>
          </div>

  
  
        </div>

    </div>
</asp:Content>
