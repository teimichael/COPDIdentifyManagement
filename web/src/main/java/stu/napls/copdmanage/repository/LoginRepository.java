package stu.napls.copdmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stu.napls.copdmanage.model.Login;

/**
 * @author Yepeng Ding
 */
public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByUsername(String username);
}
