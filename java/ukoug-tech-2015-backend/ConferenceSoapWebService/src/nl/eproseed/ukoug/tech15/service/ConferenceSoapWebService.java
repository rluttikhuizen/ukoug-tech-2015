package nl.eproseed.ukoug.tech15.service;

import java.math.BigDecimal;

import java.rmi.Remote;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import nl.eproseed.ukoug.tech15.entity.Attendee;
import nl.eproseed.ukoug.tech15.entity.Presentation;
import nl.eproseed.ukoug.tech15.entity.Speaker;

@WebService(name = "ConferenceSoapWebService", targetNamespace = "http://eproseed.nl/ukoug/tech15")
public interface ConferenceSoapWebService extends Remote {

    @WebMethod
    @WebResult(name = "Speakers")
    public List<Speaker> getSpeakers();

    @WebMethod
    @WebResult(name = "Speaker")
    public Speaker getSpeakerById(@WebParam(name = "speakerId") BigDecimal speakerId);

    @WebMethod
    @WebResult(name = "Presentations")
    public List<Presentation> getPresentations();

    @WebMethod
    @WebResult(name = "Presentation")
    public Presentation getPresentationById(@WebParam(name = "presentationId") BigDecimal presentationId);

    @WebMethod
    @WebResult(name = "Attendees")
    public List<Attendee> getAttendees();

    @WebMethod
    @WebResult(name = "Attendee")
    public Attendee getAttendeeById(@WebParam(name = "attendeeId") BigDecimal attendeeId);
}
