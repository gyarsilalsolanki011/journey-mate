package com.gyarsilalsolanki011.journeymate.service;

import com.gyarsilalsolanki011.journeymate.model.entity.Customer;
import com.gyarsilalsolanki011.journeymate.repository.CustomerRepository;
import com.gyarsilalsolanki011.journeymate.service.Repository.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    public CustomUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new User(
                customer.getEmail(),
                customer.getPassword(),
                Collections.emptyList() // You can later replace this with roles or authorities
        );

    }
}
