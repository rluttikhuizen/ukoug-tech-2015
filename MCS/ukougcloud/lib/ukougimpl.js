var async = require('async');
var transform = require("./transformations");

exports.getSpeakers = function (req, res) {
    var handler = function (error, response, body) {
        var responseMessage = body;
        if (error) {
            responseMessage = error.message;
        } else if (parseInt(response.statusCode) === 200) {
            var json = JSON.parse(body);
            console.info("SOAP body" + body);
            var resultArray = json.Body.getSpeakersResponse.speakers;
            console.info("SOAP spekers" + JSON.stringify(resultArray));
            var speakers = resultArray.map(transform.speakersSummarySOAP2REST);
            responseMessage = JSON.stringify(speakers);
        }
        res.status(response.statusCode).send(responseMessage);
        res.end();
    };
    var optionsList = {uri: '/mobile/connector/ukougdemo2/getSpeakers'};
    optionsList.headers = {'content-type': 'application/json;charset=UTF-8'};
    var outgoingMessage = {Header: null, Body: {"getSpeakers": null}};
    optionsList.body = JSON.stringify(outgoingMessage);
    var r = req.oracleMobile.rest.post(optionsList, handler);
};

exports.getSessions = function (req, res) {
    var sdk = req.oracleMobile;
    var agg = {};
    var functions = [
        wrap(getSessionsInfo, req, agg),
        wrap(addSpeakerInfoToSessions, req, agg)
    ]
    async.series(functions, function (error, result) {
        if (error && error != undefined) {
            res.send(500, error);
        } else {
            res.send(200, result[1]);
        }
        res.end();
    });
};


var getSessionsInfo = function (body, agg, sdk, callback) {
    var handler = function (error, response, body) {
        var responseMessage = body;
        if (error) {
            responseMessage = error.message;
        } else if (parseInt(response.statusCode) === 200) {
            var json = JSON.parse(body);
            console.info("SOAP body" + body);
            var resultArray = json.Body.getPresentationsResponse.presentations;
            console.info("SOAP sessions" + JSON.stringify(resultArray));
//      var speakers = resultArray.map(transform.speakersSummarySOAP2REST);
            agg.sessions = resultArray;
            responseMessage = JSON.stringify(agg.sessions);
            callback(null, responseMessage);
        } else {
            // error stop normal processing
            callback(responseMessage, null);
        }
    };

    var optionsList = {uri: '/mobile/connector/ukougdemo2/getPresentations'};
    optionsList.headers = {'content-type': 'application/json;charset=UTF-8'};
    var outgoingMessage = {Header: null, Body: {"getPresentations": null}};
    optionsList.body = JSON.stringify(outgoingMessage);
    sdk.rest.post(optionsList, handler);
};

var addSpeakerInfoToSessions = function (body, agg, sdk, callback) {
    var handler = function (error, response, body) {
        var responseMessage = body;
        if (error) {
            responseMessage = error.message;
        } else if (parseInt(response.statusCode) === 200) {
            var json = JSON.parse(body);
            var speakers = json.Body.getSpeakersResponse.speakers;

            agg.sessions.forEach(function (session) {
                var speakerId = session.speaker.id;
                speakers.forEach(function (speaker) {
                    if (speaker.id == speakerId) {
                        session.speakerId = speakerId;
                        session.speakerName = speaker.firstname + ' ' + speaker.lastname + ' - ' + speaker.company;
                        delete session['speaker'];
//                break;
                    }
                });
            });
            responseMessage = JSON.stringify(agg.sessions);
            callback(null, responseMessage);
        } else {
            // error stop normal processing
            callback(responseMessage, null);
        }
    };

    var optionsList = {uri: '/mobile/connector/ukougdemo2/getSpeakers'};
    optionsList.headers = {'content-type': 'application/json;charset=UTF-8'};
    var outgoingMessage = {Header: null, Body: {"getSpeakers": null}};
    optionsList.body = JSON.stringify(outgoingMessage);
    sdk.rest.post(optionsList, handler);

};

