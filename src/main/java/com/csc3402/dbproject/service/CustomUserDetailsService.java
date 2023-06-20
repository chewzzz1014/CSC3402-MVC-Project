package com.csc3402.dbproject.service;

import com.csc3402.dbproject.model.Customer;
import com.csc3402.dbproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(usernameOrEmail);
        if (customer != null) {
            return new org.springframework.security.core.userdetails.User(
                    customer.getEmail(),
                    customer.getPassword(),
                    Collections.emptyList() // No authorities
            );
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
