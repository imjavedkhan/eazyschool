package com.primus.eazyschool.model;

import com.primus.eazyschool.annotation.FieldsValueMatch;
import com.primus.eazyschool.annotation.PasswordValidator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Password do not match"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email does not match"
        )
})
public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PersonId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "At least 3 character")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "at least 10 digits")
    @Column(name = "mobile_number")
    private String mobileNumber;

    @NotBlank(message = "Email not blank")
    @Email(message = "Valid email address")
    private String email;

    @NotBlank(message = "Email not blank")
    @Email(message = "Valid email address")
    @Transient
    private String confirmEmail;

    @NotBlank(message = "password must not be blank")
    @Size(min = 5, message = "At least 5 character")
    @PasswordValidator
    private String pwd;

    @NotBlank(message = "password must not be blank")
    @Size(min = 5, message = "At least 5 character")
    @Transient
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private EazyClass eazyClass;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "person_courses",
    joinColumns = {
            @JoinColumn(name = "person_id", referencedColumnName = "personId")},
    inverseJoinColumns = {
            @JoinColumn(name = "course_id", referencedColumnName = "courseId")})
    private Set<Courses> courses = new HashSet<>();
}
