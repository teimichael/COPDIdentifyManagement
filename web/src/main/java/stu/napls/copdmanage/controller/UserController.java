package stu.napls.copdmanage.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu.napls.copdmanage.core.constant.Role;
import stu.napls.copdmanage.core.response.Response;
import stu.napls.copdmanage.model.Login;
import stu.napls.copdmanage.model.User;
import stu.napls.copdmanage.repository.LoginRepository;
import stu.napls.copdmanage.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Yepeng Ding
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private LoginRepository loginRepository;

    @GetMapping(value = "/get/list")
    public List<User> getList() {
        return userService.findAllUser();
    }

    @GetMapping(value = "/get/info")
    public Response getInfo(HttpSession session) {
        String username =
                String.valueOf(session.getAttribute("username"));
        Login login = loginRepository.findByUsername(username);
        return Response.success(login.getUser());
    }

    @PostMapping(value = "/create")
    public Response create() {
        User user = new User();
        user.setName("ding");
        user.setRole(String.valueOf(Role.USER));
        Login login = new Login();
        login.setUsername("ding");
        login.setPassword("ding");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        login.setPassword(bCryptPasswordEncoder.encode(login.getPassword()));
        login.setUser(userService.createUser(user));
        return Response.success(loginRepository.save(login));
    }
}
