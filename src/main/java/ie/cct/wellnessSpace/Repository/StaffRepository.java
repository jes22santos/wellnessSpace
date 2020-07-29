package ie.cct.wellnessSpace.Repository;

import ie.cct.wellnessSpace.Entities.Staffs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staffs, Integer> {

    @Query(value = "SELECT * from staffs  where id_staff=?1", nativeQuery = true)
    Staffs findById_staff(Integer id_staff);

    @Query(value = "SELECT * from staffs  where id_user=?1", nativeQuery = true)
    Staffs findByUser (Integer id_user);

    @Query(value = "SELECT * from staffs where id_category=?1", nativeQuery = true)
    Staffs findByCategory (int id_category);
}
