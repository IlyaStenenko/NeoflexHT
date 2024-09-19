package org.example.services;

public interface VacationService {
    double vacationCalculate(int vacationDays, double averageSalary);
    double vacationCalculate(int vacationDays, double averageSalary, String startDateVacation);
}
