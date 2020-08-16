package ie.cct.wellnessSpace.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name="time_slot")
public class TimeSlot {

    @Id
    @Column(name="timeslot")
    private Time timeslot;

    public TimeSlot(Time timeslot) {
        this.timeslot = timeslot;
    }

    public TimeSlot() {
    }

    public Time getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Time timeslot) {
        this.timeslot = timeslot;
    }
}
