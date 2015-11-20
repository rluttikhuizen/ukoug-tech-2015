package nl.eproseed.ukoug.tech15.soap.service;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.jws.WebService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.eproseed.ukoug.tech15.soap.entity.Attendance;
import nl.eproseed.ukoug.tech15.soap.entity.Attendee;
import nl.eproseed.ukoug.tech15.soap.entity.EvaluationSummary;
import nl.eproseed.ukoug.tech15.soap.entity.Presentation;
import nl.eproseed.ukoug.tech15.soap.entity.Speaker;
import nl.eproseed.ukoug.tech15.soap.enumeration.EvaluationEnum;
import nl.eproseed.ukoug.tech15.soap.exception.ConferenceException;

@Stateless(name = "ConferenceService", mappedName = "ConferenceService")
@WebService(serviceName = "ConferenceSoapWebService", portName = "ConferenceSoapWebServicePort",
            endpointInterface = "nl.eproseed.ukoug.tech15.soap.service.ConferenceSoapWebService")
public class ConferenceServiceBean implements ConferenceService, ConferenceServiceLocal {

    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "ConferenceSoapWebService")
    private EntityManager em;

    public ConferenceServiceBean() {
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    private Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {

        Query query = em.createQuery(jpqlStmt);

        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }

        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }

        return query.getResultList();
    }

    private <T> T persistEntity(T entity) {

        em.persist(entity);
        return entity;
    }

    private <T> T mergeEntity(T entity) {

        return em.merge(entity);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Speaker> getSpeakers() {

        List<Speaker> speakers = em.createNamedQuery("Speaker.findAll", Speaker.class).getResultList();

        // Nulling detail information that is unnecessary for this operation. Better approach would be to have
        // separate entities to return in Web Service instead of using JPA entities directly
        for (Speaker speaker : speakers) {
            speaker.setPresentationList(null);
        }

        return speakers;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public Speaker getSpeakerById(BigDecimal speakerId) {

        Speaker speaker =
            em.createNamedQuery("Speaker.findById", Speaker.class).setParameter("id", speakerId).getSingleResult();

        // Nulling detail information that is unnecessary for this operation. Better approach would be to have
        // separate entities to return in Web Service instead of using JPA entities directly
        for (Presentation presentation : speaker.getPresentationList()) {
            presentation.setAttendanceList(null);
        }

        return speaker;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Attendee> getAttendees() {

        List<Attendee> attendees = em.createNamedQuery("Attendee.findAll", Attendee.class).getResultList();

        // Nulling detail information that is unnecessary for this operation. Better approach would be to have
        // separate entities to return in Web Service instead of using JPA entities directly
        for (Attendee attendee : attendees) {
            attendee.setAttendanceList(null);
        }

        return attendees;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public Attendee getAttendeeById(BigDecimal attendeeId) {

        Attendee attendee = null;

        try {

            attendee =
                em.createNamedQuery("Attendee.findById", Attendee.class).setParameter("id",
                                                                                      attendeeId).getSingleResult();
        } catch (NoResultException e) {

            throw new ConferenceException("No attendee found for attendee id " + attendeeId);
        }

        // Nulling detail information that is unnecessary for this operation. Better approach would be to have
        // separate entities to return in Web Service instead of using JPA entities directly
        for (Attendance attendance : attendee.getAttendanceList()) {

            attendance.getPresentation().getSpeaker().setCompany(null);
            attendance.getPresentation().getSpeaker().setFirstname(null);
            attendance.getPresentation().getSpeaker().setLastname(null);
            attendance.getPresentation().getSpeaker().setSpeakerBio(null);
        }

        return attendee;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public Attendee getAttendeeByUsername(String username) {

        Attendee attendee = null;

        try {

            attendee =
                em.createNamedQuery("Attendee.findByUsername", Attendee.class).setParameter("username",
                                                                                            username).getSingleResult();
        } catch (NoResultException e) {

            throw new ConferenceException("No attendee found for attendee username " + username);
        }

        // Nulling detail information that is unnecessary for this operation. Better approach would be to have
        // separate entities to return in Web Service instead of using JPA entities directly
        for (Attendance attendance : attendee.getAttendanceList()) {

            attendance.getPresentation().getSpeaker().setCompany(null);
            attendance.getPresentation().getSpeaker().setFirstname(null);
            attendance.getPresentation().getSpeaker().setLastname(null);
            attendance.getPresentation().getSpeaker().setSpeakerBio(null);
        }

        return attendee;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Presentation> getPresentations() {

        List<Presentation> presentations =
            em.createNamedQuery("Presentation.findAll", Presentation.class).getResultList();

        // Nulling detail information that is unnecessary for this operation. Better approach would be to have
        // separate entities to return in Web Service instead of using JPA entities directly
        for (Presentation presentation : presentations) {

            presentation.setAttendanceList(null);
            presentation.setPresentationAbstract(null);
            presentation.getSpeaker().setCompany(null);
            presentation.getSpeaker().setFirstname(null);
            presentation.getSpeaker().setLastname(null);
            presentation.getSpeaker().setSpeakerBio(null);
        }

        return presentations;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public Presentation getPresentationById(BigDecimal presentationId) {

        Presentation presentation = null;

        try {

            presentation =
                em.createNamedQuery("Presentation.findById", Presentation.class).setParameter("id",
                                                                                              presentationId).getSingleResult();
        } catch (NoResultException e) {

            throw new ConferenceException("No presentation found for presentation id " + presentationId);
        }

        return presentation;
    }

    @Override
    public Attendance createAttendance(BigDecimal attendeeId, BigDecimal presentationId) {

        // Verify attendee
        if (attendeeId == null) {
            throw new ConferenceException("Attendee id cannot be null");
        }

        Attendee attendee = getAttendeeById(attendeeId);

        // Verify presentation
        if (presentationId == null) {
            throw new ConferenceException("Presentation id cannot be null");
        }

        Presentation presentation = getPresentationById(presentationId);

        // Create attendance
        Attendance attendance = new Attendance();

        attendance.setAttendee(attendee);
        attendance.setPresentation(presentation);
        attendance.setStatus("REGISTERED");

        // Persist attendance
        attendance = persistEntity(attendance);

        // Nulling detail information that is unnecessary for this operation. Better approach would be to have
        // separate entities to return in Web Service instead of using JPA entities directly
        attendance.getAttendee().setAttendanceList(null);
        attendance.getPresentation().setAttendanceList(null);

        return attendance;
    }

    @Override
    public Attendance updateAttendance(BigDecimal attendanceId, String status, String evaluation) {

        // Verify attendee
        if (attendanceId == null) {
            throw new ConferenceException("Attendance id cannot be null");
        }

        Attendance attendance = getAttendanceById(attendanceId);

        if (status != null) {
            attendance.setStatus(status);
        }

        if (evaluation != null) {
            attendance.setEvaluation(EvaluationEnum.valueOf(evaluation).name());
            attendance.setEvaluationTimestamp(new Date());
        }

        // Update attendance
        attendance = mergeEntity(attendance);

        // Nulling detail information that is unnecessary for this operation. Better approach would be to have
        // separate entities to return in Web Service instead of using JPA entities directly
        attendance.getAttendee().setAttendanceList(null);
        attendance.getPresentation().setAttendanceList(null);

        return attendance;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public Attendance getAttendanceById(BigDecimal attendanceId) {

        Attendance attendance = null;

        try {

            attendance =
                em.createNamedQuery("Attendance.findById", Attendance.class).setParameter("id",
                                                                                          attendanceId).getSingleResult();
        } catch (NoResultException e) {

            throw new ConferenceException("No attendance found for attendance id " + attendanceId);
        }

        return attendance;
    }

    @Override
    public EvaluationSummary getEvaluationSummary(BigDecimal presentationId) {

        Presentation presentation = getPresentationById(presentationId);

        EvaluationSummary summary = new EvaluationSummary();

        summary.setPresentationId(presentation.getId());
        summary.setPresentationTitle(presentation.getTitle());

        summary.setSpeakerId(presentation.getSpeaker().getId());
        summary.setSpeakerFirstname(presentation.getSpeaker().getFirstname());
        summary.setSpeakerLastname(presentation.getSpeaker().getLastname());

        summary.setNumberOfAttendees(presentation.getAttendanceList().size());

        int numberOfPositiveEvaluations = 0;
        int numberOfNeutralEvaluations = 0;
        int numberOfNegativeEvaluations = 0;

        for (Attendance attendance : presentation.getAttendanceList()) {

            if (attendance.containsEvaluation()) {

                if (attendance.isPositiveEvaluation()) {
                    numberOfPositiveEvaluations++;
                } else if (attendance.isNegativeEvaluation()) {
                    numberOfNegativeEvaluations++;
                } else if (attendance.isNeutralEvaluation()) {
                    numberOfNeutralEvaluations++;
                } else {
                    throw new ConferenceException("Unknown evaluation value: " + attendance.getEvaluation());
                }
            }
        }

        summary.setNumberOfPositiveEvaluations(numberOfPositiveEvaluations);
        summary.setNumberOfNeutralEvaluations(numberOfNeutralEvaluations);
        summary.setNumberOfNegativeEvaluations(numberOfNegativeEvaluations);

        return summary;
    }
}