exports.getSessionById = function (req, res) {
    var sdk = req.oracleMobile;
    var agg = {};
    agg.sessionId = req.params.id;
    var functions = [
        wrap(getSessionInfo, req, agg),
        wrap(addSpeakerInfoToSession, req, agg)
    ]
    async.series(functions, function (error, result) {
        if (error && error != undefined) {
            res.send(500, error);
        } else {
            res.send(200, result[1]);
        }
        res.end();
    });
};

var getSessionInfo = function (body, agg, sdk, callback) {
    var handler = function (error, response, body) {
        var responseMessage = body;
        if (error) {
            responseMessage = error.message;
        } else if (parseInt(response.statusCode) === 200) {
            var json = JSON.parse(body);
            console.info("SOAP body" + body);
            agg.session = json.Body.getPresentationByIdResponse.presentation;

            agg.session.abstract = agg.session.presentationAbstract;
            delete agg.session.presentationAbstract;
            if (agg.session.attendance) {
                agg.session.attendance.forEach(function (attendance) {
                    attendance.attendeeId = attendance.attendee.id;
                    attendance.name = attendance.attendee.name;
                    attendance.username = attendance.attendee.username;
                    attendance.company = attendance.attendee.company;
                    delete attendance['attendee'];
                });
                // rename attendance to attendances
                agg.session.attendances = agg.session.attendance;
                delete agg.session['attendance'];
            }
            responseMessage = JSON.stringify(agg.session);
            callback(null, responseMessage);
        } else {
            // error stop normal processing
            callback(responseMessage, null);
        }
    };

    var optionsList = {uri: '/mobile/connector/ukougdemo2/getPresentationById'};
    optionsList.headers = {'content-type': 'application/json;charset=UTF-8'};
    var outgoingMessage = {Header: null, Body: {"getPresentationById": {"presentationId": agg.sessionId}}};
    optionsList.body = JSON.stringify(outgoingMessage);
    sdk.rest.post(optionsList, handler);
};

