exports.speakersSummarySOAP2REST = function (speaker) {
    removeNullAttrs(speaker);
    var speakerRest = {id: speaker.id, firstname: speaker.firstname, lastname: speaker.lastname, company: speaker.company, bio: speaker.speakerBio};
    return speakerRest;
};

exports.attendancesSOAP2REST = function (att) {
    removeNullAttrs(att);
    var attRest = {id: att.id, userId: att.attendee.id, sessionId:att.presentation.id, status:att.status,
       title: att.presentation.title, presenter: att.presentation.speaker.firstname+' '+att.presentation.speaker.lastname,
       date: att.presentation.sessionDate.substr(0,10), startTime: att.presentation.startTime.substr(0,5), hall: att.presentation.hall};
    return attRest;
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
