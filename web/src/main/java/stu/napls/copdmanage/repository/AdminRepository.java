package stu.napls.copdmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stu.napls.copdmanage.model.Admin;

/**
 * @author Yepeng Ding
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
