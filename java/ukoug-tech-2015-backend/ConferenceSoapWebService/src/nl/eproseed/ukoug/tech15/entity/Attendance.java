package nl.eproseed.ukoug.tech15.entity;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

@Entity
@NamedQueries({ @NamedQuery(name = "Attendance.findAll", query = "select o from Attendance o") })
@XmlType(propOrder = { "id", "attendee", "presentation", "status", "evaluation", "evaluationTimestamp" })
public class Attendance implements Serializable {

    private static final long serialVersionUID = -2352820538098469942L;
    @Id
    @Column(nullable = false)
    private BigDecimal id;
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

    public Attendance(Attendee attendee, BigDecimal id, Presentation presentation, String status, String evaluation, Date evaluationTimestamp) {

        this.attendee = attendee;
        this.id = id;
        this.presentation = presentation;
        this.status = status;
        this.evaluation = evaluation;
        this.evaluationTimestamp = evaluationTimestamp;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluationTimestamp(Date evaluationTimestamp) {
        this.evaluationTimestamp = evaluationTimestamp;
    }

    public Date getEvaluationTimestamp() {
        return evaluationTimestamp;
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
