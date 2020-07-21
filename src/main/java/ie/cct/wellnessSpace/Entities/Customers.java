package ie.cct.wellnessSpace.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class Customers {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_customer;
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="surname", nullable=false)
    private String surname;
    @Column(name="weight", nullable=false)
    private double weight;
    @Column(name="height", nullable=false)
    private double height;
    @Column(name="birthday", nullable=false)
    private Date birthday;
    @Column(name="phone", nullable=false)
    private String phone;
    @Column(name="allergies", nullable=true)
    private String allergies;
    @ManyToOne
    @JoinColumn(name="id_user")
    private Users user;

    public Customers() {
    }

    public Customers(String name, String surname, double weight, double height, Date birthday, String phone, String allergies, Users user) {
        this.name = name;
        this.surname = surname;
        this.weight = weight;
        this.height = height;
        this.birthday = birthday;
        this.phone = phone;
        this.allergies = allergies;
        this.user = user;
    }

    public Integer getId_customer() {
        return id_customer;
    }

    public void setId_customer(Integer id_customer) {
        this.id_customer = id_customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
