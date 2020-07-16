package ie.cct.wellnessSpace.Services;

import ie.cct.wellnessSpace.Entities.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImp implements UserDetails {

    private Users user;
    public UserDetailsImp(Users user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();
        //Extract list of Roles and add it into a arraylist type GrantedAuthority
        this.user.getRoles().forEach(r ->{
            GrantedAuthority authority = new SimpleGrantedAuthority(r.getRole());
            authorities.add(authority);
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
