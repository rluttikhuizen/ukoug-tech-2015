package nl.eproseed.ukoug.tech15.toplink.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import nl.eproseed.ukoug.tech15.toplink.entity.Attendance;
import nl.eproseed.ukoug.tech15.toplink.entity.Attendee;
import nl.eproseed.ukoug.tech15.toplink.entity.Presentation;
import nl.eproseed.ukoug.tech15.toplink.entity.Speaker;

public class ConferenceServiceFacade {
    private final EntityManager em;

    public ConferenceServiceFacade() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConferenceTopLinkRestService");
        em = emf.createEntityManager();
    }

    /**
     * All changes that have been made to the managed entities in the
     * persistence context are applied to the database and committed.
     */
    public void commitTransaction() {
        final EntityTransaction entityTransaction = em.getTransaction();
        if (!entityTransaction.isActive()) {
            entityTransaction.begin();
        }
        entityTransaction.commit();
    }

    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public <T> T persistEntity(T entity) {
        em.persist(entity);
        commitTransaction();
        return entity;
    }

    public <T> T mergeEntity(T entity) {
        entity = em.merge(entity);
        commitTransaction();
        return entity;
    }

    /** <code>select o from Attendance o</code> */
    public List<Attendance> getAttendanceFindAll() {
        return em.createNamedQuery("Attendance.findAll", Attendance.class).getResultList();
    }

    /** <code>select o from Speaker o</code> */
    public List<Speaker> getSpeakerFindAll() {
        return em.createNamedQuery("Speaker.findAll", Speaker.class).getResultList();
    }

    /** <code>select o from Attendee o</code> */
    public List<Attendee> getAttendeeFindAll() {
        return em.createNamedQuery("Attendee.findAll", Attendee.class).getResultList();
    }

    /** <code>select o from Presentation o</code> */
    public List<Presentation> getPresentationFindAll() {
        return em.createNamedQuery("Presentation.findAll", Presentation.class).getResultList();
    }
}
