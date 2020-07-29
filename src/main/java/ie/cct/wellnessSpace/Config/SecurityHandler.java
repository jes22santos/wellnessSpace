package ie.cct.wellnessSpace.Config;

import ie.cct.wellnessSpace.Entities.Customers;
import ie.cct.wellnessSpace.Entities.Staffs;
import ie.cct.wellnessSpace.Entities.Users;
import ie.cct.wellnessSpace.Repository.CustomerRepository;
import ie.cct.wellnessSpace.Repository.StaffRepository;
import ie.cct.wellnessSpace.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SecurityHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    CustomerRepository customerRepository;
    /*
    Class to handle the redirection after login, based on the user's role it
    will open a different page
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        Object principal= authentication.getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        int id_user = userRepository.findByUsername(username).getId_user();
        Staffs staff = staffRepository.findByUser(id_user);
        Customers customers = customerRepository.findByUser(id_user);

        /*
        handler sucess login, it check if the user or admin has already a profile, if no it means that
        it is a new user and need to fill the profile. If the user has already filled their profile
        it redirect to the my account page
         */
        if (roles.contains("ROLE_ADMIN") && (staff == null)) {
            response.sendRedirect("/admin/addProfileAdmin");
        } else if (roles.contains("ROLE_USER") && (customers ==null)){
            response.sendRedirect("/user/addProfile");
        } else if (roles.contains("ROLE_ADMIN") && (staff != null)){
            response.sendRedirect("/admin/myAccount");
        } else {
            response.sendRedirect("/user/booking");
        }
    }
}
