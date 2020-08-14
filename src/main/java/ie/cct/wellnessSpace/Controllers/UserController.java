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

    @Autowired
    private ProdBookRepository prodBookRepository;

    @Autowired
    private UserRepository userRepository;
//-------------------------------------------------------------------------------------------------------------------//
    /*
        Endpoint to open get request myAccount for users
     */
    @GetMapping("/user/myAccount")
    public String userMyAccount(Model model, Principal principal){
        Integer user = userRepository.findByUsername(principal.getName()).getId_user();
        Customers customer = customerRepository.findByUser(user);
        model.addAttribute("customer", customer);
        return "/user/myAccount";
    }
//-------------------------------------------------------------------------------------------------------------------//
    /*
        Add profile to a customer linking it to a user
     */
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

//-------------------------------------------------------------------------------------------------------------------//
    /*
        Endpoint to open user profile details, it check the authority from the user who made the request
        when request comes from admin user this method get the customer ID passed by parameter
        when request comes from customer user this method get the username and find their own profile
     */
    @GetMapping("/user/profile")
    public String userProfile(Model model, Authentication authentication, Integer id){

        boolean userRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
        if(userRole){
            Integer user = userRepository.findByUsername(authentication.getName()).getId_user();
            Customers customer = customerRepository.findByUser(user);
            model.addAttribute("customer", customer);
        }
        else {
            Customers customer = customerRepository.findById_customer(id);
            model.addAttribute("customer", customer);
        }
        return "/user/profile";
    }


//-------------------------------------------------------------------------------------------------------------------//
    /*
        Booking get requests, and bring the category list for the first dropdown
     */
    @RequestMapping(value = "user/booking", method = RequestMethod.GET)
    public String listbook(Model model) {

        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList != null) {
            model.addAttribute("categoryList", categoryList);
        }
        return "user/booking";
    }
//-------------------------------------------------------------------------------------------------------------------//
    /*
        The next 3 methods are for a list from database and retorn a json file to the jquery ajax function in the HTML page
        they are for the dynamic cascading dropdowns for treatment / professional and time available, all depends on the first
        dropdown category.
        code based on tutorial by Learning Programming, 2019
     */
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
//-------------------------------------------------------------------------------------------------------------------//
    /*
        booking post request, using booking service class to save the booking in  the database
     */
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
//-------------------------------------------------------------------------------------------------------------------//
    /*
        Bring a list of all bookings to the looged user
     */
    @RequestMapping(value = "/user/myBookings", method = RequestMethod.GET)
    public String userMyBookings(Model model, Principal principal){
        String user = principal.getName();
        List<Bookings> myBookings = bookingService.joinBookingSql(user);
        model.addAttribute("bookings", myBookings);
        return "user/myBookings";
    }
//-------------------------------------------------------------------------------------------------------------------//
    /*
        Brings booking details finding by the ID passed by parameter from myBookings
     */
    @RequestMapping(value = "/user/bookingDetails", method = RequestMethod.GET)
    public String getBookingDetails(@RequestParam(name="id") Integer id_booking, Model model){
        Bookings booking = bookingRepository.getOne(id_booking);
        List<ProdBook> prodBookList = prodBookRepository.findAllByBooking(id_booking);
        model.addAttribute("booking", booking);
        model.addAttribute("productsList", prodBookList);
        return"user/bookingDetails";
    }
//-------------------------------------------------------------------------------------------------------------------//
    /*
        Method to change the booking status to cancelled
     */
    @RequestMapping(value = "/user/cancelBooking", method = RequestMethod.GET)
    public String cancelBooking(@RequestParam(name="id") Integer id_booking, Model model){
        Bookings bookingToUpdate = bookingRepository.getOne(id_booking);
        bookingToUpdate.setStatus(new Status(3));
        bookingRepository.save(bookingToUpdate);

        return"redirect:/user/myBookings";
    }

}
