package ie.cct.wellnessSpace.Repository;

import ie.cct.wellnessSpace.Entities.Treatments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TreatmentsRepository extends JpaRepository<Treatments, Integer> {

   @Query(value = "SELECT * from treatments  where id_category=?1", nativeQuery = true)
   List<Treatments> findById_category(int id_category);

   @Query(value = "SELECT * from treatments  where id_category=?1 or id_category=?2", nativeQuery = true)
   List<Treatments> findById_category(int id_category, int id_category2);
}
