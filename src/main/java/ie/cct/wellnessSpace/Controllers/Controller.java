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
public class Controller {

    private TreatmentsRepository treatmentsRepository;

    @Autowired
    public Controller(TreatmentsRepository treatmentsRepository) {

        this.treatmentsRepository = treatmentsRepository;
    }

    @RequestMapping(value = "/mental-treatments", method = RequestMethod.GET)
    public String listMental(Model model) {
        int id_category = 3;
        List<Treatments> mentalTreatments = treatmentsRepository.findById_category(id_category);
        if (mentalTreatments != null) {
            model.addAttribute("mentalTreatments", mentalTreatments);
        }
        return "mentalTreatments";
    }

    @RequestMapping(value = "/facial-treatments", method = RequestMethod.GET)
    public String listFacial(Model model) {
        int id_category = 2;
        List<Treatments> facialTreatments = treatmentsRepository.findById_category(id_category);
        if (facialTreatments != null) {
            model.addAttribute("facialTreatments", facialTreatments);
        }
        return "facialTreatments";
    }

    @RequestMapping(value = "/body-treatments", method = RequestMethod.GET)
    public String listBody(Model model) {
        int id_category = 1;
        int id_category2 = 4;
        List<Treatments> bodyTreatments = treatmentsRepository.findById_category(id_category, id_category2);
        if (bodyTreatments != null) {
            model.addAttribute("bodyTreatments", bodyTreatments);
        }
        return "bodyTreatments";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String logIn(){
        return "login";
    }
}
