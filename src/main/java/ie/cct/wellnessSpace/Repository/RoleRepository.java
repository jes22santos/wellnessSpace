package ie.cct.wellnessSpace.Repository;

import ie.cct.wellnessSpace.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "SELECT * from role  where role=?1", nativeQuery = true)
    Role findByRole(String role);
}
