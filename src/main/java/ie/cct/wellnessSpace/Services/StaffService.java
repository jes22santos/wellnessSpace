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



    /*
    Method to save a staff
     */
    public void save(StaffRegister staff, String username) {
        Staffs newStaff = new Staffs();
        Users user = new Users(userRepository.findByUsername(username).getId_user());
        Category category = new Category(categoryRepository.findByN_category(staff.getCategory()).getId_category());
        newStaff.setName(staff.getName());
        newStaff.setSurname(staff.getSurname());
        newStaff.setCredential(staff.getCredential());
        newStaff.setUser(user);
        newStaff.setCategory(category);
        staffRepository.save(newStaff);
    }
}
