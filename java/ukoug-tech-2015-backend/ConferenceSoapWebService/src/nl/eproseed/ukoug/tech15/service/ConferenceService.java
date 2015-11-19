package nl.eproseed.ukoug.tech15.service;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import nl.eproseed.ukoug.tech15.entity.Attendee;
import nl.eproseed.ukoug.tech15.entity.Presentation;
import nl.eproseed.ukoug.tech15.entity.Speaker;

@Remote
public interface ConferenceService {

    List<Speaker> getSpeakers();
    Speaker getSpeakerById(BigDecimal speakerId);

    List<Attendee> getAttendees();
    Attendee getAttendeeById(BigDecimal attendeeId);

    List<Presentation> getPresentations();
    Presentation getPresentationById(BigDecimal presentationId);
}
