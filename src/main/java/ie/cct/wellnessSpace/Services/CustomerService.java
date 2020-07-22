package ie.cct.wellnessSpace.Services;


import ie.cct.wellnessSpace.Entities.Customers;
import ie.cct.wellnessSpace.Entities.Role;
import ie.cct.wellnessSpace.Entities.Users;
import ie.cct.wellnessSpace.Repository.CustomerRepository;
import ie.cct.wellnessSpace.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    private Customers customer;

    public void save(Customers customer, String username) {
        Users user = new Users(userRepository.findByUsername(username).getId_user());
        this.customer = customer;
        this.customer.setUser(user);
        customerRepository.save(this.customer);
    }
}
