console.log("crossers.js 1.4 ready");

var beforeIDs = "";
var click = 0;

function crosserClick(id) {
    var ids = id.toString();
    var beforeID = 0;
    if (beforeIDs != "") {
        beforeID = parseInt(beforeIDs);
    }

    if (beforeIDs[0] == ids[0]) {
        if (beforeIDs[1] < ids[1]) {
            for (var x = beforeID + 1; x < id; x++) {
                jQuery("#" + x).toggleClass("filled-now");
            }
        }
        else {
            for (var x = id + 1; x < beforeID; x++) {
                jQuery("#" + x).toggleClass("filled-now");
            }
        }
    }
    else if (beforeIDs[1] == ids[1]) {
        if (beforeIDs[0] < ids[0]) {
            for (var x = beforeID + 10; x < id; x=x+10) {
                jQuery("#" + x).toggleClass("filled-now");
            }
        }
        else {
            for (var x = id + 10; x < beforeID; x=x+10) {
                jQuery("#" + x).toggleClass("filled-now");
            }
        }
    }
    else {
        jQuery("#" + beforeID).toggleClass('filled-now');
    }

    click++;
    if (click > 2 && click % 2 != 0) {
        for (var y = 1; y < 7; y++) {
            for (var x = 1; x < 7; x++) {
                if (jQuery("#" + x.toString() + y.toString()).hasClass("filled-now")) {
                    jQuery("#" + x.toString() + y.toString()).toggleClass("filled-now");
                }
            }
        }
    }

    jQuery("#" + id).toggleClass('filled-now');
    beforeIDs = ids;
    return false;
}