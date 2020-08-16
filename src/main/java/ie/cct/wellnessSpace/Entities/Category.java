package ie.cct.wellnessSpace.Entities;

import javax.persistence.*;

@Entity
@Table (name="category")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id_category;
    @Column(name="n_category", nullable=false)
    private String n_category;

    public Category(int id_category, String n_category) {
        this.id_category = id_category;
        this.n_category = n_category;
    }

    public Category(int id_category) {
        this.id_category = id_category;
    }

    public Category() {
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getN_category() {
        return n_category;
    }

    public void setN_category(String n_category) {
        this.n_category = n_category;
    }
}
