package nl.eproseed.ukoug.tech15.service;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.jws.WebService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.eproseed.ukoug.tech15.entity.Attendance;
import nl.eproseed.ukoug.tech15.entity.Attendee;
import nl.eproseed.ukoug.tech15.entity.Presentation;
import nl.eproseed.ukoug.tech15.entity.Speaker;

@Stateless(name = "ConferenceService", mappedName = "ConferenceService")
@WebService(serviceName = "ConferenceSoapWebService", portName = "ConferenceSoapWebServicePort",
            endpointInterface = "nl.eproseed.ukoug.tech15.service.ConferenceSoapWebService")
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

    /** <code>select o from Speaker o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Speaker> getSpeakers() {

        List<Speaker> speakers = em.createNamedQuery("Speaker.findAll", Speaker.class).getResultList();

        // Nulling detail information that is unnecessary for this operation. Better approach would be to have
        // separate entities to returnn in Web Service instead of using JPA entities directly
        for (Speaker speaker : speakers) {
            speaker.setPresentationList(null);
        }

        return speakers;
    }

    /** <code>select o from Speaker o where o.id = :id</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public Speaker getSpeakerById(BigDecimal speakerId) {

        Speaker speaker = em.createNamedQuery("Speaker.findById", Speaker.class).setParameter("id", speakerId).getSingleResult();
        
        // Nulling detail information that is unnecessary for this operation. Better approach would be to have
        // separate entities to returnn in Web Service instead of using JPA entities directly
        for (Presentation presentation : speaker.getPresentationList()) {
            presentation.setAttendanceList(null);
        }
        
        return speaker;
    }

    /** <code>select o from Attendee o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Attendee> getAttendees() {

        List<Attendee> attendees = em.createNamedQuery("Attendee.findAll", Attendee.class).getResultList();

        // Nulling detail information that is unnecessary for this operation. Better approach would be to have
        // separate entities to returnn in Web Service instead of using JPA entities directly
        for (Attendee attendee : attendees) {
            attendee.setAttendanceList(null);
        }

        return attendees;
    }

    /** <code>select o from Attendee o where o.id = :id</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public Attendee getAttendeeById(BigDecimal attendeeId) {

        Attendee attendee = em.createNamedQuery("Attendee.findById", Attendee.class).setParameter("id",
                                                                                     attendeeId).getSingleResult();
        
        // Nulling detail information that is unnecessary for this operation. Better approach would be to have
        // separate entities to returnn in Web Service instead of using JPA entities directly
        for (Attendance attendance : attendee.getAttendanceList()) {
            
            attendance.getPresentation().getSpeaker().setCompany(null);
            attendance.getPresentation().getSpeaker().setFirstname(null);
            attendance.getPresentation().getSpeaker().setLastname(null);
            attendance.getPresentation().getSpeaker().setSpeakerBio(null);
        }
        
        return attendee;
    }

    /** <code>select o from Presentation o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Presentation> getPresentations() {

        List<Presentation> presentations =
            em.createNamedQuery("Presentation.findAll", Presentation.class).getResultList();

        // Nulling detail information that is unnecessary for this operation. Better approach would be to have
        // separate entities to returnn in Web Service instead of using JPA entities directly
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

    /** <code>select o from Presentation o where o.id = :id</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public Presentation getPresentationById(BigDecimal presentationId) {

        return em.createNamedQuery("Presentation.findById", Presentation.class).setParameter("id",
                                                                                             presentationId).getSingleResult();
    }
}
