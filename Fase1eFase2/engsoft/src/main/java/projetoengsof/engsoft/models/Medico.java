package projetoengsof.engsoft.models;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
public class Medico extends Pessoa implements Serializable {


    public String numeroMedico;

    public String especialidade;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "medico")
    public Set<Consulta> consultas = new HashSet<>();

    @OneToMany
    public Set<Horario> horario = new HashSet<>();


    public boolean verificarSeExisteHorario(LocalDateTime dataPertendida) {

        for (Horario horario : this.horario) {
            if (horario.getDiaSemana().equals(dataPertendida.getDayOfWeek())) {
                if (dataPertendida.toLocalTime().isAfter(horario.getHoraInicio()) && dataPertendida.toLocalTime().isBefore(horario.getHoraFim().minusHours(1))) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean verificaSeMedicoTemConsultas(LocalDateTime dataPertendida) {

        if (verificarSeExisteHorario(dataPertendida)) {
            Iterator<Consulta> it = this.getConsultas().iterator();
            while (it.hasNext()) {
                Consulta cons = it.next();
                if (cons.getDataHora().isEqual(dataPertendida) || (cons.getDataHora().isBefore(dataPertendida) && cons.getDataHora().plusHours(1).isAfter(dataPertendida)) ) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }


    public void addHorario(Horario horario) {
        this.horario.add(horario);
//    horario.setMedico(this);
    }

    public void addConsulta(Consulta consulta) {
        if (verificarSeExisteHorario(consulta.getDataHora()) && verificaSeMedicoTemConsultas(consulta.getDataHora())) {
            this.consultas.add(consulta);
        }
    }

}