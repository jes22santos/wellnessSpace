package ie.cct.wellnessSpace.Controllers;

import ie.cct.wellnessSpace.Entities.Customers;
import ie.cct.wellnessSpace.Entities.Treatments;
import ie.cct.wellnessSpace.Entities.Users;
import ie.cct.wellnessSpace.Repository.TreatmentsRepository;
import ie.cct.wellnessSpace.Services.CustomerService;
import ie.cct.wellnessSpace.Validator.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
@org.springframework.stereotype.Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private CustomerService customerService;

        @GetMapping("/user/myAccount")
        public String userMyAccount(){

            return "/user/myAccount";
        }

    @ModelAttribute("customer")
    public Customers customer() {

            return new Customers();
    }

    @GetMapping("/user/addProfile")
    public String addProfile(Model model) {

        return "/user/addProfile";
    }

    @PostMapping("/user/addProfile")
    String addProfile(@ModelAttribute("customer")  Customers customer, Principal principal) {
        String username = principal.getName();
        customerService.save(customer, username);

        return "redirect:/user/myAccount";
    }

}
