package com.gyarsilalsolanki011.journeymate.service;

import com.gyarsilalsolanki011.journeymate.exception.EntityNotFoundException;
import com.gyarsilalsolanki011.journeymate.exception.InternalServiceException;
import com.gyarsilalsolanki011.journeymate.mapper.CustomerMapper;
import com.gyarsilalsolanki011.journeymate.model.dto.CustomerDTO;
import com.gyarsilalsolanki011.journeymate.model.entity.Customer;
import com.gyarsilalsolanki011.journeymate.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class CustomerService implements com.gyarsilalsolanki011.journeymate.service.Repository.CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO getCustomerByName(String fullName, Principal principal) {
        String email = principal.getName();
        customerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with email: " + email));

        Customer customer = customerRepository.findByFullName(fullName)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with fullName: " + fullName));

        return CustomerMapper.toCustomerDto(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, Principal principal) {
        String authEmail = principal.getName();
        customerRepository.findByEmail(authEmail)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with email: " + authEmail));

        Customer customer = new Customer();
        String email = authEmail;
        if (!customerDTO.getFullName().isEmpty()){
            customer.setFullName(customerDTO.getFullName());
        } else if (!customerDTO.getPassword().isEmpty()) {
            customer.setPassword(customerDTO.getPassword());
        } else if (!customerDTO.getPhoneNumber().isEmpty()) {
            customer.setPhone(customerDTO.getPhoneNumber());
        } else if (!customerDTO.getEmail().isEmpty()) {
            email = customerDTO.getEmail();
            customer.setEmail(customerDTO.getEmail());
        } else {
            throw new InternalServiceException("Please do provide data");
        }

        customerRepository.save(customer);
        return CustomerMapper.toCustomerDto(
                customerRepository.findByEmail(email)
                        .orElseThrow(() -> new EntityNotFoundException("Customer Not found!"))
        );
    }

    @Override
    public String deleteCustomerByEmail(String email, Principal principal) {
        if (email.equals(principal.getName())) {
            Customer customer = customerRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("Customer not found with email: " + email));
            customerRepository.delete(customer);
        }

        return "Customer deleted successfully!";
    }
}
