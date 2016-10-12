package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.UserInfo;
import ru.dz.repository.UserRepository;

/**
 * Created by ainur on 11.10.2016.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserInfo getUser(Long id) {
        return userRepository.findOne(id);
    }

    public void addUser(UserInfo user) {
        userRepository.save(user);
    }

    public UserInfo findUserByVkID(Integer vkId) {
        return userRepository.findByVkId(vkId);
    }
}
