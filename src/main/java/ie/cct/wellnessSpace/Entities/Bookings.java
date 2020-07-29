package ie.cct.wellnessSpace.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table
public class Bookings {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "booking_seq", initialValue = 1, allocationSize=1)
    @Column(name="id_booking", nullable=false)
    private Integer id_booking;
    @Column(name="date", nullable=false)
    private Date date;
    @Column(name="time", nullable=false)
    private Time time;
    @Column(name="notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name="id_customer")
    private Customers customer;

    @ManyToOne
    @JoinColumn(name="id_staff")
    private Staffs staff;

    @ManyToOne
    @JoinColumn(name="id_treatment")
    private Treatments treatment;

    @ManyToOne
    @JoinColumn(name="id_status")
    private Status status;

    public Bookings(Date date, Time time, String notes, Customers customer, Staffs staff, Treatments treatment, Status status) {
        this.date = date;
        this.time = time;
        this.notes = notes;
        this.customer = customer;
        this.staff = staff;
        this.treatment = treatment;
        this.status = status;
    }

    public Bookings() {
    }

    public Integer getId_booking() {
        return id_booking;
    }

    public void setId_booking(Integer id_booking) {
        this.id_booking = id_booking;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Staffs getStaff() {
        return staff;
    }

    public void setStaff(Staffs staff) {
        this.staff = staff;
    }

    public Treatments getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatments treatment) {
        this.treatment = treatment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
