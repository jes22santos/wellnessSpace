package ie.cct.wellnessSpace.Repository;

import ie.cct.wellnessSpace.Entities.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Time> {

    @Query(value = "SELECT * from timeslot" , nativeQuery = true)
    List<TimeSlot> findAll();
}
