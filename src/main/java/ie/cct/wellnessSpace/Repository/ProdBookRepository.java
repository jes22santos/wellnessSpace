package ie.cct.wellnessSpace.Repository;

import ie.cct.wellnessSpace.Entities.ProdBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdBookRepository extends JpaRepository<ProdBook, Integer> {

    @Query(value = "SELECT * from prod_book  where id_prodBook=?1", nativeQuery = true)
    List<ProdBook> findAllById_prodBook(Integer id_prodBook);

    @Query(value = "SELECT * from prod_book  where id_booking=?1", nativeQuery = true)
    List<ProdBook> findAllByBooking(Integer id_booking);
}
