package ie.cct.wellnessSpace.Entities;

import javax.persistence.*;

@Entity
@Table
public class Status {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="status_seq")
    @SequenceGenerator(name = "status_seq", sequenceName = "status_seq", initialValue = 5, allocationSize=1)
    @Column(name="id_status", nullable=false)
    private Integer id_status;

    @Column(name="status", nullable=false)
    private String status;

    public Status(Integer id_status, String status) {
        this.id_status = id_status;
        this.status = status;
    }

    public Status(Integer id_status) {
        this.id_status = id_status;
    }

    public Status() {
    }

    public Integer getId_status() {
        return id_status;
    }

    public void setId_status(Integer id_status) {
        this.id_status = id_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
