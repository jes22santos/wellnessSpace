package ie.cct.wellnessSpace.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class AdminController {
        @GetMapping("/admin/myAccount")
        public String index(){
            return "admin/myAccount";
        }
}
