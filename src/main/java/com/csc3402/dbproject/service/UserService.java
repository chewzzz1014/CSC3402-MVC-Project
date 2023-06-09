package com.csc3402.dbproject.service;

import com.csc3402.dbproject.dto.UserDto;
import com.csc3402.dbproject.model.Customer;
import com.csc3402.dbproject.repository.CustomerRepository;

public interface UserService {
    void saveUser(UserDto userDto);
    Customer findUserByEmail(String email);
}