console.info("version 1.0");



var count = 0;

window.onload = function () {
    setTimeout(function () {
        var j = document.getElementById("j12");
        var j13 = document.getElementById("j13");
        var j23 = document.getElementById("j23");
        var j123 = document.getElementById("j123");

        var c1 = document.getElementById("c1");
        var c2 = document.getElementById("c2");
        var c3 = document.getElementById("c3");

        // j.style.left = c2.getBoundingClientRect().left + 'px';
        j.style.top = (c3.style.top + c3.offsetHeight ) * 0.97+ 'px';
        j.style.left = c2.getBoundingClientRect().left  + 'px';
        j.style.width = (c1.getBoundingClientRect().right - c2.getBoundingClientRect().left) * 0.65 + 'px';
        j.style.height = (c1.getBoundingClientRect().bottom - c3.getBoundingClientRect().bottom) * 0.6 + 'px';

        j13.style.left = c3.getBoundingClientRect().left  + 'px';
        j13.style.top = (c1.getBoundingClientRect().top - c3.getBoundingClientRect().top) * 1.05 + 'px';
        j13.style.width = c2.getBoundingClientRect().left - c3.getBoundingClientRect().left + 'px';
        j13.style.height = (c3.getBoundingClientRect().bottom - c1.getBoundingClientRect().top) * 0.7 + 'px';

        j23.style.left = c1.getBoundingClientRect().right  + 'px';
        j23.style.top = (c2.getBoundingClientRect().top - c3.getBoundingClientRect().top) * 1.05 + 'px';
        j23.style.width = c3.getBoundingClientRect().right - c1.getBoundingClientRect().right + 'px';
        j23.style.height = (c3.getBoundingClientRect().bottom - c1.getBoundingClientRect().top) * 0.7 + 'px';

        j123.style.left = c2.getBoundingClientRect().left + 'px';
        j123.style.top = (c1.getBoundingClientRect().top - c3.getBoundingClientRect().top) * 1.45 + 'px';
        j123.style.width = (c1.getBoundingClientRect().right - c2.getBoundingClientRect().left)* 0.65 + 'px';
        j123.style.height = (c3.getBoundingClientRect().bottom - c1.getBoundingClientRect().top) * 0.55 + 'px';

       
        console.log(c3.getBoundingClientRect().top);
        console.log(c1.getBoundingClientRect().left);

    }, 60);
};

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("Text", ev.target.id);
    
    
}

function drop(ev) {
    ev.preventDefault();
    console.log(ev.target.className);
    var thetag = ev.target.nodeName;
    var theanswer = ev.target.id;
    var data = ev.dataTransfer.getData("Text");
    console.log(data);
    console.log(ev.target.id);
    console.log(theanswer[1]);
    var rect = ev.target.getBoundingClientRect();
    console.log(rect.top, rect.right, rect.bottom, rect.left);
    console.log(ev.clientX, ev.clientY);
    if (thetag == "TERM") {
        null;
    }
    else {
        ev.target.appendChild(document.getElementById(data));
        var ans = document.getElementById(data).className;
        checkAnswer(data, theanswer,ans);
    }
}

function checkAnswer(theterm, theiranswer,ans) {
   // var stripped = theterm.replace('drag', '');
    //var _0xc89b = ["", "\x62\x6F\x74\x68", "\x61\x6E\x73\x31", "\x61\x6E\x73\x32"];
    //var theanswers = [_0xc89b[0], _0xc89b[1], _0xc89b[2], _0xc89b[3], _0xc89b[2], _0xc89b[2], _0xc89b[2], _0xc89b[3], _0xc89b[2], _0xc89b[1], _0xc89b[3]];
    if (ans == theiranswer) {
        count++;
        $("#" + theterm + " wr").remove();
        $('#' + theterm).append(" <r>&#x2713;</r>");
        $('#' + theterm).css('cursor', 'default');
        $("#" + theterm).droppable({
            drop: function (event, ui) {
                $(this).html($(event.originalTarget).html());
                $(ui.draggable).draggable("destroy");
            }
        });
        $('#' + theterm).on('dragstart', function (event) { event.preventDefault(); });
    }
    else if (theiranswer == "box") {
        null;
    }
    else {
        $("#" + theterm + " wr").remove();
        $('#' + theterm).append(" <wr>&#x2717;</wr>");
    }
    if (count == 10) {
        $('.box').html("<h3>You Win</h3>");
        var isFirefox = typeof InstallTrigger !== 'undefined';   // Firefox 1.0+
        if (isFirefox == true) {
            $('#replay').css("display", "block");
            $('#replay').css("margin-top", "200px");
            $('#replay').css("margin-left", "44%");
        }
        else {
            $('#win').css("display", "block");
            restartbutton();
        }
    }
}

function restartbutton() {
    setTimeout(function () {
        //$('#win').hide();
        $('#replay').css("display", "block");
    }, 2000);
}


function restartgame() {
    alert("This button will restart the game");
    //window.location.reload();
}