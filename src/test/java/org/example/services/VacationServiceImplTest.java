package org.example.services;

import org.example.repositories.HolidaysDateRepository;
import org.example.services.Impl.VacationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class VacationServiceImplTest {

    @Test
    public void testVacationCalculateWithoutHolidays() {
        VacationServiceImpl vacationService = new VacationServiceImpl(null);
        int vacationDays = 10;
        double averageSalary = 30000.0;

        double result = vacationService.vacationCalculate(vacationDays, averageSalary);
        double expected = vacationDays * (averageSalary / 29.3);

        assertEquals(expected, result, "Vacation calculation with no holidays should be correct.");
    }

    @Mock
    private HolidaysDateRepository holidaysDateRepository;

    @InjectMocks
    private VacationServiceImpl vacationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testVacationCalculateWithHolidays() {
        int vacationDays = 10;
        double averageSalary = 30000.0;
        String startDateVacation = "2024.01.01";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate startDate = LocalDate.parse(startDateVacation, formatter);
        LocalDate endDate = startDate.plusDays(vacationDays - 1);

        // Подготовка данных для мока
        when(holidaysDateRepository.countHolidaysBetween(startDate, endDate)).thenReturn(Optional.of(2));

        // Выполнение метода
        double result = vacationService.vacationCalculate(vacationDays, averageSalary, startDateVacation);
        double expected = (vacationDays - 2) * (averageSalary / 29.3);

        // Проверка результата
        assertEquals(expected, result, "Vacation calculation with holidays should be correct.");
    }
}
