package com.primus.eazyschool.model;

import com.primus.eazyschool.annotation.PasswordValidator;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Profile {

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "At least 3 character")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "at least 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Email not blank")
    @Email(message = "Valid email address")
    private String email;

    @NotBlank(message="Address1 must not be blank")
    @Size(min=5, message="Address1 must be at least 5 characters long")
    private String address1;

    private String address2;

    @NotBlank(message="City must not be blank")
    @Size(min=5, message="City must be at least 5 characters long")
    private String city;

    @NotBlank(message="State must not be blank")
    @Size(min=5, message="State must be at least 5 characters long")
    private String state;

    @NotBlank(message="Zip Code must not be blank")
    @Pattern(regexp="(^$|[0-9]{6})",message = "Zip Code must be 5 digits")
    private String zipCode;

}
