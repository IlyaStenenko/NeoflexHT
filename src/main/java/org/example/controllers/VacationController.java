package org.example.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.services.VacationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class VacationController {
    private final VacationService vacationService;

    @GetMapping("/calculate")
    public double getVacation(@RequestParam("averageSalary") Double averageSalary,
                              @RequestParam("vacationDays") Integer vacationDays,
                              @RequestParam(value = "startDateVacation", required = false) String startDateVacation) {
        if (startDateVacation != null) {
            return vacationService.vacationCalculate(vacationDays, averageSalary, startDateVacation);
        } else {
            return vacationService.vacationCalculate(vacationDays, averageSalary);
        }
    }
}
