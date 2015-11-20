package nl.eproseed.ukoug.tech15.soap.entity;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {
         "presentationId", "presentationTitle", "speakerId", "speakerFirstname", "speakerLastname", "numberOfAttendees", "numberOfPositiveEvaluations", "numberOfNeutralEvaluations",
         "numberOfNegativeEvaluations" })
public class EvaluationSummary {

    private BigDecimal presentationId;
    private String presentationTitle;
    
    private BigDecimal speakerId;
    private String speakerFirstname;
    private String speakerLastname;

    private int numberOfAttendees;
    
    private int numberOfPositiveEvaluations;
    private int numberOfNeutralEvaluations;
    private int numberOfNegativeEvaluations;

    public EvaluationSummary() {
        super();
    }

    public void setPresentationId(BigDecimal presentationId) {
        this.presentationId = presentationId;
    }

    public BigDecimal getPresentationId() {
        return presentationId;
    }

    public void setPresentationTitle(String presentationTitle) {
        this.presentationTitle = presentationTitle;
    }

    public String getPresentationTitle() {
        return presentationTitle;
    }

    public void setSpeakerId(BigDecimal speakerId) {
        this.speakerId = speakerId;
    }

    public BigDecimal getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerFirstname(String speakerFirstname) {
        this.speakerFirstname = speakerFirstname;
    }

    public String getSpeakerFirstname() {
        return speakerFirstname;
    }

    public void setSpeakerLastname(String speakerLastname) {
        this.speakerLastname = speakerLastname;
    }

    public String getSpeakerLastname() {
        return speakerLastname;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfPositiveEvaluations(int numberOfPositiveEvaluations) {
        this.numberOfPositiveEvaluations = numberOfPositiveEvaluations;
    }

    public int getNumberOfPositiveEvaluations() {
        return numberOfPositiveEvaluations;
    }

    public void setNumberOfNeutralEvaluations(int numberOfNeutralEvaluations) {
        this.numberOfNeutralEvaluations = numberOfNeutralEvaluations;
    }

    public int getNumberOfNeutralEvaluations() {
        return numberOfNeutralEvaluations;
    }

    public void setNumberOfNegativeEvaluations(int numberOfNegativeEvaluations) {
        this.numberOfNegativeEvaluations = numberOfNegativeEvaluations;
    }

    public int getNumberOfNegativeEvaluations() {
        return numberOfNegativeEvaluations;
    }
}
