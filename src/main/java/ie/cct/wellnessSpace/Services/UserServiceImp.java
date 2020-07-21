package ie.cct.wellnessSpace.Services;

import ie.cct.wellnessSpace.Entities.Role;
import ie.cct.wellnessSpace.Entities.Users;
import ie.cct.wellnessSpace.Repository.RoleRepository;
import ie.cct.wellnessSpace.Repository.UserRepository;
import ie.cct.wellnessSpace.Validator.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Override
    public void save(UserRegister userRegister) {

        Users user = new Users();
        Role role = new Role(roleRepository.findByRole("ROLE_USER").getId_role());
        user.setUsername(userRegister.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userRegister.getPassword()));
        user.setRoles(role);
        userRepository.save(user);
    }


    @Override
    public Users findByUsername(String username) {

        return userRepository.findByUsername(username);
    }
}
