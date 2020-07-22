package ie.cct.wellnessSpace.Services;

import ie.cct.wellnessSpace.Entities.Category;
import ie.cct.wellnessSpace.Entities.Customers;
import ie.cct.wellnessSpace.Entities.Staffs;
import ie.cct.wellnessSpace.Entities.Users;
import ie.cct.wellnessSpace.Repository.CategoryRepository;
import ie.cct.wellnessSpace.Repository.StaffRepository;
import ie.cct.wellnessSpace.Repository.UserRepository;
import ie.cct.wellnessSpace.Validator.StaffRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StaffRepository staffRepository;

    private Staffs staff = new Staffs();

    public void save(StaffRegister staff, String username) {
        Users user = new Users(userRepository.findByUsername(username).getId_user());
        Category category = new Category(categoryRepository.findByN_category(staff.getCategory()).getId_category());
        this.staff.setName(staff.getName());
        this.staff.setSurname(staff.getSurname());
        this.staff.setCredential(staff.getCredential());
        this.staff.setUser(user);
        this.staff.setCategory(category);
        staffRepository.save(this.staff);
    }
}
