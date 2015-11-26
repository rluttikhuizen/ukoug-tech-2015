package nl.eproseed.ukoug.tech15.toplink.service;

import java.util.List;

import nl.eproseed.ukoug.tech15.toplink.entity.Attendance;
import nl.eproseed.ukoug.tech15.toplink.entity.Attendee;
import nl.eproseed.ukoug.tech15.toplink.entity.Presentation;
import nl.eproseed.ukoug.tech15.toplink.entity.Speaker;

public class ConferenceServiceFacadeClient {
    public static void main(String[] args) {
        try {
            final ConferenceServiceFacade conferenceServiceFacade = new ConferenceServiceFacade();
            for (Attendance attendance : (List<Attendance>) conferenceServiceFacade.getAttendanceFindAll()) {
                printAttendance(attendance);
            }
            for (Speaker speaker : (List<Speaker>) conferenceServiceFacade.getSpeakerFindAll()) {
                printSpeaker(speaker);
            }
            for (Attendee attendee : (List<Attendee>) conferenceServiceFacade.getAttendeeFindAll()) {
                printAttendee(attendee);
            }
            for (Presentation presentation : (List<Presentation>) conferenceServiceFacade.getPresentationFindAll()) {
                printPresentation(presentation);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printAttendance(Attendance attendance) {
        System.out.println("evaluation = " + attendance.getEvaluation());
        System.out.println("evaluationTimestamp = " + attendance.getEvaluationTimestamp());
        System.out.println("id = " + attendance.getId());
        System.out.println("status = " + attendance.getStatus());
        System.out.println("attendee = " + attendance.getAttendee());
        System.out.println("presentation = " + attendance.getPresentation());
    }

    private static void printSpeaker(Speaker speaker) {
        System.out.println("company = " + speaker.getCompany());
        System.out.println("firstname = " + speaker.getFirstname());
        System.out.println("id = " + speaker.getId());
        System.out.println("lastname = " + speaker.getLastname());
        System.out.println("speakerBio = " + speaker.getSpeakerBio());
    }

    private static void printAttendee(Attendee attendee) {
        System.out.println("company = " + attendee.getCompany());
        System.out.println("id = " + attendee.getId());
        System.out.println("name = " + attendee.getName());
        System.out.println("username = " + attendee.getUsername());
        System.out.println("attendanceList = " + attendee.getAttendances());
    }

    private static void printPresentation(Presentation presentation) {
        System.out.println("presentationAbstract = " + presentation.getAbstract_());
        System.out.println("contentLevel = " + presentation.getContentLevel());
        System.out.println("coPresenter = " + presentation.getCoPresenter());
        System.out.println("coPresenterEmail = " + presentation.getCoPresenterEmail());
        System.out.println("day = " + presentation.getDay());
        System.out.println("endTime = " + presentation.getEndTime());
        System.out.println("experience = " + presentation.getExperience());
        System.out.println("hall = " + presentation.getHall());
        System.out.println("id = " + presentation.getId());
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
        System.out.println("speaker = " + presentation.getSpeaker());
    }
}
