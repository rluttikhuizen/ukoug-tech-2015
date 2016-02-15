package com.blogspot.lucbors.uma.mobile.model;

import java.util.Arrays;
import java.util.List;

import oracle.ateam.sample.mobile.v2.persistence.util.EntityUtils;
import oracle.ateam.sample.mobile.v2.persistence.manager.DBPersistenceManager;
import oracle.ateam.sample.mobile.v2.persistence.model.Entity;

import com.blogspot.lucbors.uma.mobile.model.service.SessionsService;

import java.math.BigDecimal;


public class Sessions extends Entity {

    private String abstract1;
    private BigDecimal contentLevel;
    private String day;
    private String endTime;
    private String experience;
    private String hall;
    private BigDecimal id;
    private BigDecimal length;
    private String preferredLength;
    private String presentationFormat;
    private String presentationStatus;
    private String sessionDate;
    private String startTime;
    private String stream;
    private String title;
    private String topic;
    private String track;
    private BigDecimal speakerId;
    private String speakerName;

    private List<Attendance> attendances = createIndirectList("attendances");


    public String getAbstract1() {

        if (!canonicalGetExecuted()) {
            SessionsService crudService = (SessionsService) EntityUtils.getEntityCRUDService(Sessions.class);
            crudService.getCanonicalSessions(this);
        }
        return this.abstract1;
    }

    public void setAbstract1(String abstract1) {
        this.abstract1 = abstract1;
    }

    public BigDecimal getContentLevel() {
        return this.contentLevel;
    }

    public void setContentLevel(BigDecimal contentLevel) {
        this.contentLevel = contentLevel;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getHall() {
        return this.hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getLength() {
        return this.length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public String getPreferredLength() {
        return this.preferredLength;
    }

    public void setPreferredLength(String preferredLength) {
        this.preferredLength = preferredLength;
    }

    public String getPresentationFormat() {
        return this.presentationFormat;
    }

    public void setPresentationFormat(String presentationFormat) {
        this.presentationFormat = presentationFormat;
    }

    public String getPresentationStatus() {
        return this.presentationStatus;
    }

    public void setPresentationStatus(String presentationStatus) {
        this.presentationStatus = presentationStatus;
    }

    public String getSessionDate() {
        return this.sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStream() {
        return this.stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTrack() {
        return this.track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public BigDecimal getSpeakerId() {
        return this.speakerId;
    }

    public void setSpeakerId(BigDecimal speakerId) {
        this.speakerId = speakerId;
    }

    public String getSpeakerName() {
        return this.speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }


    public void setAttendances(List<Attendance> attendances) {
        this.attendances.clear();
        this.attendances.addAll(attendances);
        getProviderChangeSupport().fireProviderRefresh("attendances");
    }

    /**
     * This method is called when entity instance is recreated from persisted JSON string in DataSynchAction
     */
    public void setAttendances(Attendance[] attendances) {
        setAttendances(Arrays.asList(attendances));
    }

    public List<Attendance> getAttendances() {
        return this.attendances;
    }


    public void addAttendances(int index, Attendance attendances) {
        attendances.setIsNewEntity(true);
        EntityUtils.generatePrimaryKeyValue(attendances, 1);
        attendances.setSessionId(getId());
    }

    public void removeAttendances(Attendance attendances) {
        DBPersistenceManager pm = new DBPersistenceManager();
        pm.removeEntity(attendances, true);
    }


}
