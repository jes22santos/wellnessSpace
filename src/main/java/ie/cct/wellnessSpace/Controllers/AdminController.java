package ie.cct.wellnessSpace.Controllers;


import ie.cct.wellnessSpace.Entities.Users;
import ie.cct.wellnessSpace.Services.StaffService;
import ie.cct.wellnessSpace.Services.UserServiceImp;
import ie.cct.wellnessSpace.Validator.StaffRegister;
import ie.cct.wellnessSpace.Validator.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;


@org.springframework.stereotype.Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private StaffService staffService;

    @GetMapping("/admin/myAccount")
    public String myAccountAdmin(){

        return "/admin/myAccount";
    }

    @GetMapping("/admin/bookingsControl")
    public String bookingControl(){

        return "/admin/bookingsControl";
    }
    @GetMapping("/admin/products")
    public String products(){

        return "/admin/products";
    }

    @ModelAttribute("users")
    public UserRegister userRegister() {
        return new UserRegister();
    }

    @GetMapping("/admin/registerAdmin")
    public String registration(Model model) {

        return "/admin/registerAdmin";
    }

    @PostMapping("/admin/registerAdmin")
    String signUp(@ModelAttribute("users") @Valid UserRegister users, BindingResult result) {

        Users existing = userServiceImp.findByUsername(users.getUsername());
        if (existing != null) {
            result.rejectValue("username", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            return "/admin/registerAdmin";
        }

        userServiceImp.saveAdmin(users);

        return "/admin/registerAdmin";
    }

    @ModelAttribute("staff")
    public StaffRegister staffRegister() {

        return new StaffRegister();
    }

    @GetMapping("/admin/addProfileAdmin")
    public String addProfileAdmin(Model model) {

        return "/admin/addProfileAdmin";
    }

    @PostMapping("/admin/addProfileAdmin")
    String addProfile(@ModelAttribute("staff")  StaffRegister staff, Principal principal) {
        String username = principal.getName();
        staffService.save(staff, username);

        return "redirect:/admin/myAccount";
    }
}
