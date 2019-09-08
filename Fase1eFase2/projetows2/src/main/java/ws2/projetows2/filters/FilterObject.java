package projetoengsof.engsoft.filters;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class FilterObject {
    private String especialidade;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private DayOfWeek dia;
}
