package projetoengsof.engsoft.filters.medicoFiltros;

import projetoengsof.engsoft.models.Consulta;
import projetoengsof.engsoft.models.Horario;
import projetoengsof.engsoft.models.Medico;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class HoraFimFiltro implements MedicoFiltro {

    private LocalTime horaFim;


    public HoraFimFiltro(LocalTime horaFim){
        this.horaFim = horaFim;
    }

    /*
    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if(horaFim==null)return medicos;

        Set<Medico> horaFimFiltro =new HashSet<>();
        for(Medico medico:medicos){
            for(Consulta consulta:medico.getConsultas()){
                if(consulta.getDataHora().getHour() <= horaFim.getHour()) {
                    horaFimFiltro.add(medico);
                    break;
                }
            }
        }
        return horaFimFiltro;
    }*/


    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if(horaFim==null)return medicos;

        Set<Medico> horaFimFiltro =new HashSet<>();
        for(Medico medico:medicos){
            for(Horario horario:medico.getHorario()){
                if(horario.getHoraFim().getHour() >= horaFim.getHour()) {
                    horaFimFiltro.add(medico);
                    break;
                }
            }
        }
        return horaFimFiltro;
    }





}