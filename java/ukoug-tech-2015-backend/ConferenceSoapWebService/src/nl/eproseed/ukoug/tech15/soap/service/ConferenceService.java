package nl.eproseed.ukoug.tech15.soap.service;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import nl.eproseed.ukoug.tech15.soap.entity.Attendance;
import nl.eproseed.ukoug.tech15.soap.entity.Attendee;
import nl.eproseed.ukoug.tech15.soap.entity.EvaluationSummary;
import nl.eproseed.ukoug.tech15.soap.entity.Presentation;
import nl.eproseed.ukoug.tech15.soap.entity.Speaker;

@Remote
public interface ConferenceService {

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
    
    EvaluationSummary getEvaluationSummary(BigDecimal presentationId);
}
