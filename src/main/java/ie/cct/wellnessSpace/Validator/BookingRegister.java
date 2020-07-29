package ie.cct.wellnessSpace.Validator;

import java.sql.Date;
import java.sql.Time;

public class BookingRegister {

    private int idTreatment;
    private int idStaff;
    private Date date;
    private Time time;
    private String notes;

    public BookingRegister(int idTreatment, int idStaff, Date date, Time time, String notes) {
        this.idTreatment = idTreatment;
        this.idStaff = idStaff;
        this.date = date;
        this.time = time;
        this.notes = notes;
    }

    public BookingRegister() {
    }

    public int getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(int idTreatment) {
        this.idTreatment = idTreatment;
    }

    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
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
}
