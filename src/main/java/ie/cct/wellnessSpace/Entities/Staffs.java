package ie.cct.wellnessSpace.Entities;

import javax.persistence.*;

@Entity
@Table(name="staffs")
public class Staffs {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="staff_seq")
    @SequenceGenerator(name = "staff_seq", sequenceName = "staff_seq", initialValue = 2, allocationSize=1)
    @Column(name="id_staff")
    private Integer id_staff;
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="surname", nullable=false)
    private String surname;
    @Column(name="credentiaL", nullable=false)
    private String credential;
    @ManyToOne
    @JoinColumn(name="id_user")
    private Users user;
    @ManyToOne
    @JoinColumn(name="id_category")
    private Category category;

    public Staffs(String name, String surname, String credential, Users user, Category category) {
        this.name = name;
        this.surname = surname;
        this.credential = credential;
        this.user = user;
        this.category = category;
    }

    public Staffs() {
    }

    public Staffs(Integer id_staff) {
        this.id_staff = id_staff;
    }

    public Staffs(Staffs byUser) {
    }

    public Integer getId_staff() {
        return id_staff;
    }

    public void setId_staff(Integer id_staff) {
        this.id_staff = id_staff;
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

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
