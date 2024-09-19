package org.example.repositories;

import org.example.models.HolidaysDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface HolidaysDateRepository extends JpaRepository<HolidaysDate, Long> {
    @Query(value = "SELECT COUNT(h) FROM holidays_date h WHERE h.date BETWEEN :startDate AND :endDate", nativeQuery = true)
    Optional<Integer> countHolidaysBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
