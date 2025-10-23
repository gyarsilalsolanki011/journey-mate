package com.gyarsilalsolanki011.journeymate.service.Repository;

import com.gyarsilalsolanki011.journeymate.model.dto.CustomerDTO;

import java.security.Principal;

public interface CustomerService {
    CustomerDTO getCustomerByName(String fullName, Principal principal);
    CustomerDTO updateCustomer(CustomerDTO customerDTO, Principal principal);
    String deleteCustomerByEmail(String email, Principal principal);
}