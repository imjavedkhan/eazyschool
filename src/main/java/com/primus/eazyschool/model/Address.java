package com.primus.eazyschool.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
public class Address extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @NotBlank(message = "Address1 must not be blank")
    @Size(min=5, message = "Address1 must be of atleast 5 character")
    private String address1;

    private String address2;

    @NotBlank(message = "City must not be blank")
    @Size(min = 5, message = "City must be of atleast 5 character")
    private String city;

    @NotBlank(message = "State must not be blank")
    @Size(min = 5, message = "State must be of atleast 5 character")
    private String state;

    @NotBlank(message = "Zip code must not be blank")
    @Pattern(regexp = "(^$|[0-9]{6})", message = "Zip code must be of 6 digits")
    private int zipCode;


}
