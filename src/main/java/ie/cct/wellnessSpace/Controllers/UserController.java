package ie.cct.wellnessSpace.Controllers;

import ie.cct.wellnessSpace.Entities.Treatments;
import ie.cct.wellnessSpace.Repository.TreatmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@org.springframework.stereotype.Controller
@RequestMapping("/")
public class UserController {


        @GetMapping("/user/myAccount")
        public String userMyAccount(){

            return "/user/myAccount";
        }



}
