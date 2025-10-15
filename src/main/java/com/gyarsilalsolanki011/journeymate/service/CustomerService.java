package com.gyarsilalsolanki011.journeymate.service;

import com.gyarsilalsolanki011.journeymate.model.dto.CustomerDTO;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements com.gyarsilalsolanki011.journeymate.service.Repository.CustomerService {
    @Override
    public CustomerDTO getCustomerById(Integer customerId) {
        return null;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public String deleteCustomer() {
        return null;
    }
}
