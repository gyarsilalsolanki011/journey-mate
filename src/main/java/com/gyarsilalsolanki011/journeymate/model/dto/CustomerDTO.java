package com.gyarsilalsolanki011.journeymate.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @NotBlank(message = "Full Name is required")
    @Size(max = 50, message = "Full Name can have at most 50 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email can have at most 100 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,20}$",
            message = "Password must include uppercase, lowercase, number, and special character"
    )
    private String password;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone must be between 10 to 15 digits")
    private String phoneNumber;
}
