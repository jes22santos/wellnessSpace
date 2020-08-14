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

    @Autowired
    private StaffRepository staffRepository;
    /*
        This method is responsible for checking the time slot availability for an specific day and professional.
        it first checks if the day is a monday or a day before the actual day
        and then get all bookings of this professional for that day and compares time by time, removing from the free time list the
        time that is already booked.
        and returns the free time list
     */
    public List<Time> bookingAvailability(Date date, Integer id_staff){

        List<Time> timeFree = new ArrayList<Time>();
        List<TimeSlot> timeSlots = timeSlotRepository.findAll();
        List <Bookings> bookingsList = bookingRepository.findByDateandStaff(date, id_staff);
        Status booked = new Status(1);
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
                    if((bookingsList.get(i).getTime().equals(timeFree.get(j))) && (bookingsList.get(i).getStatus().getId_status().equals(booked.getId_status()))){
                        timeFree.remove(j);
                    }
                }
            }
        }
        return (timeFree);

    }

    /*
        This method is responsible to save the booking in the database using JPA repository, but before saving, it creates new
        instance of all attributes and set them based on the information that came from parameter as a booking register instance
     */

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

    /*
        Method used to bring a list of all bookings based on the customer ID
     */
    public List<Bookings> joinBookingSql(String user){

        Integer id_user = userRepository.findByUsername(user).getId_user();
        Customers customer = new Customers(customerRepository.findByUser(id_user).getId_customer());

        return(bookingRepository.findAllByCustomer(customer.getId_customer()));
    }
    /*
        Method used to bring a list of all bookings based on the staff id
     */
    public List<Bookings> staffBookings(String staff){

        Integer id_user = userRepository.findByUsername(staff).getId_user();
        Staffs getStaff = new Staffs(staffRepository.findByUser(id_user).getId_staff());

        return(bookingRepository.findAllByStaff(getStaff.getId_staff()));
    }
    /*
        method used to filter by dates bookings of a staff
     */
    public List<Bookings> staffBookingsDates(String staff, Date dateFrom, Date dateTo){

        Integer id_user = userRepository.findByUsername(staff).getId_user();
        Staffs getStaff = new Staffs(staffRepository.findByUser(id_user).getId_staff());

        return(bookingRepository.findAllByStaffAndDate(getStaff.getId_staff(), dateFrom, dateTo));
    }
}
