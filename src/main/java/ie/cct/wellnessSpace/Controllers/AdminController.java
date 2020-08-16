package ie.cct.wellnessSpace.Controllers;


import com.itextpdf.text.DocumentException;
import ie.cct.wellnessSpace.Entities.*;
import ie.cct.wellnessSpace.Repository.*;
import ie.cct.wellnessSpace.Services.*;
import ie.cct.wellnessSpace.Validator.ProdBookRegister;
import ie.cct.wellnessSpace.Validator.StaffRegister;
import ie.cct.wellnessSpace.Validator.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffRepository staffRepository;
//-------------------------------------------------------------------------------------------------------------------//
    /*
        Endpoint Get request for Admin myAccount
     */
    @GetMapping("/admin/myAccount")
    public String myAccountAdmin(Model model, Principal principal){
        Integer user = userRepository.findByUsername(principal.getName()).getId_user();
        Staffs staff = staffRepository.findByUser(user);
        Date today = Date.valueOf(LocalDate.now());
        List<Bookings> todayBookings = bookingService.staffBookingsDates(principal.getName(),today, today);
        model.addAttribute("staff", staff);
        model.addAttribute("bookings", todayBookings);
        return "/admin/myAccount";
    }
//-------------------------------------------------------------------------------------------------------------------//
    /*
        List all booking for the logged staff
     */
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
//-------------------------------------------------------------------------------------------------------------------//
    /*
        Getting booking by Id passed by parameter from BookingControl, and open details
     */
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
//-------------------------------------------------------------------------------------------------------------------//
    /*
        Method to change booking status, from booking details.
     */
    @RequestMapping(value = "/admin/changeStatus", method = RequestMethod.GET)
    public String cancelBooking(@RequestParam(name="id") Integer id_booking, @RequestParam(name="status") Integer id_status, RedirectAttributes redirectAttributes){
        Bookings bookingToUpdate = bookingRepository.getOne(id_booking);
        bookingToUpdate.setStatus(new Status(id_status));
        bookingRepository.save(bookingToUpdate);
        redirectAttributes.addAttribute("id", id_booking);
        return"redirect:/admin/bookingDetails/";
    }
//-------------------------------------------------------------------------------------------------------------------//
    /*
        List all products linked to a booking with the ID in the parameter
     */
    @RequestMapping(value="/admin/addProdBook", method = RequestMethod.GET)
    public String getProdBook(Model model, @RequestParam(name="id") Integer id_booking){
        List<Products> listproducts = productRepository.findAll();
        Bookings bookingToAdd = new Bookings(id_booking);
        model.addAttribute("products", listproducts);
        model.addAttribute("booking", bookingToAdd);
        return"admin/addProdBook";
    }
//-------------------------------------------------------------------------------------------------------------------//
    /*
        Add a product to a booking
     */
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
//-------------------------------------------------------------------------------------------------------------------//
     /*
        delete a product from a booking, and add the quantity back to the product stock.
     */
    @RequestMapping(value = "/admin/deleteProdBook", method = RequestMethod.GET)
    public String deleteProdBook(@RequestParam(name="id") Integer id_prodBook, Model model, RedirectAttributes redirectAttributes){
        ProdBook toCancel = prodBookRepository.getOne(id_prodBook);
        Integer idBooking = toCancel.getBooking().getId_booking();
        prodBookService.delete(toCancel);
        redirectAttributes.addAttribute("id", idBooking);
        return "redirect:/admin/bookingDetails/";
    }
//-------------------------------------------------------------------------------------------------------------------//
    /*
        List all products registed
     */
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
//-------------------------------------------------------------------------------------------------------------------//
    /*
        Add a new product to the stock
     */
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
//-------------------------------------------------------------------------------------------------------------------//
    /*
        Add new user type Admin
     */
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
//-------------------------------------------------------------------------------------------------------------------//

    /*
        Add Profile to a staff linked to an admin user
     */
    @ModelAttribute("staff")
    public StaffRegister staffRegister() {

        return new StaffRegister();
    }

    @GetMapping("/admin/addProfileAdmin")
    public String addProfileAdmin(Model model) {

        return "admin/addProfileAdmin";
    }

    @PostMapping("/admin/addProfileAdmin")
    String addProfile(@ModelAttribute("staff")  StaffRegister staff, Principal principal) {
        String username = principal.getName();
        staffService.save(staff, username);

        return "redirect:/admin/myAccount";
    }
//-------------------------------------------------------------------------------------------------------------------//
    /*
        Open invoice page, showing the total price of the booking
     */
    @RequestMapping(value = "/admin/invoice", method = RequestMethod.GET)
    public String getInvoice(@RequestParam(name="id") Integer id_booking, Model model){
        Bookings getBooking = bookingRepository.getOne(id_booking);
        List<ProdBook> listProdBook = prodBookRepository.findAllByBooking(id_booking);
        double priceTotal = 0; 
        for(int i =0; i< listProdBook.size(); i++){
            priceTotal=+(listProdBook.get(i).getQuantity()*listProdBook.get(i).getProduct().getCost());
        }
        priceTotal = priceTotal + getBooking.getTreatment().getCost();
        model.addAttribute("booking", getBooking);
        model.addAttribute("productsList", listProdBook);
        model.addAttribute("priceTotal", priceTotal);
        return"admin/invoice";
    }

//-------------------------------------------------------------------------------------------------------------------//

    /*
        Generting and pdf invoice using the invoice Service
     */
    @RequestMapping(value = "/admin/invoiceDownload", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> download(@RequestParam(name="id") Integer id_booking) throws IOException, DocumentException {
        Bookings booking = bookingRepository.getOne(id_booking);
        List<ProdBook> listProdBook = prodBookRepository.findAllByBooking(id_booking);
        ByteArrayInputStream newPdf = InvoiceService.generatePdfInvoice(booking, listProdBook);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Booking-invoice.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(newPdf));
    }




}
