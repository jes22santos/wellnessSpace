package ie.cct.wellnessSpace.Repository;

import ie.cct.wellnessSpace.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT * from category  where n_category=?1", nativeQuery = true)
    Category findByN_category(String n_category);
}
