package ie.cct.wellnessSpace.Repository;

import ie.cct.wellnessSpace.Entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    @Query(value = "SELECT * from status where status=?1", nativeQuery = true)
    Status findByStatus(String status);
}
