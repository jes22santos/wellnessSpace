package ie.cct.wellnessSpace.Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_role;
    @Column(name="role", nullable=false)
    private String role;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }
    //@ManyToMany(mappedBy = "roles")
    //private Set<Users> user;

    public Integer getId_role() {
        return id_role;
    }

    public void setId_role(Integer id_role) {
        this.id_role = id_role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

