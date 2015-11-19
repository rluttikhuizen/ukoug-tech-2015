package nl.eproseed.ukoug.tech15.entity;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

@Entity
@NamedQueries({
              @NamedQuery(name = "Presentation.findAll", query = "select o from Presentation o"),
              @NamedQuery(name = "Presentation.findById", query = "select o from Presentation o where o.id = :id")
    })
@XmlType(propOrder = {
         "id", "title", "speaker", "coPresenter", "coPresenterEmail", "stream", "topic", "presentationFormat",
         "presentationAbstract", "experience", "preferredLength", "contentLevel", "day", "sessionDate", "track", "startTime", "endTime",
         "length", "hall", "presentationStatus", "attendanceList"
    })
public class Presentation implements Serializable {

    private static final long serialVersionUID = -3310009164475122261L;
    @Column(name = "ABSTRACT")
    private String presentationAbstract;
    @Column(name = "CONTENT_LEVEL")
    private BigDecimal contentLevel;
    @Column(name = "CO_PRESENTER", length = 200)
    private String coPresenter;
    @Column(name = "CO_PRESENTER_EMAIL", length = 200)
    private String coPresenterEmail;
    @Column(length = 200)
    private String day;
    @Column(name = "END_TIME", length = 200)
    private String endTime;
    @Column(length = 200)
    private String experience;
    @Column(length = 200)
    private String hall;
    @Id
    @Column(nullable = false)
    private BigDecimal id;
    private BigDecimal length;
    @Column(name = "PREFERRED_LENGTH", length = 200)
    private String preferredLength;
    @Column(name = "PRESENTATION_FORMAT", length = 200)
    private String presentationFormat;
    @Column(name = "PRESENTATION_STATUS", length = 200)
    private String presentationStatus;
    @Temporal(TemporalType.DATE)
    @Column(name = "SESSION_DATE")
    private Date sessionDate;
    @Column(name = "START_TIME", length = 200)
    private String startTime;
    @Column(length = 200)
    private String stream;
    @Column(nullable = false, length = 200)
    private String title;
    @Column(length = 200)
    private String topic;
    @Column(length = 200)
    private String track;
    @ManyToOne
    @JoinColumn(name = "SPEAKER_ID")
    // Use to break cyclic error for JAX-WS
    @XmlElement
    @XmlInverseReference(mappedBy = "presentationList")
    private Speaker speaker;
    @OneToMany(mappedBy = "presentation", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @XmlElement(name = "attendance")
    private List<Attendance> attendanceList;

    public Presentation() {
    }

    public Presentation(String presentationAbstract, String coPresenter, String coPresenterEmail, BigDecimal contentLevel,
                        String day, String endTime, String experience, String hall, BigDecimal id, BigDecimal length,
                        String preferredLength, String presentationFormat, String presentationStatus, Date sessionDate,
                        Speaker speaker, String startTime, String stream, String title, String topic, String track) {

        this.presentationAbstract = presentationAbstract;
        this.coPresenter = coPresenter;
        this.coPresenterEmail = coPresenterEmail;
        this.contentLevel = contentLevel;
        this.day = day;
        this.endTime = endTime;
        this.experience = experience;
        this.hall = hall;
        this.id = id;
        this.length = length;
        this.preferredLength = preferredLength;
        this.presentationFormat = presentationFormat;
        this.presentationStatus = presentationStatus;
        this.sessionDate = sessionDate;
        this.speaker = speaker;
        this.startTime = startTime;
        this.stream = stream;
        this.title = title;
        this.topic = topic;
        this.track = track;
    }

    public String getPresentationAbstract() {
        return presentationAbstract;
    }

    public void setPresentationAbstract(String presentationAbstract) {
        this.presentationAbstract = presentationAbstract;
    }

    public BigDecimal getContentLevel() {
        return contentLevel;
    }

    public void setContentLevel(BigDecimal contentLevel) {
        this.contentLevel = contentLevel;
    }

    public String getCoPresenter() {
        return coPresenter;
    }

    public void setCoPresenter(String coPresenter) {
        this.coPresenter = coPresenter;
    }

    public String getCoPresenterEmail() {
        return coPresenterEmail;
    }

    public void setCoPresenterEmail(String coPresenterEmail) {
        this.coPresenterEmail = coPresenterEmail;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public String getPreferredLength() {
        return preferredLength;
    }

    public void setPreferredLength(String preferredLength) {
        this.preferredLength = preferredLength;
    }

    public String getPresentationFormat() {
        return presentationFormat;
    }

    public void setPresentationFormat(String presentationFormat) {
        this.presentationFormat = presentationFormat;
    }

    public String getPresentationStatus() {
        return presentationStatus;
    }

    public void setPresentationStatus(String presentationStatus) {
        this.presentationStatus = presentationStatus;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public Attendance addAttendance(Attendance attendance) {
        getAttendanceList().add(attendance);
        attendance.setPresentation(this);
        return attendance;
    }

    public Attendance removeAttendance(Attendance attendance) {
        getAttendanceList().remove(attendance);
        attendance.setPresentation(null);
        return attendance;
    }
}
