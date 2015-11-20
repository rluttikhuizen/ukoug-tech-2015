package nl.eproseed.ukoug.tech15.rest.entity;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

@Entity
@NamedQueries({ @NamedQuery(name = "Attendance.findAll", query = "select o from Attendance o") })
@XmlRootElement
public class Attendance implements Serializable {
    
    private static final long serialVersionUID = -4134029313742202647L;
   
    @Id
    @Column(nullable = false)
    @SequenceGenerator(name = "attendanceSeq", sequenceName = "ATTENDANCE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendanceSeq")
    private String id;
    @Column(length = 100)
    private String status;
    @Column(length = 100)
    private String evaluation;
    @Column(name = "EVALUATION_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date evaluationTimestamp;
    @ManyToOne
    @JoinColumn(name = "ATTENDEE_ID")
    // Use to break cyclic error for JAX-WS
    @XmlElement
    @XmlInverseReference(mappedBy = "attendanceList")
    private Attendee attendee;
    @ManyToOne
    @JoinColumn(name = "PRESENTATION_ID")
    // Use to break cyclic error for JAX-WS
    @XmlElement
    @XmlInverseReference(mappedBy = "attendanceList")
    private Presentation presentation;

    public Attendance() {
    }

    public Attendance(Attendee attendee, String evaluation, Timestamp evaluationTimestamp, String id,
                      Presentation presentation, String status) {
        
        this.attendee = attendee;
        this.evaluation = evaluation;
        this.evaluationTimestamp = evaluationTimestamp;
        this.id = id;
        this.presentation = presentation;
        this.status = status;
    }


    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Date getEvaluationTimestamp() {
        return evaluationTimestamp;
    }

    public void setEvaluationTimestamp(Date evaluationTimestamp) {
        this.evaluationTimestamp = evaluationTimestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Attendee getAttendee() {
        return attendee;
    }

    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }
}
