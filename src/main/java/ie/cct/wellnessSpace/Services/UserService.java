package ie.cct.wellnessSpace.Services;

import ie.cct.wellnessSpace.Entities.Users;
import ie.cct.wellnessSpace.Validator.UserRegister;

public interface UserService  {
    void save(UserRegister userRegister);

    Users findByUsername(String username);

}
