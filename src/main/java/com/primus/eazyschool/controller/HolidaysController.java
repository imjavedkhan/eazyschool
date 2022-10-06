package com.primus.eazyschool.controller;

import com.primus.eazyschool.model.Holiday;
import com.primus.eazyschool.model.HolidayType;
import com.primus.eazyschool.repository.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HolidaysRepository holidaysRepository;

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


        List<Holiday> holidays = holidaysRepository.findAllHoliday();

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
