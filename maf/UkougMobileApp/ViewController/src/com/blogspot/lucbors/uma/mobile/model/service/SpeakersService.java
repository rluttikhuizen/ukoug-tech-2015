package com.blogspot.lucbors.uma.mobile.model.service;


import java.util.ArrayList;

import java.util.List;

import oracle.ateam.sample.mobile.v2.persistence.util.EntityUtils;
import oracle.ateam.sample.mobile.v2.persistence.service.EntityCRUDService;

import com.blogspot.lucbors.uma.mobile.model.Speakers;


/**
 *  Service class that provides CRUD and custom operations against the speakers data object.
 *  The behavior of this class is driven by the speakers classMappingDescriptor in persistence-mapping.xml.
 *
 *  You can customize and extend this behavior by overriding methods of the EntityCRUDService superclass, and/or
 *  creating a subclass of the local and remote persistence managers as configured in persistence-mapping.xml.
 */
public class SpeakersService extends EntityCRUDService<Speakers> {

    /**
     * Default constructor. If autoQuery is set to true in the classMappingDescriptor in persistence-mapping.xml, then
     * the findAll method will be executed and the speakers list will be populated when this constructor is invoked.
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
     * SpeakersService crudService = (SpeakersService) EntityUtils.getEntityCRUDService(Speakers.class);
     *
     * Note that calling this method might fire a remote method call when autoQuery is set to true in the
     * classMappingDescriptor and the data control has not been instantiated yet for the feature context in which you
     * execute the above call. Remember: each feature has its own class loader, bean data controls are NOT shared
     * accross features!
     */
    public SpeakersService() {
    }

    /**
     * Use this constructor with autoQuery=false in Java code when you want to execute a method in this service class
     * without autoQuery as configured in persistence-mapping.xml taking place.
     */
    public SpeakersService(boolean autoQuery) {
        super(autoQuery);
    }

    protected Class getEntityClass() {
        return Speakers.class;
    }

    protected String getEntityListName() {
        return "speakers";
    }

    public List<Speakers> getSpeakers() {
        return getEntityList();
    }

    /**
     * This method is automatically called when using the Create operation on the speakers collection
     * in the data control palette. It gets a new speakers instance as argument and adds this instance to the
     * speakers list.
     * Do NOT drag and drop this method from the data control palette, use the Create operation instead to ensure
     * that iterator binding and speakers list stay in sync.
     * @param index
     * @param speakers
     */
    public void addSpeakers(int index, Speakers speakers) {
        addEntity(index, speakers);
    }

    /**
     * This method is automatically called when using the Delete operation on the speakers collection
     * in the data control palette. It removes the speakers instance passed in from the speakers list, deletes the
     * corresponding row from the database (if persisted) and calls the configured remove method on the remote
     * persistence manager.
     * Do NOT drag and drop this method from the data control palette, use the Delete operation instead to ensure
     * that iterator binding and speakers list stay in sync.
     * @param speakers
     */
    public void removeSpeakers(Speakers speakers) {
        removeEntity(speakers);
    }

    /**
     * Inserts or updates a speakers using the configured persistence managers.
     * The insert or update is determined by calling isNewEntity on the speakers instance.
     * @param speakers
     */
    public void saveSpeakers(Speakers speakers) {
        super.mergeEntity(speakers);
    }

    /**
     * Retrieves all speakers instances using the configured persistence managers and populates the speakers list
     * with the result.
     * When this method is called for the first time, and a remote persistence manager is configured,
     * the data is fetched remotely and the local DB is populated with the results.
     */
    public void findAllSpeakers() {
        super.findAll();
    }

    /**
     * Retrieves all speakers instances using the findAll method on the remote persistence manager
     * and populates the speakers list
     */
    public void findAllSpeakersRemote() {
        super.doRemoteFindAll();
    }

    /**
     * Retrieves the speakers instances that match the searchValue filter using the configured persistence
     * managers and populates the speakers list with the result.
     * By default, the search value is applied to all string attributes using a "startsWith" operator.
     * To customize the attributes on which the searchValue is applied, you can override method getQuickSearchAttributeNames.
     * If a find method is configured against the remote persistence manager, then this method will also
     * call this method.
     * @param searchValue
     */
    public void findSpeakers(String searchValue) {
        super.find(searchValue);
    }


    /**
     * Synchronizes all pending data sync actions using the remote persistence manager
     * @param inBackground
     */
    public void synchronizeSpeakers(Boolean inBackground) {
        super.synchronize(inBackground);
    }

    /**
     * Resets the values of the speakers instance to the values as stored in the SQLite database. This method
     * will do nothing when the speakers is not persisted to the database.
     * @param speakers
     */
    public void resetSpeakers(Speakers speakers) {
        super.resetEntity(speakers);
    }

    /**
     * Returns true when there are pending speakers data sync actions. Returns false if there are no such actions.
     */
    public boolean getHasSpeakersDataSynchActions() {
        return getDataSynchManager().getHasDataSynchActions();
    }


}


