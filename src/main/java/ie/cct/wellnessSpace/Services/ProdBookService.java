package ie.cct.wellnessSpace.Services;

import ie.cct.wellnessSpace.Entities.Bookings;
import ie.cct.wellnessSpace.Entities.ProdBook;
import ie.cct.wellnessSpace.Entities.Products;
import ie.cct.wellnessSpace.Repository.ProdBookRepository;
import ie.cct.wellnessSpace.Repository.ProductRepository;
import ie.cct.wellnessSpace.Validator.ProdBookRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdBookService {

    @Autowired
    private ProdBookRepository prodBookRepository;

    @Autowired
    private ProductRepository productRepository;

    /*
        Method to add a product to a booking and alter the quantity of this product on the product table
     */
    public void save(ProdBookRegister prodBookRegister){
        ProdBook newProdBook = new ProdBook();
        newProdBook.setBooking(new Bookings(prodBookRegister.getId_booking()));
        newProdBook.setProduct(new Products(prodBookRegister.getId_product()));
        newProdBook.setQuantity(prodBookRegister.getQuantity());

        prodBookRepository.save(newProdBook);
        Products productToreduce = productRepository.findById_product(prodBookRegister.getId_product());
        int quantity = productToreduce.getQuantity() - newProdBook.getQuantity();
        productToreduce.setQuantity(quantity);
        productRepository.save(productToreduce);
    }
    /*
    Method to delete a product from a booking
     */
    public void delete(ProdBook toCancel){

        if (toCancel != null) {
            Products products = productRepository.getOne(toCancel.getProduct().getId_product());
            int newQuantity = products.getQuantity() + toCancel.getQuantity();
            products.setQuantity(newQuantity);
            prodBookRepository.deleteById(toCancel.getId_prodBook());
        }
    }
}
