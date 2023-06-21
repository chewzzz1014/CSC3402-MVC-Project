package com.csc3402.dbproject.service;

import com.csc3402.dbproject.dto.UserDto;
import com.csc3402.dbproject.model.Customer;
import com.csc3402.dbproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        Customer customer = new Customer(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()));
        customerRepository.save(customer);
    }

    @Override
    public Customer findUserByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}