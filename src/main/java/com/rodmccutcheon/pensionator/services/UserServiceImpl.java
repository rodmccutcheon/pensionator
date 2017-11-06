package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
}
