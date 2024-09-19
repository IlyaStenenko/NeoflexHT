package org.example.services.Impl;

import org.example.repositories.HolidaysDateRepository;
import org.example.services.VacationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class VacationServiceImpl implements VacationService {

    private HolidaysDateRepository holidaysDateRepository;

    public VacationServiceImpl(HolidaysDateRepository holidaysDateRepository) {
        this.holidaysDateRepository = holidaysDateRepository;
    }

    @Override
    public double vacationCalculate(int vacationDays, double averageSalary) {
        return vacationDays * (averageSalary / 29.3);
    }

    @Override
    public double vacationCalculate(int vacationDays, double averageSalary, String startDateVacation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate startDate = LocalDate.parse(startDateVacation, formatter);
        LocalDate endDate = startDate.plusDays(vacationDays - 1);
        Integer countHolidayDays = holidaysDateRepository.countHolidaysBetween(startDate, endDate).orElse(0);
        return (vacationDays - countHolidayDays) * (averageSalary / 29.3);
    }
}
