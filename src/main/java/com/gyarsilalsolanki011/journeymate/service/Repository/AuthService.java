package com.gyarsilalsolanki011.journeymate.service.Repository;

import com.gyarsilalsolanki011.journeymate.model.dto.CustomerDTO;
import com.gyarsilalsolanki011.journeymate.model.dto.LoginResponse;

public interface AuthService {
    String registerCustomer(CustomerDTO customerDTO);
    LoginResponse loginCustomer(String gmail, String Password);
}
