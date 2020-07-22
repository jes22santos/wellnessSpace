package ie.cct.wellnessSpace.Repository;

import ie.cct.wellnessSpace.Entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
    @Query(value = "SELECT * from customers  where id_customer=?1", nativeQuery = true)
    Customers findById_customer(Integer id_customer);


    @Query(value = "SELECT * from Customers  where id_user=?1", nativeQuery = true)
    Customers findByUser (Integer id_user);
}
