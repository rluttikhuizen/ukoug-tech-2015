package nl.eproseed.ukoug.tech15.toplink.entity;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
              @NamedQuery(name = "Attendee.findAll", query = "select o from Attendee o"),
              @NamedQuery(name = "Attendee.findById", query = "select o from Attendee o where o.id = :id"),
              @NamedQuery(name = "Attendee.findByUsername",
                          query = "select o from Attendee o where o.username = :username")
    })
public class Attendee implements Serializable {
    private static final long serialVersionUID = -2074846133734197356L;
    @Column(length = 200)
    private String company;
    @Id
    @Column(nullable = false)
    private BigDecimal id;
    @Column(nullable = false, length = 200)
    private String name;
    @Column(nullable = false, length = 200)
    private String username;
    @OneToMany(mappedBy = "attendee", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Attendance> attendances;

    public Attendee() {
    }

    public Attendee(String company, BigDecimal id, String name, String username) {
        this.company = company;
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Attendance addAttendance(Attendance attendance) {
        getAttendances().add(attendance);
        attendance.setAttendee(this);
        return attendance;
    }

    public Attendance removeAttendance(Attendance attendance) {
        getAttendances().remove(attendance);
        attendance.setAttendee(null);
        return attendance;
    }
}
