package ie.cct.wellnessSpace.Controllers;

import ie.cct.wellnessSpace.Entities.Treatments;
import ie.cct.wellnessSpace.Repository.TreatmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/user")
public class UserController {


        @GetMapping("myAccount")
        public String index(){
            return "/myAccount";
        }



}
