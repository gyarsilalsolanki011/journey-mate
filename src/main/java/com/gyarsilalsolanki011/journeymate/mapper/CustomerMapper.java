package com.gyarsilalsolanki011.journeymate.mapper;

import com.gyarsilalsolanki011.journeymate.model.dto.CustomerDTO;
import com.gyarsilalsolanki011.journeymate.model.entity.Customer;
import com.gyarsilalsolanki011.journeymate.util.PasswordUtil;

public class CustomerMapper {
    public static CustomerDTO toCustomerDto(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        dto.setFullName(customer.getFullName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhone());
        return dto;
    }

    public static Customer toCustomerEntity(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setFullName(customerDTO.getFullName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(PasswordUtil.encode(customerDTO.getPassword()));
        customer.setPhone(customerDTO.getPhoneNumber());
        return customer;
    }
}
