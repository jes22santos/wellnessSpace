package ie.cct.wellnessSpace.Entities;



import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_user;
    @Column(name="username", nullable=false)
    private String username;
    @Column(name="password", nullable=false)
    private String password;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="userrole",
            joinColumns={@JoinColumn(name="id_user", referencedColumnName="id_user")},
            inverseJoinColumns={@JoinColumn(name="id_role", referencedColumnName="id_role")})
    private Collection<Role> roles;

    public Users(String username, String password, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Users() {
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
