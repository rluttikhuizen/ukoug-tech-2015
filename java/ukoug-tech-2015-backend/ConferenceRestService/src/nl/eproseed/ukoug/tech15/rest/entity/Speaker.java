package nl.eproseed.ukoug.tech15.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({ @NamedQuery(name = "Speaker.findAll", query = "select o from Speaker o") })
@XmlRootElement
public class Speaker implements Serializable {
    
    private static final long serialVersionUID = 8466972298834310902L;

    @Id
    @Column(nullable = false)
    private String id;
    @Column(length = 200)
    private String firstname;
    @Column(length = 200)
    private String lastname;
    @Column(length = 200)
    private String company;
    @Column(name = "SPEAKER_BIO")
    private String speakerBio;
//    @OneToMany(mappedBy = "speaker", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
//    @XmlElement(name = "presentation")
//    private List<Presentation> presentationList;

    public Speaker() {
    }

    public Speaker(String company, String firstname, String id, String lastname, String speakerBio) {
        
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

//    public List<Presentation> getPresentationList() {
//        return presentationList;
//    }
//
//    public void setPresentationList(List<Presentation> presentationList) {
//        this.presentationList = presentationList;
//    }
//
//    public Presentation addPresentation(Presentation presentation) {
//        getPresentationList().add(presentation);
//        presentation.setSpeaker(this);
//        return presentation;
//    }
//
//    public Presentation removePresentation(Presentation presentation) {
//        getPresentationList().remove(presentation);
//        presentation.setSpeaker(null);
//        return presentation;
//    }
}
