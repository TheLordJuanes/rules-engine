package com.bootcamp.prft.rulesEngine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;

    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Incorrect email format")
    private String email;

    @NotNull(message = "Attribute 'password' cannot be null.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[#$%@]).+$", message = "Incorrect password format")
    private String password;

    @Pattern(regexp = "^\\+57\\d{10}", message = "Incorrect phone number format")
    private String phoneNumber;

    @NotNull(message = "Attribute 'first name' cannot be null.")
    @Pattern(regexp = "[a-zA-Z ]{0,120}", message = "The first name mustn't contain more than 120 characters, nor special characters or numbers.")
    private String firstName;

    @NotNull(message = "Attribute 'last name' cannot be null.")
    @Pattern(regexp = "[a-zA-Z ]{0,120}", message = "The last name mustn't contain more than 120 characters, nor special characters or numbers.")
    private String lastName;
}