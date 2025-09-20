package com.gyarsilalsolanki011.journeymate.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name can have at most 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email can have at most 100 characters")
    private String email;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone must be between 10 to 15 digits")
    private String phoneNumber;
}
