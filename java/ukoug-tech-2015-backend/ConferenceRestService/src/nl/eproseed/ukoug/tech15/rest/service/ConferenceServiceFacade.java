package nl.eproseed.ukoug.tech15.rest.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.eproseed.ukoug.tech15.rest.entity.Attendee;
import nl.eproseed.ukoug.tech15.rest.entity.Presentation;
import nl.eproseed.ukoug.tech15.rest.entity.Speaker;

@Path("conference")
@Consumes(value = {"application/json", "aplication/xml"})
@Produces(value = {"application/json", "aplication/xml"})
public class ConferenceServiceFacade implements ConferenceService {
    
    private final EntityManager em;

    public ConferenceServiceFacade() {
        
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConferenceRestService");
        em = emf.createEntityManager();
    }

    /**
     * All changes that have been made to the managed entities in the
     * persistence context are applied to the database and committed.
     */
    private void commitTransaction() {
       
        final EntityTransaction entityTransaction = em.getTransaction();
       
        if (!entityTransaction.isActive()) {
            entityTransaction.begin();
        }
        entityTransaction.commit();
    }

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
        commitTransaction();
        
        return entity;
    }

    private <T> T mergeEntity(T entity) {
        
        entity = em.merge(entity);
        commitTransaction();
        
        return entity;
    }

    @Override
    @GET
    @Path("/presentations")
    public List<Presentation> getPresentations() {
        
        List<Presentation> presentations = em.createNamedQuery("Presentation.findAll", Presentation.class).getResultList();
        
        return presentations;
    }
    
    @Override
    @GET
    @Path("/attendees")
    public List<Attendee> getAttendees() {
        
        return em.createNamedQuery("Attendee.findAll", Attendee.class).getResultList();
    }

    @Override
    @GET
    @Path("/speakers")
    public List<Speaker> getSpeakers() {
        
        return em.createNamedQuery("Speaker.findAll", Speaker.class).getResultList();
    }
}
