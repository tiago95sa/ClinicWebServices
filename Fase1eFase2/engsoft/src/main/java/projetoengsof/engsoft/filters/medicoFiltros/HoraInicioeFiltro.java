package projetoengsof.engsoft.filters.medicoFiltros;

import projetoengsof.engsoft.models.Consulta;
import projetoengsof.engsoft.models.Horario;
import projetoengsof.engsoft.models.Medico;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class HoraInicioeFiltro implements MedicoFiltro {

    private LocalTime horaInicio;


    public HoraInicioeFiltro(LocalTime horaInicio){
        this.horaInicio = horaInicio;
    }

    /*
    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if(horaInicio==null)return medicos;
        Set<Medico> horaInicioFiltro =new HashSet<>();
        for(Medico medico:medicos){
            for(Consulta consulta:medico.getConsultas()){
                if(consulta.getDataHora().getHour() >= horaInicio.getHour()) {
                    horaInicioFiltro.add(medico);
                    break;
                }
            }
        }
        return horaInicioFiltro;
    }*/

    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if(horaInicio==null)return medicos;

        Set<Medico> horaInicioFiltro =new HashSet<>();
        for(Medico medico:medicos){
            for(Horario horario:medico.getHorario()){
                if(horario.getHoraInicio().getHour() <= horaInicio.getHour()) {
                    horaInicioFiltro.add(medico);
                    break;
                }
            }
        }
        return horaInicioFiltro;
    }


}
