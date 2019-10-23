package stu.napls.copdmanage.service;

import stu.napls.copdmanage.model.User;

import java.util.List;

/**
 * @author Yepeng Ding
 */
public interface UserService {
    User findById(Long id);

    List<User> findAllUser();

    User createUser(User user);
}
