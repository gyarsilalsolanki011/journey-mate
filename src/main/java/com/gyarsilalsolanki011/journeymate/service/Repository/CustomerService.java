package com.gyarsilalsolanki011.journeymate.service.Repository;

import com.gyarsilalsolanki011.journeymate.model.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO getCustomerById(Integer customerId);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    String deleteCustomer();
}