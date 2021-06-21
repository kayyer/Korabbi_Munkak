console.info("TTS 1.1.4 is ready");

function initFunc() {
    console.log("TTS loaded");
}

function readST(wheretofind, slanguage) {
    var text = jQuery('#' + wheretofind).html();
    console.log(text + " readed from " + wheretofind);
    var msg = new SpeechSynthesisUtterance(text);
    if (slanguage == null) {
        slanguage = "en-US";
    }
    msg.lang = slanguage;
    window.speechSynthesis.speak(msg);

    return false;
}

jQuery(document).ready(initFunc);