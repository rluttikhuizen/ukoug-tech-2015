package nl.eproseed.ukoug.tech15.soap.entity;

import java.io.Serializable;

import java.math.BigDecimal;

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
import javax.xml.bind.annotation.XmlType;

@Entity
@NamedQueries({
              @NamedQuery(name = "Speaker.findAll", query = "select o from Speaker o"),
              @NamedQuery(name = "Speaker.findById", query = "select o from Speaker o where o.id = :id")
    })
@XmlType(propOrder = { "id", "firstname", "lastname", "company", "speakerBio", "presentationList" })
public class Speaker implements Serializable {

    private static final long serialVersionUID = 8466972298834310902L;

    @Id
    @Column(nullable = false)
    private BigDecimal id;
    @Column(length = 200)
    private String firstname;
    @Column(length = 200)
    private String lastname;
    @Column(length = 200)
    private String company;
    @Column(name = "SPEAKER_BIO")
    private String speakerBio;
    @OneToMany(mappedBy = "speaker", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @XmlElement(name = "presentation")
    private List<Presentation> presentationList;

    public Speaker() {
    }

    public Speaker(BigDecimal id, String company, String firstname, String lastname, String speakerBio) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.speakerBio = speakerBio;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSpeakerBio() {
        return speakerBio;
    }

    public void setSpeakerBio(String speakerBio) {
        this.speakerBio = speakerBio;
    }

    public List<Presentation> getPresentationList() {
        return presentationList;
    }

    public void setPresentationList(List<Presentation> presentationList) {
        this.presentationList = presentationList;
    }

    public Presentation addPresentation(Presentation presentation) {
        getPresentationList().add(presentation);
        presentation.setSpeaker(this);
        return presentation;
    }

    public Presentation removePresentation(Presentation presentation) {
        getPresentationList().remove(presentation);
        presentation.setSpeaker(null);
        return presentation;
    }
}