var addSpeakerInfoToSession = function (body, agg, sdk, callback) {
    var handler = function (error, response, body) {
        var responseMessage = body;
        if (error) {
            responseMessage = error.message;
        } else if (parseInt(response.statusCode) === 200) {
            var json = JSON.parse(body);
            var speaker = json.Body.getSpeakerByIdResponse.speaker;
            agg.session.speakerId = speaker.id;
            agg.session.speakerName = speaker.firstname + ' ' + speaker.lastname + ' - ' + speaker.company;
            delete agg.session['speaker'];
            responseMessage = JSON.stringify(agg.session);
            callback(null, responseMessage);
        } else {
            // error stop normal processing
            callback(responseMessage, null);
        }
    };

    var optionsList = {uri: '/mobile/connector/ukougdemo2/getSpeakerById'};
    optionsList.headers = {'content-type': 'application/json;charset=UTF-8'};
    var outgoingMessage = {Header: null, Body: {"getSpeakerById": {"speakerId": agg.session.speaker.id}}};
    optionsList.body = JSON.stringify(outgoingMessage);
    sdk.rest.post(optionsList, handler);
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

exports.createAttendance = function (req, res) {
    var handler = function (error, response, body) {
        var responseMessage = body;
        if (error) {
            responseMessage = error.message;
        } else if (parseInt(response.statusCode) === 200) {
            var json = JSON.parse(body);
            responseMessage = JSON.stringify(body);
        }
        res.status(response.statusCode).send(responseMessage);
        res.end();
    };
    console.info("POST BODY: " + JSON.stringify(req.body));
    var sessionId = req.body.sessionId;
    var attendeeId = req.body.attendeeId;
    var optionsList = {uri: '/mobile/connector/ukougdemo2/createAttendance'};
    optionsList.headers = {'content-type': 'application/json;charset=UTF-8'};
    var outgoingMessage = {Header: null, Body: {"createAttendance": {
                "attendeeId": attendeeId,
                "presentationId": sessionId
            }}};
    console.info("POST ATT: " + JSON.stringify(outgoingMessage));
    optionsList.body = JSON.stringify(outgoingMessage);
    req.oracleMobile.rest.post(optionsList, handler);
};

exports.updateAttendance = function (req, res) {
    var handler = function (error, response, body) {
        var responseMessage = body;
        if (error) {
            responseMessage = error.message;
        } else if (parseInt(response.statusCode) === 200) {
            var json = JSON.parse(body);
            responseMessage = JSON.stringify(body);
        }
        res.status(response.statusCode).send(responseMessage);
        res.end();
    };
    console.info("PUT BODY: " + JSON.stringify(req.body));
// Evaluation --> POSITIVE, NEUTRAL, NEGATIVE
// Attendance status --> REGISTERED, REGISTERED_ATTENDED, NOT_REGISTERED_ATTENDED, UNREGISTERED      
    var optionsList = {uri: '/mobile/connector/ukougdemo2/updateAttendance'};
    optionsList.headers = {'content-type': 'application/json;charset=UTF-8'};
    var outgoingMessage = {Header: null, Body: {"updateAttendance": {
                "attendanceId": req.body.id,
                "status": req.body.present ? 'REGISTERED_ATTENDED' : 'REGISTERED',
                "evaluation": req.body.rating
            }}};
    console.info("PUT ATT: " + JSON.stringify(outgoingMessage));
    optionsList.body = JSON.stringify(outgoingMessage);
    req.oracleMobile.rest.post(optionsList, handler);
};


exports.rescheduleSession = function (req, res) {
    console.info("Reschedule request body: "+JSON.stringify(req.body));
    var sdkInstance = req.oracleMobile;
    var sessionId = req.body.id;
    var day = req.body.sessionDate;
    var startTime = req.body.startTime;
    var notification = {message: 'Session ' + sessionId + ' has been reschuled to ' + startTime};
//      var notification = {message : {message: 'New fall risk in building ' + building+' floor '+floor+' room '+room
//                         ,'building':building, 'floor':floor, 'room':room}};

    // Handler for the request that gets the authorization token.
    // The authorization token is passed in the body.
    var handler = function (error, response, body) {
        if (error) {
            console.error('AppId Error: ' + error);
            res.send(500, error);
        } else {

            // Handler that actually sends the notification.
            var notificationHandler = function (error, response, body) {
                if (error) {
                    console.error('Notification failed for: ' +
                      room);
                    res.send(500, error);

                } else {
                    res.send(200, notification);
                }
            };
            // Note that the authorization token is set
            // in the Authorization header.
            var optionsList = {
                uri: '/mobile/system/notifications/notifications/',
                json: notification,
                headers: {'Authorization': body}
            };

            sdkInstance.rest.post(optionsList, notificationHandler);
        }
    };

    // Request for a token includes the mobile backend name and 
    // version along with the MOBILE_NOTIFICATION_APPID user name,
    // which is the internal user who has permission to send 
    // notifications.
    var request = {
        name: 'UKOUG',
        version: '1.0',
        username: 'MOBILE_NOTIFICATION_APPID'};
    var optionsList = {
        uri: '/mobile/platform/ums/tokens/',
        json: request};

    // Get token for notification.
    // The handler we are passing in not only extracts the token 
    // but triggers the call to send the notification.
    sdkInstance.rest.post(optionsList, handler);
    }

// async modules expect functions in the format of function(callback); 
// This functions wraps the function to have ths format
// and the body of this function executes the original function you passed to wrap
    function wrap(functionToWrap, req, agg) {
        return function (callback) {
            return functionToWrap(req.body, agg, req.oracleMobile, callback);
        }
}
;


