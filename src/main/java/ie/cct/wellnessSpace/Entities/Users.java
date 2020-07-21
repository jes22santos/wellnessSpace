package ie.cct.wellnessSpace.Entities;



import javax.persistence.*;


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
    @ManyToOne
    @JoinColumn(name="id_role")
    private Role role;

    public Users(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
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


    public Role getRoles() {
        return role;
    }

    public void setRoles(Role roles) {
        this.role = roles;
    }
}
