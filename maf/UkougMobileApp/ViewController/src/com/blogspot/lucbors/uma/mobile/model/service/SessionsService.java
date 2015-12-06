package com.blogspot.lucbors.uma.mobile.model.service;


import java.util.ArrayList;

import java.util.List;

import oracle.ateam.sample.mobile.v2.persistence.util.EntityUtils;
import oracle.ateam.sample.mobile.v2.persistence.service.EntityCRUDService;

import com.blogspot.lucbors.uma.mobile.model.Sessions;


/**
 *  Service class that provides CRUD and custom operations against the sessions data object.
 *  The behavior of this class is driven by the sessions classMappingDescriptor in persistence-mapping.xml.
 *
 *  You can customize and extend this behavior by overriding methods of the EntityCRUDService superclass, and/or
 *  creating a subclass of the local and remote persistence managers as configured in persistence-mapping.xml.
 */
public class SessionsService extends EntityCRUDService<Sessions> {

    /**
     * Default constructor. If autoQuery is set to true in the classMappingDescriptor in persistence-mapping.xml, then
     * the findAll method will be executed and the sessions list will be populated when this constructor is invoked.
     * If you created a data control for this service class, the data control will use this constructor, allowing you to
     * immediately show data in your user interface when accessing the data control for the first time.
     * By default, the findAll method will first query the local SQLite database for any rows and show these immediately in
     * the UI. Then the remote findAll method as configured in persistence-mapping.xml will be executed in the background,
     * and the UI will be automatically refreshed when the remote data have been processed and stored in the local SQLite
     * database.
     * If you want the user to wait until the remote data have been processed and not show local data first, you can set
     * the remoteReadInBackground attribute in the classMappingDescriptor to false.
     *
     * If you need programmatic access to the same instance of this class as used by the bean data control typically
     * created for this class, then you can use the following convenience method:
     *
     * SessionsService crudService = (SessionsService) EntityUtils.getEntityCRUDService(Sessions.class);
     *
     * Note that calling this method might fire a remote method call when autoQuery is set to true in the
     * classMappingDescriptor and the data control has not been instantiated yet for the feature context in which you
     * execute the above call. Remember: each feature has its own class loader, bean data controls are NOT shared
     * accross features!
     */
    public SessionsService() {
    }

    /**
     * Use this constructor with autoQuery=false in Java code when you want to execute a method in this service class
     * without autoQuery as configured in persistence-mapping.xml taking place.
     */
    public SessionsService(boolean autoQuery) {
        super(autoQuery);
    }

    protected Class getEntityClass() {
        return Sessions.class;
    }

    protected String getEntityListName() {
        return "sessions";
    }

    public List<Sessions> getSessions() {
        return getEntityList();
    }

    /**
     * This method is automatically called when using the Create operation on the sessions collection
     * in the data control palette. It gets a new sessions instance as argument and adds this instance to the
     * sessions list.
     * Do NOT drag and drop this method from the data control palette, use the Create operation instead to ensure
     * that iterator binding and sessions list stay in sync.
     * @param index
     * @param sessions
     */
    public void addSessions(int index, Sessions sessions) {
        addEntity(index, sessions);
    }

    /**
     * This method is automatically called when using the Delete operation on the sessions collection
     * in the data control palette. It removes the sessions instance passed in from the sessions list, deletes the
     * corresponding row from the database (if persisted) and calls the configured remove method on the remote
     * persistence manager.
     * Do NOT drag and drop this method from the data control palette, use the Delete operation instead to ensure
     * that iterator binding and sessions list stay in sync.
     * @param sessions
     */
    public void removeSessions(Sessions sessions) {
        removeEntity(sessions);
    }

    /**
     * Inserts or updates a sessions using the configured persistence managers.
     * The insert or update is determined by calling isNewEntity on the sessions instance.
     * @param sessions
     */
    public void saveSessions(Sessions sessions) {
        super.mergeEntity(sessions);
    }

    /**
     * Retrieves all sessions instances using the configured persistence managers and populates the sessions list
     * with the result.
     * When this method is called for the first time, and a remote persistence manager is configured,
     * the data is fetched remotely and the local DB is populated with the results.
     */
    public void findAllSessions() {
        super.findAll();
    }

    /**
     * Retrieves all sessions instances using the findAll method on the remote persistence manager
     * and populates the sessions list
     */
    public void findAllSessionsRemote() {
        super.doRemoteFindAll();
    }

    /**
     * Retrieves the sessions instances that match the searchValue filter using the configured persistence
     * managers and populates the sessions list with the result.
     * By default, the search value is applied to all string attributes using a "startsWith" operator.
     * To customize the attributes on which the searchValue is applied, you can override method getQuickSearchAttributeNames.
     * If a find method is configured against the remote persistence manager, then this method will also
     * call this method.
     * @param searchValue
     */
    public void findSessions(String searchValue) {
        super.find(searchValue);
    }

    /**
     * Invokes the getCanonical method on the remote persistence manager if this has not happened yet
     * for this instance during this application session. The corresponding row in the local database is also updated if
     * the entity is persistable. This method uses the setting of remote-read-in-background property
     * in persistenceMapping.xml to determine whether the method is executed in the background.
     * While you can call this method from the user interface layer using the data control palette, it is easier and
     * cleaner to call this method from a getter method for one of the attributes that will be populated by the
     * getCanonical method call. Here is an example of the code you should add to such a getter method:
     *
     * if (!canonicalGetExecuted())
     * {
     *   SessionsService crudService = (SessionsService) EntityUtils.getEntityCRUDService(Department.class);
     *   crudService.getCanonicalSessions(this);
     * }
     *
     * If you specifed the getCanonical triggering attribute in the AMPA REST wizard, then the above code is already generated
     * for you.
     *
     * @param sessions
     */
    public void getCanonicalSessions(Sessions sessions) {
        super.getCanonical(sessions, isDoRemoteReadInBackground());
    }


    /**
     * Synchronizes all pending data sync actions using the remote persistence manager
     * @param inBackground
     */
    public void synchronizeSessions(Boolean inBackground) {
        super.synchronize(inBackground);
    }

    /**
     * Resets the values of the sessions instance to the values as stored in the SQLite database. This method
     * will do nothing when the sessions is not persisted to the database.
     * @param sessions
     */
    public void resetSessions(Sessions sessions) {
        super.resetEntity(sessions);
    }

    /**
     * Returns true when there are pending sessions data sync actions. Returns false if there are no such actions.
     */
    public boolean getHasSessionsDataSynchActions() {
        return getDataSynchManager().getHasDataSynchActions();
    }


}


