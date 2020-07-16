package ie.cct.wellnessSpace.Services;

import ie.cct.wellnessSpace.Entities.Users;
import ie.cct.wellnessSpace.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Getting user from database throw user repository and creating a new user detail
        //instance passing this user as parameter
        Users user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        UserDetailsImp userDetailsImp = new UserDetailsImp(user);
        return userDetailsImp;
    }
}
