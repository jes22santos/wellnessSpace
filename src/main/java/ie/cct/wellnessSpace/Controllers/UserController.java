package ie.cct.wellnessSpace.Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import ie.cct.wellnessSpace.Entities.*;
import ie.cct.wellnessSpace.Repository.*;
import ie.cct.wellnessSpace.Services.BookingService;
import ie.cct.wellnessSpace.Services.CustomerService;
import ie.cct.wellnessSpace.Validator.BookingRegister;
import ie.cct.wellnessSpace.Validator.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TreatmentsRepository treatmentsRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;



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

    @RequestMapping(value = "user/booking", method = RequestMethod.GET)
    public String listbook(Model model) {

        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList != null) {
            model.addAttribute("categoryList", categoryList);
        }
        return "user/booking";
    }
    @ResponseBody
    @RequestMapping(value="/user/loadTreatmentsByCategory/{id}", method = RequestMethod.GET)
    public String loadTreatmentsByCategory(@PathVariable("id") int id){
        Gson gson = new Gson();
        return gson.toJson(treatmentsRepository.findById_category(id));
    }

    @ResponseBody
    @RequestMapping(value="/user/loadStaffsByCategory/{id}", method = RequestMethod.GET)
    public String loadStaffsByCategory(@PathVariable("id") int id){
        Gson gson = new Gson();
        return gson.toJson(staffRepository.findByCategory(id));
    }

    @ResponseBody
    @RequestMapping(value="/user/loadTimeSlotsFree/{date}/conf/{professionalID}", method = RequestMethod.GET)
    public List<Time> loadTimeSlotsFree(@PathVariable("date")Date date, @PathVariable("professionalID") int professionalID){


        return (bookingService.bookingAvailability(date,professionalID));
    }

    @ModelAttribute("booking")
    public BookingRegister booking() {

        return new BookingRegister();
    }
    @PostMapping("/user/booking")
    String addBooking(@ModelAttribute("booking") BookingRegister booking, Principal principal) {
        String username = principal.getName();
        bookingService.save(booking, username);

        return "redirect:/user/myBookings";
    }

    @RequestMapping(value = "/user/myBookings", method = RequestMethod.GET)
    public String userMyBookings(Model model, Principal principal){
        String user = principal.getName();
        List<Bookings> myBookings = bookingService.joinBookingSql(user);
        model.addAttribute("bookings", myBookings);
        return "user/myBookings";
    }


}
