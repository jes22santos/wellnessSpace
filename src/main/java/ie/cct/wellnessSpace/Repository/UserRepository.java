package ie.cct.wellnessSpace.Repository;

import ie.cct.wellnessSpace.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT * from users  where username=?1", nativeQuery = true)
    Users findByUsername(String username);


}
