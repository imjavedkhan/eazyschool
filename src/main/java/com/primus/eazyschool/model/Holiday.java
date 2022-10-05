package com.primus.eazyschool.model;

import lombok.Data;

@Data
public class Holiday {

    private final String day;
    private final String reason;
    private final HolidayType holidayType;

}
