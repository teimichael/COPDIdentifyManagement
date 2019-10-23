package stu.napls.copdmanage.service.impl;

import org.springframework.stereotype.Service;
import stu.napls.copdmanage.model.Admin;
import stu.napls.copdmanage.repository.AdminRepository;
import stu.napls.copdmanage.service.AdminService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yepeng Ding
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminRepository adminRepository;


    @Override
    public List<Admin> findAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
}
