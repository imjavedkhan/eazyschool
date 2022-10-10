package com.primus.eazyschool.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "class")
public class EazyClass extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;

    @NotBlank(message = "Name must not be blank")
    @Size(min=3, message = "name must be of at least 3 character")
    private String name;

    @OneToMany(mappedBy = "eazyClass", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST, targetEntity = Person.class)
    private Set<Person> persons;
}
