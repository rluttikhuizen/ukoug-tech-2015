package nl.eproseed.ukoug.tech15.soap.service;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import nl.eproseed.ukoug.tech15.soap.entity.Attendance;
import nl.eproseed.ukoug.tech15.soap.entity.Attendee;
import nl.eproseed.ukoug.tech15.soap.entity.EvaluationSummary;
import nl.eproseed.ukoug.tech15.soap.entity.Presentation;
import nl.eproseed.ukoug.tech15.soap.entity.Speaker;

@Local
public interface ConferenceServiceLocal {
  
    List<Speaker> getSpeakers();
    Speaker getSpeakerById(BigDecimal speakerId);

    List<Attendee> getAttendees();
    Attendee getAttendeeById(BigDecimal attendeeId);
    Attendee getAttendeeByUsername(String username);
    
    List<Presentation> getPresentations();
    Presentation getPresentationById(BigDecimal presentationId);
    Presentation reschedulePresentation(BigDecimal presentationId, String day, Date sessionDate, String startTime, String endTime, BigDecimal length, String hall);
    
    Attendance getAttendanceById(BigDecimal attendanceId);
    Attendance createAttendance(BigDecimal attendeeId, BigDecimal presentationId);
    Attendance updateAttendance(BigDecimal attendanceId, String status, String evaluation);
    List<Attendance> getAttendancesForUserByUsername(String username);
    
    EvaluationSummary getEvaluationSummary(BigDecimal presentationId);
}
