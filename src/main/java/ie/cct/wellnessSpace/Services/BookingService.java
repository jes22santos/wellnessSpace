package ie.cct.wellnessSpace.Services;

import ie.cct.wellnessSpace.Entities.*;
import ie.cct.wellnessSpace.Repository.*;
import ie.cct.wellnessSpace.Validator.BookingRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StatusRepository statusRepository;

    public List<Time> bookingAvailability(Date date, Integer id_staff){

        List<Time> timeFree = new ArrayList<Time>();
        List<TimeSlot> timeSlots = timeSlotRepository.findAll();
        List <Bookings> bookingsList = bookingRepository.findByDateandStaff(date, id_staff);
        boolean monday = false;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        monday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
        java.util.Date today = new java.util.Date();
        for (int i=0; i<timeSlots.size(); i++){
            timeFree.add(timeSlots.get(i).getTimeslot());
        }
        if ((monday) || (date.before(today))) {
            timeFree = null;
        }
        else if (bookingsList.size() > 0 ){
            for (int i = 0; i < bookingsList.size(); i++) {
                for (int j = 0; j < timeFree.size(); j++) {
                    if(bookingsList.get(i).getTime().equals(timeFree.get(j))){
                        timeFree.remove(j);
                    }
                }
            }
        }
        return (timeFree);

    }

    public void save(BookingRegister booking, String username)  {
        Integer id_user = userRepository.findByUsername(username).getId_user();
        Customers customer = new Customers(customerRepository.findByUser(id_user).getId_customer());
        Staffs staff = new Staffs (booking.getIdStaff());
        Status status = new Status(statusRepository.findByStatus("Booked").getId_status());
        Treatments treatment = new Treatments(booking.getIdTreatment());
        Bookings newBooking = new Bookings();
        newBooking.setDate(booking.getDate());
        newBooking.setTime(booking.getTime());
        newBooking.setNotes(booking.getNotes());
        newBooking.setCustomer(customer);
        newBooking.setStaff(staff);
        newBooking.setTreatment(treatment);
        newBooking.setStatus(status);
        bookingRepository.save(newBooking);
    }

    public List<Bookings> joinBookingSql(String user){

        Integer id_user = userRepository.findByUsername(user).getId_user();
        Customers customer = new Customers(customerRepository.findByUser(id_user).getId_customer());

        return(bookingRepository.findAllByCustomer(customer.getId_customer()));
    }
}
