package nl.eproseed.ukoug.tech15.toplink.entity;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
              @NamedQuery(name = "Attendance.findAll", query = "select o from Attendance o"),
              @NamedQuery(name = "Attendance.findById", query = "select o from Attendance o where o.id = :id"),
    })
public class Attendance implements Serializable {
    private static final long serialVersionUID = 7253673987013765125L;
    @Column(length = 100)
    private String evaluation;
    @Column(name = "EVALUATION_TIMESTAMP")
    private Timestamp evaluationTimestamp;
    @Id
    @Column(nullable = false)
    private BigDecimal id;
    @Column(length = 100)
    private String status;
    @ManyToOne
    @JoinColumn(name = "ATTENDEE_ID")
    private Attendee attendee;
    @ManyToOne
    @JoinColumn(name = "PRESENTATION_ID")
    private Presentation presentation;

    public Attendance() {
    }

    public Attendance(Attendee attendee, String evaluation, Timestamp evaluationTimestamp, BigDecimal id,
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

    public Timestamp getEvaluationTimestamp() {
        return evaluationTimestamp;
    }

    public void setEvaluationTimestamp(Timestamp evaluationTimestamp) {
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
