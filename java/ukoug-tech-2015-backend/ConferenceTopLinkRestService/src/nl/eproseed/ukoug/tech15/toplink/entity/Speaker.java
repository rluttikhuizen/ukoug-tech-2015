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
              @NamedQuery(name = "Speaker.findAll", query = "select o from Speaker o"),
              @NamedQuery(name = "Speaker.findById", query = "select o from Speaker o where o.id = :id")
    })
public class Speaker implements Serializable {
    private static final long serialVersionUID = 7500128312109901819L;
    @Column(length = 200)
    private String company;
    @Column(length = 200)
    private String firstname;
    @Id
    @Column(nullable = false)
    private BigDecimal id;
    @Column(length = 200)
    private String lastname;
    @Column(name = "SPEAKER_BIO")
    private String speakerBio;
    @OneToMany(mappedBy = "speaker", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Presentation> presentations;

    public Speaker() {
    }

    public Speaker(String company, String firstname, BigDecimal id, String lastname, String speakerBio) {
        this.company = company;
        this.firstname = firstname;
        this.id = id;
        this.lastname = lastname;
        this.speakerBio = speakerBio;
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

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public List<Presentation> getPresentations() {
        return presentations;
    }

    public void setPresentations(List<Presentation> presentations) {
        this.presentations = presentations;
    }

    public Presentation addPresentation(Presentation presentation) {
        getPresentations().add(presentation);
        presentation.setSpeaker(this);
        return presentation;
    }

    public Presentation removePresentation(Presentation presentation) {
        getPresentations().remove(presentation);
        presentation.setSpeaker(null);
        return presentation;
    }
}
