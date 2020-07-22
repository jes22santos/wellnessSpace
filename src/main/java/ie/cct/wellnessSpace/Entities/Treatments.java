package ie.cct.wellnessSpace.Entities;

import com.sun.xml.bind.v2.model.core.ID;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="treatments")
public class Treatments {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="treat_seq")
    @SequenceGenerator(name = "treat_seq", sequenceName = "treat_seq", initialValue = 19, allocationSize=1)
    @Column(name="id_treatment")
    private Integer id_treatment;
    @Column(name="name", nullable=false)
    private String name;
    @Column (name="cost", nullable=false)
    private double cost;
    @Column (name="duration", nullable=false)
    private Time duration;
    @Column(name="details", nullable=false)
    private String details;
    @ManyToOne
    @JoinColumn(name="Id_category")
    private Category category;

    public Integer getIdTreatment() {
        return id_treatment;
    }

    public void setIdTreatment(Integer id_treatment) {
        this.id_treatment = id_treatment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
