package com.primus.eazyschool.model;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contactId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "name must be at least 3 character")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "at least 10 digits")
    @Column(name = "mobile_num")
    private String mobileNum;

    @NotBlank(message = "Email must not be blank")
    @Email
    private String email;

    @NotBlank(message = "Subject must not be blank")
    @Size(min = 3, message = "Subject must be at least 5 character")
    private String subject;

    @NotBlank(message =   "Message must not be blank")
    @Size(min = 3, message = "message must be at least 10 character")
    private String message;

    private String status;

}
