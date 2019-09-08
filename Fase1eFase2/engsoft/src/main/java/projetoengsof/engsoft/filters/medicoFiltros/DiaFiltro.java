package projetoengsof.engsoft.filters.medicoFiltros;

import projetoengsof.engsoft.models.Consulta;
import projetoengsof.engsoft.models.Medico;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DiaFiltro implements MedicoFiltro {

    private DayOfWeek dia ;

    public DiaFiltro(DayOfWeek dia){
        this.dia = dia;
    }


    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if(dia==null)return medicos;
        Set<Medico> diaFiltro=new HashSet<>();
        for(Medico medico: medicos){
            for(Consulta consulta:medico.getConsultas()) {
                if (consulta.getDataHora().getDayOfWeek().equals(dia)) {
                    diaFiltro.add(medico);
                    break;
                }
            }
        }
        return diaFiltro;
    }

}
