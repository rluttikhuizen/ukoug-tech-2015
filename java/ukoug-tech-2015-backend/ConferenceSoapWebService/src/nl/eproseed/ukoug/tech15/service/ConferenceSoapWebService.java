package nl.eproseed.ukoug.tech15.service;

import java.math.BigDecimal;

import java.rmi.Remote;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import nl.eproseed.ukoug.tech15.entity.Attendance;
import nl.eproseed.ukoug.tech15.entity.Attendee;
import nl.eproseed.ukoug.tech15.entity.Presentation;
import nl.eproseed.ukoug.tech15.entity.Speaker;

@WebService(name = "ConferenceSoapWebService", targetNamespace = "http://eproseed.nl/ukoug/tech15")
public interface ConferenceSoapWebService extends Remote {

    @WebMethod
    @WebResult(name = "speakers")
    public List<Speaker> getSpeakers();

    @WebMethod
    @WebResult(name = "speaker")
    public Speaker getSpeakerById(@WebParam(name = "speakerId") BigDecimal speakerId);

    @WebMethod
    @WebResult(name = "presentations")
    public List<Presentation> getPresentations();

    @WebMethod
    @WebResult(name = "presentation")
    public Presentation getPresentationById(@WebParam(name = "presentationId") BigDecimal presentationId);

    @WebMethod
    @WebResult(name = "attendees")
    public List<Attendee> getAttendees();

    @WebMethod
    @WebResult(name = "attendee")
    public Attendee getAttendeeById(@WebParam(name = "attendeeId") BigDecimal attendeeId);

    @WebMethod
    @WebResult(name = "attendee")
    public Attendee getAttendeeByUsername(@WebParam(name = "username") String username);

    @WebMethod
    @WebResult(name = "attendance")
    public Attendance getAttendanceById(@WebParam(name = "attendanceId") BigDecimal attendanceId);

    @WebMethod
    @WebResult(name = "attendance")
    public Attendance createAttendance(@WebParam(name = "attendeeId") BigDecimal attendeeId,
                                       @WebParam(name = "presentationId") BigDecimal presentationId);

    @WebMethod
    @WebResult(name = "attendance")
    public Attendance updateAttendance(@WebParam(name = "attendanceId") BigDecimal attendanceId,
                                       @WebParam(name = "status") String status,
                                       @WebParam(name = "evaluation") String evaluation);
}
