package stu.napls.copdmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stu.napls.copdmanage.model.User;

/**
 * @author Yepeng Ding
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
