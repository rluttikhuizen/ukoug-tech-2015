package com.blogspot.lucbors.uma.mobile.model.service;


import java.util.ArrayList;

import java.util.List;

import oracle.ateam.sample.mobile.v2.persistence.util.EntityUtils;
import oracle.ateam.sample.mobile.v2.persistence.service.EntityCRUDService;

import com.blogspot.lucbors.uma.mobile.model.Attendance;


/**
 *  Service class that provides CRUD and custom operations against the attendance data object.
 *  The behavior of this class is driven by the attendance classMappingDescriptor in persistence-mapping.xml.
 *
 *  You can customize and extend this behavior by overriding methods of the EntityCRUDService superclass, and/or
 *  creating a subclass of the local and remote persistence managers as configured in persistence-mapping.xml.
 */
public class AttendanceService extends EntityCRUDService<Attendance> {

    /**
     * Default constructor. If autoQuery is set to true in the classMappingDescriptor in persistence-mapping.xml, then
     * the findAll method will be executed and the attendance list will be populated when this constructor is invoked.
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
     * AttendanceService crudService = (AttendanceService) EntityUtils.getEntityCRUDService(Attendance.class);
     *
     * Note that calling this method might fire a remote method call when autoQuery is set to true in the
     * classMappingDescriptor and the data control has not been instantiated yet for the feature context in which you
     * execute the above call. Remember: each feature has its own class loader, bean data controls are NOT shared
     * accross features!
     */
    public AttendanceService() {
    }

    /**
     * Use this constructor with autoQuery=false in Java code when you want to execute a method in this service class
     * without autoQuery as configured in persistence-mapping.xml taking place.
     */
    public AttendanceService(boolean autoQuery) {
        super(autoQuery);
    }

    protected Class getEntityClass() {
        return Attendance.class;
    }

    protected String getEntityListName() {
        return "attendance";
    }

    public List<Attendance> getAttendance() {
        return getEntityList();
    }

    /**
     * This method is automatically called when using the Create operation on the attendance collection
     * in the data control palette. It gets a new attendance instance as argument and adds this instance to the
     * attendance list.
     * Do NOT drag and drop this method from the data control palette, use the Create operation instead to ensure
     * that iterator binding and attendance list stay in sync.
     * @param index
     * @param attendance
     */
    public void addAttendance(int index, Attendance attendance) {
        addEntity(index, attendance);
    }

    /**
     * This method is automatically called when using the Delete operation on the attendance collection
     * in the data control palette. It removes the attendance instance passed in from the attendance list, deletes the
     * corresponding row from the database (if persisted) and calls the configured remove method on the remote
     * persistence manager.
     * Do NOT drag and drop this method from the data control palette, use the Delete operation instead to ensure
     * that iterator binding and attendance list stay in sync.
     * @param attendance
     */
    public void removeAttendance(Attendance attendance) {
        removeEntity(attendance);
    }

    /**
     * Inserts or updates a attendance using the configured persistence managers.
     * The insert or update is determined by calling isNewEntity on the attendance instance.
     * @param attendance
     */
    public void saveAttendance(Attendance attendance) {
        super.mergeEntity(attendance);
    }

    /**
     * Retrieves all attendance instances using the configured persistence managers and populates the attendance list
     * with the result.
     * When this method is called for the first time, and a remote persistence manager is configured,
     * the data is fetched remotely and the local DB is populated with the results.
     */
    public void findAllAttendance() {
        super.findAll();
    }

    /**
     * Retrieves all attendance instances using the findAll method on the remote persistence manager
     * and populates the attendance list
     */
    public void findAllAttendanceRemote() {
        super.doRemoteFindAll();
    }

    /**
     * Retrieves the attendance instances that match the searchValue filter using the configured persistence
     * managers and populates the attendance list with the result.
     * By default, the search value is applied to all string attributes using a "startsWith" operator.
     * To customize the attributes on which the searchValue is applied, you can override method getQuickSearchAttributeNames.
     * If a find method is configured against the remote persistence manager, then this method will also
     * call this method.
     * @param searchValue
     */
    public void findAttendance(String searchValue) {
        super.find(searchValue);
    }


    /**
     * Synchronizes all pending data sync actions using the remote persistence manager
     * @param inBackground
     */
    public void synchronizeAttendance(Boolean inBackground) {
        super.synchronize(inBackground);
    }

    /**
     * Resets the values of the attendance instance to the values as stored in the SQLite database. This method
     * will do nothing when the attendance is not persisted to the database.
     * @param attendance
     */
    public void resetAttendance(Attendance attendance) {
        super.resetEntity(attendance);
    }

    /**
     * Returns true when there are pending attendance data sync actions. Returns false if there are no such actions.
     */
    public boolean getHasAttendanceDataSynchActions() {
        return getDataSynchManager().getHasDataSynchActions();
    }


}


