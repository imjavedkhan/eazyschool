package com.primus.eazyschool.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity {

    @Id
    private  String day;
    private  String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "holidaytype")
    private  HolidayType holidayType;

}
