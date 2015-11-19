package nl.eproseed.ukoug.tech15.entity;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.XmlElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

@Entity
@NamedQueries({ @NamedQuery(name = "Attendance.findAll", query = "select o from Attendance o") })
public class Attendance implements Serializable {

    private static final long serialVersionUID = -2352820538098469942L;
    @Id
    @Column(nullable = false)
    private BigDecimal id;
    @Column(length = 100)
    private String status;
    @ManyToOne
    @JoinColumn(name = "ATTENDEE_ID")
    // Use to break cyclic error for JAX-WS
    @XmlElement
    @XmlInverseReference(mappedBy="attendanceList")
    private Attendee attendee;
    @ManyToOne
    @JoinColumn(name = "PRESENTATION_ID")
    // Use to break cyclic error for JAX-WS
    @XmlElement
    @XmlInverseReference(mappedBy="attendanceList") 
    private Presentation presentation;

    public Attendance() {
    }

    public Attendance(Attendee attendee, BigDecimal id, Presentation presentation, String status) {

        this.attendee = attendee;
        this.id = id;
        this.status = status;
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
