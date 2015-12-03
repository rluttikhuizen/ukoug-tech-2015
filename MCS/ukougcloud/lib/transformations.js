exports.speakersSummarySOAP2REST = function (speaker) {
    removeNullAttrs(speaker);
    var speakerRest = {id: speaker.id, firstname: speaker.firstname, lastname: speaker.lastname, company: speaker.company, bio: speaker.speakerBio};
    return speakerRest;
};

function removeNullAttrs(obj) {
    for (var k in obj)
    {
        var value = obj[k];
        if (typeof value === "object" && value['@nil'] === 'true') {
            delete obj[k];
        }
        // recursive call if an object
        else if (typeof value === "object") {
            removeNullAttrs(value);
        }
    }
}
