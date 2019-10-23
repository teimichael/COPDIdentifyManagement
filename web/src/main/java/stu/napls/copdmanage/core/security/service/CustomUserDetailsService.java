package stu.napls.copdmanage.core.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import stu.napls.copdmanage.core.security.model.AuthUser;
import stu.napls.copdmanage.model.Login;
import stu.napls.copdmanage.repository.LoginRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yepeng Ding
 */
@Service(value = "userDetailService")
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Login login = loginRepository.findByUsername(s);
        if (login == null || login.getUser() == null) {
            throw new UsernameNotFoundException("User does not exist!");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        // TODO Multiple-roles is possible
        authorities.add(new SimpleGrantedAuthority(login.getUser().getRole()));
        return new AuthUser(login.getUsername(), login.getPassword(), authorities);
    }
}
