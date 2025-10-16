package com.gyarsilalsolanki011.journeymate.controller;

import com.gyarsilalsolanki011.journeymate.model.dto.CustomerDTO;
import com.gyarsilalsolanki011.journeymate.service.Repository.CustomerService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Validated
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{fullName}")
    public ResponseEntity<CustomerDTO> getCustomerByName(
            @NotBlank @PathVariable String fullName,
            Principal principal
    ){
        return ResponseEntity.ok(customerService.getCustomerByName(fullName, principal));
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @RequestBody CustomerDTO customerDTO,
            Principal principal
    ){
        return ResponseEntity.ok(customerService.updateCustomer(customerDTO, principal));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomerByEmail(
            @NotBlank @RequestParam String email,
            Principal principal
    ){
        return ResponseEntity.ok(customerService.deleteCustomerByEmail(email, principal));
    }
}
