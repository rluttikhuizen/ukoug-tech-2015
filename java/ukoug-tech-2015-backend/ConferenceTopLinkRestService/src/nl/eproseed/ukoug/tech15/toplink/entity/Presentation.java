package nl.eproseed.ukoug.tech15.toplink.entity;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
              @NamedQuery(name = "Presentation.findAll", query = "select o from Presentation o"),
              @NamedQuery(name = "Presentation.findById", query = "select o from Presentation o where o.id = :id")
    })
public class Presentation implements Serializable {
    private static final long serialVersionUID = 6051912706935591849L;
    @Column(name = "ABSTRACT")
    private String abstract_;
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
    private Speaker speaker;
    @OneToMany(mappedBy = "presentation", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Attendance> attendances;

    public Presentation() {
    }

    public Presentation(String abstract_, String coPresenter, String coPresenterEmail, BigDecimal contentLevel,
                        String day, String endTime, String experience, String hall, BigDecimal id, BigDecimal length,
                        String preferredLength, String presentationFormat, String presentationStatus, Date sessionDate,
                        Speaker speaker, String startTime, String stream, String title, String topic, String track) {
        this.abstract_ = abstract_;
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

    public String getAbstract_() {
        return abstract_;
    }

    public void setAbstract_(String abstract_) {
        this.abstract_ = abstract_;
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

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Attendance addAttendance(Attendance attendance) {
        getAttendances().add(attendance);
        attendance.setPresentation(this);
        return attendance;
    }

    public Attendance removeAttendance(Attendance attendance) {
        getAttendances().remove(attendance);
        attendance.setPresentation(null);
        return attendance;
    }
}
