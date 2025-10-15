package com.gyarsilalsolanki011.journeymate.service;

import com.gyarsilalsolanki011.journeymate.exception.EntityNotFoundException;
import com.gyarsilalsolanki011.journeymate.exception.InternalServiceException;
import com.gyarsilalsolanki011.journeymate.mapper.CustomerMapper;
import com.gyarsilalsolanki011.journeymate.model.dto.CustomerDTO;
import com.gyarsilalsolanki011.journeymate.model.dto.LoginResponse;
import com.gyarsilalsolanki011.journeymate.model.entity.Customer;
import com.gyarsilalsolanki011.journeymate.repository.CustomerRepository;
import com.gyarsilalsolanki011.journeymate.service.Repository.UserDetailsService;
import com.gyarsilalsolanki011.journeymate.util.JwtUtil;
import com.gyarsilalsolanki011.journeymate.util.PasswordUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements com.gyarsilalsolanki011.journeymate.service.Repository.AuthService {
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;

    public AuthService(CustomerRepository customerRepository, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String registerCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomerEntity(customerDTO);
        customerRepository.save(customer);
        return "Customer Registered Successfully";
    }

    @Override
    public LoginResponse loginCustomer(String email, String password) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Customer Not found with email"));
        if (!PasswordUtil.encode(password).equals(customer.getPassword())){
            throw new InternalServiceException("Invalid Credential");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String token = jwtUtil.generateToken(authentication);
        return new LoginResponse("Logged Successfully!", token);
    }
}
