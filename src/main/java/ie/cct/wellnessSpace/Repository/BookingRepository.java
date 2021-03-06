package ie.cct.wellnessSpace.Repository;

import ie.cct.wellnessSpace.Entities.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Integer> {

    @Query(value = "SELECT * from bookings  where date=?1 and id_staff=?2", nativeQuery = true)
    List <Bookings> findByDateandStaff(Date date, Integer id_staff);

    @Query(value = "SELECT * from bookings  where id_customer=?1 order by date", nativeQuery = true)
    List<Bookings> findAllByCustomer(Integer id_customer);

    @Query(value = "SELECT * from bookings  where id_staff=?1 order by date", nativeQuery = true)
    List<Bookings> findAllByStaff(Integer id_staff);

    @Query(value = "SELECT * from bookings  where id_staff=?1 AND date BETWEEN ?2 AND ?3 order by date", nativeQuery = true)
    List<Bookings> findAllByStaffAndDate(Integer id_staff, Date dateFrom, Date dateTo);

    @Query(value="UPDATE bookings SET id_status=3 where id_booking=?1", nativeQuery=true)
    void updateStatus(Integer id_booking);

}
