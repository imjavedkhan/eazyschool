package com.primus.eazyschool.model;

import lombok.Data;

@Data
public class Holiday extends BaseEntity {

    private  String day;
    private  String reason;
    private  HolidayType holidayType;

}
