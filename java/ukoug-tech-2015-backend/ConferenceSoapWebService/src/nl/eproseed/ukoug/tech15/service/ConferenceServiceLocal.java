package nl.eproseed.ukoug.tech15.service;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import nl.eproseed.ukoug.tech15.entity.Attendance;
import nl.eproseed.ukoug.tech15.entity.Attendee;
import nl.eproseed.ukoug.tech15.entity.Presentation;
import nl.eproseed.ukoug.tech15.entity.Speaker;

@Local
public interface ConferenceServiceLocal {
  
    List<Speaker> getSpeakers();
    Speaker getSpeakerById(BigDecimal speakerId);

    List<Attendee> getAttendees();
    Attendee getAttendeeById(BigDecimal attendeeId);
    Attendee getAttendeeByUsername(String username);
    
    List<Presentation> getPresentations();
    Presentation getPresentationById(BigDecimal presentationId);
    
    Attendance getAttendanceById(BigDecimal attendanceId);
    Attendance createAttendance(BigDecimal attendeeId, BigDecimal presentationId);
    Attendance updateAttendance(BigDecimal attendanceId, String status, String evaluation);
}
