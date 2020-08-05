package ie.cct.wellnessSpace.Controllers;


import ie.cct.wellnessSpace.Entities.*;
import ie.cct.wellnessSpace.Repository.BookingRepository;
import ie.cct.wellnessSpace.Repository.ProdBookRepository;
import ie.cct.wellnessSpace.Repository.ProductRepository;
import ie.cct.wellnessSpace.Repository.StatusRepository;
import ie.cct.wellnessSpace.Services.BookingService;
import ie.cct.wellnessSpace.Services.ProdBookService;
import ie.cct.wellnessSpace.Services.StaffService;
import ie.cct.wellnessSpace.Services.UserServiceImp;
import ie.cct.wellnessSpace.Validator.ProdBookRegister;
import ie.cct.wellnessSpace.Validator.StaffRegister;
import ie.cct.wellnessSpace.Validator.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.util.List;


@org.springframework.stereotype.Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ProdBookService prodBookService;

    @Autowired
    private ProdBookRepository prodBookRepository;

    @GetMapping("/admin/myAccount")
    public String myAccountAdmin(){

        return "/admin/myAccount";
    }

    @RequestMapping(value = "/admin/bookingsControl", method = RequestMethod.GET)
    public String bookingControl(Model model, Principal principal, Date dateFrom, Date dateTo){
        String staff = principal.getName();
        if((dateFrom != null)&& (dateTo!= null)){
            List<Bookings> myBookings = bookingService.staffBookingsDates(staff, dateFrom, dateTo);
            model.addAttribute("bookings", myBookings);
        }
        else {
            List<Bookings> myBookings = bookingService.staffBookings(staff);
            model.addAttribute("bookings", myBookings);
        }
        return "/admin/bookingsControl";
    }
    @RequestMapping(value = "/admin/bookingDetails", method = RequestMethod.GET)
    public String getBookingDetails(@RequestParam(name="id") Integer id_booking, Model model){
        Bookings booking = bookingRepository.getOne(id_booking);
        List<Status> status = statusRepository.findAll();
        List<ProdBook> prodBookList = prodBookRepository.findAllByBooking(id_booking);
        model.addAttribute("booking", booking);
        model.addAttribute("status", status);
        model.addAttribute("productsList", prodBookList);
        return"admin/bookingDetails";
    }
    @RequestMapping(value = "/admin/changeStatus", method = RequestMethod.GET)
    public String cancelBooking(@RequestParam(name="id") Integer id_booking, @RequestParam(name="status") Integer id_status, RedirectAttributes redirectAttributes){
        Bookings bookingToUpdate = bookingRepository.getOne(id_booking);
        bookingToUpdate.setStatus(new Status(id_status));
        bookingRepository.save(bookingToUpdate);
        redirectAttributes.addAttribute("id", id_booking);
        return"redirect:/admin/bookingDetails/";
    }
    @RequestMapping(value="/admin/addProdBook", method = RequestMethod.GET)
    public String getProdBook(Model model, @RequestParam(name="id") Integer id_booking){
        List<Products> listproducts = productRepository.findAll();
        Bookings bookingToAdd = new Bookings(id_booking);
        model.addAttribute("products", listproducts);
        model.addAttribute("booking", bookingToAdd);
        return"admin/addProdBook";
    }
    @ModelAttribute("prodBook")
    public ProdBookRegister newProdBook() {

        return new ProdBookRegister();
    }
    @PostMapping("/admin/addProdBook")
    String addProfile(@ModelAttribute("prodBook")  ProdBookRegister prodBook, RedirectAttributes redirectAttributes) {

        prodBookService.save(prodBook);


        redirectAttributes.addAttribute("id", prodBook.getId_booking());

        return "redirect:/admin/bookingDetails/";
    }
    @RequestMapping(value = "/admin/products", method = RequestMethod.GET)
    public String listProducts(Model model, String search){
        if (search != null){
            List<Products> listProducts = productRepository.findAllByNameLike(search);
            model.addAttribute("products", listProducts);
        }else {
            List<Products> listProducts = productRepository.findAll();
            model.addAttribute("products", listProducts);
        }

        return "/admin/products";
    }
    @ModelAttribute("product")
    public Products newProducts() {

        return new Products();
    }
    @GetMapping("/admin/addNewProduct")
    public String addProduct(Model model) {

        return "/admin/addNewProduct";
    }

    @PostMapping("/admin/addNewProduct")
    String addProfile(@ModelAttribute("product")  Products product) {

        productRepository.save(product);

        return "redirect:/admin/products";
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
