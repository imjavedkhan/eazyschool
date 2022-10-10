package com.primus.eazyschool.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Courses extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String name;

    private String fees;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Person> persons = new HashSet<>();
}
