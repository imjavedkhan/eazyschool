package com.primus.eazyschool.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Contact {

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "name must be at least 3 character")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "at least 10 digits")
    private String mobileNum;

    @NotBlank(message = "Email must not be blank")
    @Email
    private String email;

    @NotBlank(message = "Subject must not be blank")
    @Size(min = 3, message = "Subject must be at least 5 character")
    private String subject;

    @NotBlank(message = "Message must not be blank")
    @Size(min = 3, message = "message must be at least 10 character")
    private String message;
}
