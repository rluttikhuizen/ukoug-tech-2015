package nl.eproseed.ukoug.tech15.rest.entity;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({ @NamedQuery(name = "Attendee.findAll", query = "select o from Attendee o") })
@XmlRootElement
public class Attendee implements Serializable {
   
    private static final long serialVersionUID = 6447165266581453597L;
   
    @Column(length = 200)
    private String company;
    @Id
    @Column(nullable = false)
    private String id;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 200, nullable = false)
    private String username;
    @OneToMany(mappedBy = "attendee", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @XmlElement(name = "attendance")
    private List<Attendance> attendanceList;

    public Attendee() {
    }

    public Attendee(String company, String id, String name, String username) {
        
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public Attendance addAttendance(Attendance attendance) {
        getAttendanceList().add(attendance);
        attendance.setAttendee(this);
        return attendance;
    }

    public Attendance removeAttendance(Attendance attendance) {
        getAttendanceList().remove(attendance);
        attendance.setAttendee(null);
        return attendance;
    }
}
