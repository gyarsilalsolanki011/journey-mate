package com.gyarsilalsolanki011.journeymate.service;

import com.gyarsilalsolanki011.journeymate.model.dto.CustomerDTO;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CustomerService implements com.gyarsilalsolanki011.journeymate.service.Repository.CustomerService {
    @Override
    public CustomerDTO getCustomerByName(String fullName, Principal principal) {
        return null;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, Principal principal) {
        return null;
    }

    @Override
    public String deleteCustomerByEmail(String email, Principal principal) {
        return null;
    }
}
