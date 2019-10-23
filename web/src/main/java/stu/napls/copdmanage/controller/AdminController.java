package stu.napls.copdmanage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu.napls.copdmanage.model.Admin;
import stu.napls.copdmanage.service.AdminService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yepeng Ding
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @GetMapping(value = "/get/list")
    public List<Admin> getList() {
        return adminService.findAllAdmin();
    }

    @PostMapping(value = "/create")
    public Admin create() {
        Admin admin = new Admin();
        admin.setPrivilege("super root");

        return adminService.createAdmin(admin);
    }
}
