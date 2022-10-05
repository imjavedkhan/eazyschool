package com.primus.eazyschool.controller;

import com.primus.eazyschool.model.Holiday;
import com.primus.eazyschool.model.HolidayType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display, Model model){

        if(null != display && display.equals("all")){
            model.addAttribute("festival",true);
            model.addAttribute("federal",true);
        } else if (null != display && display.equals("festival")) {
            model.addAttribute("festival",true);
        } else if (null != display && display.equals("federal")) {
            model.addAttribute("federal",true);
        }


        List<Holiday> holidays = Arrays.asList(
                new Holiday(" Jan 1 ","New Year's Day", HolidayType.FESTIVAL),
                new Holiday(" Oct 31 ","Halloween", HolidayType.FESTIVAL),
                new Holiday(" Nov 24 ","Thanksgiving Day", HolidayType.FESTIVAL),
                new Holiday(" Dec 25 ","Christmas", HolidayType.FESTIVAL),
                new Holiday(" Jan 17 ","Martin Luther King Jr. Day", HolidayType.FEDERAL),
                new Holiday(" July 4 ","Independence Day", HolidayType.FEDERAL),
                new Holiday(" Sep 5 ","Labor Day", HolidayType.FEDERAL),
                new Holiday(" Nov 11 ","Veterans Day", HolidayType.FEDERAL)
        );

        HolidayType[] holidayTypes = HolidayType.values();

        for (HolidayType holidayType : holidayTypes)
        {
            model.addAttribute(
                    holidayType.toString(),
                    (
                            holidays.stream().filter(holiday -> holiday.getHolidayType().equals(holidayType))
                                    .collect(Collectors.toList())
                    )
            );
        }
        return "holidays";
    }




}
