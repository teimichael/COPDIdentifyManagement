package stu.napls.copdmanage.service;

import stu.napls.copdmanage.model.Admin;

import java.util.List;

/**
 * @author Yepeng Ding
 */
public interface AdminService {
    List<Admin> findAllAdmin();

    Admin createAdmin(Admin admin);
}
