package stu.napls.copdmanage.service.impl;

import org.springframework.stereotype.Service;
import stu.napls.copdmanage.model.User;
import stu.napls.copdmanage.repository.UserRepository;
import stu.napls.copdmanage.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author Yepeng Ding
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        User user = null;
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            user = result.get();
        }
        return user;
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
