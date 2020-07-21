package ie.cct.wellnessSpace.Controllers;

import ie.cct.wellnessSpace.Entities.Treatments;
import ie.cct.wellnessSpace.Entities.Users;
import ie.cct.wellnessSpace.Repository.TreatmentsRepository;
import ie.cct.wellnessSpace.Services.UserServiceImp;
import ie.cct.wellnessSpace.Validator.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    private TreatmentsRepository treatmentsRepository;


    @Autowired
    public Controller(TreatmentsRepository treatmentsRepository) {

        this.treatmentsRepository = treatmentsRepository;
    }
    @Autowired
    private UserServiceImp userServiceImp;


    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/login")
    public String login(Model model){

        return "login";
    }

    @ModelAttribute("users")
    public UserRegister userRegister() {
        return new UserRegister();
    }

    @GetMapping("/signup")
    public String registration(Model model) {

        return "signup";
    }

    @PostMapping("/signup")
    String signUp(@ModelAttribute("users") @Valid UserRegister user, BindingResult result) {

        Users existing = userServiceImp.findByUsername(user.getUsername());
        if (existing != null) {
            result.rejectValue("username", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            return "signup";
        }

        userServiceImp.save(user);

        return "redirect:/login";
    }

    @RequestMapping(value = "/mentalTreatments", method = RequestMethod.GET)
    public String listMental(Model model) {
        int id_category = 3;
        List<Treatments> mentalTreatments = treatmentsRepository.findById_category(id_category);
        if (mentalTreatments != null) {
            model.addAttribute("mentalTreatments", mentalTreatments);
        }
        return "/mentalTreatments";
    }

    @RequestMapping(value = "/facialTreatments", method = RequestMethod.GET)
    public String listFacial(Model model) {
        int id_category = 2;
        List<Treatments> facialTreatments = treatmentsRepository.findById_category(id_category);
        if (facialTreatments != null) {
            model.addAttribute("facialTreatments", facialTreatments);
        }
        return "/facialTreatments";
    }

    @RequestMapping(value = "/bodyTreatments", method = RequestMethod.GET)
    public String listBody(Model model) {
        int id_category = 1;
        int id_category2 = 4;
        List<Treatments> bodyTreatments = treatmentsRepository.findById_category(id_category, id_category2);
        if (bodyTreatments != null) {
            model.addAttribute("bodyTreatments", bodyTreatments);
        }
        return "/bodyTreatments";
    }

    /*@GetMapping("/user/myAccount")
    public String userMyAccount(){

        return "/user/myAccount";
    }*/
}
