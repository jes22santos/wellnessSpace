package ie.cct.wellnessSpace.Repository;

import ie.cct.wellnessSpace.Entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

    @Query(value = "SELECT * from products  where id_product=?1", nativeQuery = true)
    Products findById_product(Integer id_product);

    @Query(value = "SELECT * FROM products", nativeQuery = true)
    List<Products> findAll();

    @Query(value= "SELECT * FROM products WHERE name LIKE %?1%", nativeQuery=true)
    List<Products> findAllByNameLike(String name);

}
