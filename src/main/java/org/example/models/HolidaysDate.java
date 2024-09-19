package org.example.models;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "holidays_date", schema = "public")
@Accessors(chain = true)
@Getter
@Setter
public class HolidaysDate {
    @Id
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "holiday_name")
    private String holidayName;
}
