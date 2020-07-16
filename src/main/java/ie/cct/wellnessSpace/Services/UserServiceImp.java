package ie.cct.wellnessSpace.Services;

import ie.cct.wellnessSpace.Entities.Role;
import ie.cct.wellnessSpace.Entities.Users;
import ie.cct.wellnessSpace.Repository.RoleRepository;
import ie.cct.wellnessSpace.Repository.UserRepository;
import ie.cct.wellnessSpace.Validator.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public void save(UserRegister userRegister) {

        //user.setRoles(new HashSet<>(roleRepository.findAll()));
        Users user = new Users();
        user.setUsername(userRegister.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userRegister.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        userRepository.save(user);
    }


    @Override
    public Users findByUsername(String username) {

        return userRepository.findByUsername(username);
    }
}
