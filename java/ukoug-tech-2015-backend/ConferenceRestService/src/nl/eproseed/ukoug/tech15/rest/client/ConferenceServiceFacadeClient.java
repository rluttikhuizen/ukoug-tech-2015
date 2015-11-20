package nl.eproseed.ukoug.tech15.rest.client;

import java.util.List;

import nl.eproseed.ukoug.tech15.rest.entity.Attendance;
import nl.eproseed.ukoug.tech15.rest.entity.Attendee;
import nl.eproseed.ukoug.tech15.rest.entity.Presentation;
import nl.eproseed.ukoug.tech15.rest.entity.Speaker;
import nl.eproseed.ukoug.tech15.rest.service.ConferenceServiceFacade;

public class ConferenceServiceFacadeClient {
    
    public static void main(String[] args) {
        
        try {
            final ConferenceServiceFacade conferenceServiceFacade = new ConferenceServiceFacade();
            for (Presentation presentation : (List<Presentation>) conferenceServiceFacade.getPresentations()) {
                printPresentation(presentation);
            }
            for (Attendee attendee : (List<Attendee>) conferenceServiceFacade.getAttendees()) {
                printAttendee(attendee);
            }
            for (Speaker speaker : (List<Speaker>) conferenceServiceFacade.getSpeakers()) {
                printSpeaker(speaker);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printPresentation(Presentation presentation) {
        
        System.out.println("presentationabstract = " + presentation.getPresentationAbstract());
        System.out.println("contentLevel = " + presentation.getContentLevel());
        System.out.println("coPresenter = " + presentation.getCoPresenter());
        System.out.println("coPresenterEmail = " + presentation.getCoPresenterEmail());
        System.out.println("day = " + presentation.getDay());
        System.out.println("endTime = " + presentation.getEndTime());
        System.out.println("experience = " + presentation.getExperience());
        System.out.println("hall = " + presentation.getHall());
        //System.out.println("id = " + presentation.getId());
        System.out.println("length = " + presentation.getLength());
        System.out.println("preferredLength = " + presentation.getPreferredLength());
        System.out.println("presentationFormat = " + presentation.getPresentationFormat());
        System.out.println("presentationStatus = " + presentation.getPresentationStatus());
        System.out.println("sessionDate = " + presentation.getSessionDate());
        System.out.println("startTime = " + presentation.getStartTime());
        System.out.println("stream = " + presentation.getStream());
        System.out.println("title = " + presentation.getTitle());
        System.out.println("topic = " + presentation.getTopic());
        System.out.println("track = " + presentation.getTrack());
//        System.out.println("speaker = " + presentation.getSpeaker());
//        System.out.println("attendanceList = " + presentation.getAttendanceList());
    }

    private static void printAttendance(Attendance attendance) {
        
        System.out.println("evaluation = " + attendance.getEvaluation());
        System.out.println("evaluationTimestamp = " + attendance.getEvaluationTimestamp());
        System.out.println("id = " + attendance.getId());
        System.out.println("status = " + attendance.getStatus());
        //System.out.println("attendee = " + attendance.getAttendee());
        //System.out.println("presentation = " + attendance.getPresentation());
    }

    private static void printAttendee(Attendee attendee) {
        
        System.out.println("company = " + attendee.getCompany());
        System.out.println("id = " + attendee.getId());
        System.out.println("name = " + attendee.getName());
        System.out.println("username = " + attendee.getUsername());
        //System.out.println("attendanceList = " + attendee.getAttendanceList());
    }

    private static void printSpeaker(Speaker speaker) {
        
        System.out.println("company = " + speaker.getCompany());
        System.out.println("firstname = " + speaker.getFirstname());
        System.out.println("id = " + speaker.getId());
        System.out.println("lastname = " + speaker.getLastname());
        System.out.println("speakerBio = " + speaker.getSpeakerBio());
        //System.out.println("presentationList = " + speaker.getPresentationList());
    }
}
