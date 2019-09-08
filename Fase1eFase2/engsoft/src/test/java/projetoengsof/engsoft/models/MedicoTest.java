package projetoengsof.engsoft.models;

import org.apache.tomcat.jni.Local;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.Assert.*;


public class MedicoTest {

    @Test
    public void testAdicionarConsulta(){
        Medico medico=new Medico();
        Utilizador utilizador = new Utilizador();
        LocalTime l1 = LocalTime.of(10,0);
        LocalTime l2 = LocalTime.of(17,0);
        Horario horario = new Horario(l1,l2,DayOfWeek.MONDAY);
        horario.setHoraFim(l2);
        horario.setHoraInicio(l1);
        System.out.println(horario);
        Horario horario2 = new Horario(LocalTime.of(14,0), LocalTime.of(23,0),DayOfWeek.TUESDAY);
        medico.addHorario(horario);
        medico.addHorario(horario2);


        Consulta consulta = new Consulta(LocalDateTime.of(2019, Month.FEBRUARY, 25, 11, 0) , "200", 50, utilizador, medico ,"Tiago");
        medico.addConsulta(consulta);

        assertEquals(1,medico.getConsultas().size());
    }

    @Test
    public void testAdicionarHorario(){
        Medico medico=new Medico();
        medico.addHorario(new Horario( LocalTime.of(10,0),LocalTime.of(12,0),DayOfWeek.MONDAY));
        assertEquals(1,medico.getHorario().size());
    }
}