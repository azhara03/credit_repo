package com.Credit.credit.Service.Impl;

import com.Credit.credit.Entity.User;
import com.Credit.credit.Repository.UserRepository;
import com.Credit.credit.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    //@Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}
