package nl.eproseed.ukoug.tech15.rest.service;

import java.util.List;

import nl.eproseed.ukoug.tech15.rest.entity.Attendee;
import nl.eproseed.ukoug.tech15.rest.entity.Presentation;
import nl.eproseed.ukoug.tech15.rest.entity.Speaker;

public interface ConferenceService {
    
    List<Presentation> getPresentations();

    List<Attendee> getAttendees();

    List<Speaker> getSpeakers();
}
