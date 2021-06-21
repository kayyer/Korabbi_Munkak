console.info("version 1.1");

var id = null;
var prevCard = [];

var congrats = 0;

number = 0;
var animations = ['https://image.ibb.co/epha5A/giphy.gif',
    'https://image.ibb.co/epha5A/giphy.gif',
    'https://image.ibb.co/epha5A/giphy.gif'
];

var refreshIntervalId = setInterval(function () {
    jQuery("#hiddenimageid").attr('src', animations[number]);
}, 1)
function character() {
    clearInterval(refreshIntervalId);
    jQuery("#hiddenimageid").attr('src', animations[number]);

    console.log(number);
    number++;


}

function clickPicture(p1) {

    for (var i = 0; i < 10; i++) {
        jQuery("#picture0" + i).removeClass("show");
        jQuery("#picture0" + i).addClass("defaultMemory");
        jQuery("#picture0" + i).attr('src', '/Pictures/amigosCard.png');
    }

    switch (p1) {

        case "picture00":
            jQuery("#" + p1).attr('src', '/Pictures/kakadu.jpg');
            break;

        case "picture01":
            jQuery("#" + p1).attr('src', '/Pictures/lion.jpg');
            break;

        case "picture02":
            jQuery("#" + p1).attr('src', '/Pictures/lion_megoldás.png');
            var msg = new SpeechSynthesisUtterance("Lion");
            window.speechSynthesis.speak(msg);
            break;

        case "picture03":
            jQuery("#" + p1).attr('src', '/Pictures/emu.jpg');
            break;

        case "picture04":
            jQuery("#" + p1).attr('src', '/Pictures/zebra.jpg');
            break;

        case "picture05":
            jQuery("#" + p1).attr('src', '/Pictures/varánusz.jpg');
            break;

        case "picture06":
            jQuery("#" + p1).attr('src', '/Pictures/zebra_megoldás.png');
            var msg = new SpeechSynthesisUtterance("Zebra");
            window.speechSynthesis.speak(msg);
            break;

        case "picture07":
            jQuery("#" + p1).attr('src', '/Pictures/kakadu_megoldás.png');
            var msg = new SpeechSynthesisUtterance("Cockatoo");
            window.speechSynthesis.speak(msg);
            break;

        case "picture08":
            jQuery("#" + p1).attr('src', '/Pictures/emu_megoldás.png');
            var msg = new SpeechSynthesisUtterance("Emu");
            window.speechSynthesis.speak(msg);
            break;

        case "picture09":
            jQuery("#" + p1).attr('src', '/Pictures/varánusz_megoldás.png');
            var msg = new SpeechSynthesisUtterance("Varanus");
            window.speechSynthesis.speak(msg);
            break;

    }
    var elem = jQuery("#" + p1);

    elem.toggleClass("show");

    prevCard.push(p1);

    var pairs = [["picture01", "picture02"], ["picture04", "picture06"], ["picture00", "picture07"], ["picture03", "picture08"], ["picture05", "picture09"]];

    for (i = 0; i < pairs.length; i++) {
        if ((prevCard[prevCard.length - 1] == pairs[i][0] || prevCard[prevCard.length - 1] == pairs[i][1]) && (prevCard[prevCard.length - 2] == pairs[i][0] || prevCard[prevCard.length - 2] == pairs[i][1])) {
            congrats += 1;
            jQuery("#" + prevCard[prevCard.length - 1]).css('visibility', 'hidden');
            jQuery("#" + prevCard[prevCard.length - 2]).css('visibility', 'hidden');
            jQuery("#MainContent_label2").text(congrats);
            console.info("Eredmény: " + congrats);

            if (congrats == 5) {
                character();
            }
        }

    }

    return false;
}
