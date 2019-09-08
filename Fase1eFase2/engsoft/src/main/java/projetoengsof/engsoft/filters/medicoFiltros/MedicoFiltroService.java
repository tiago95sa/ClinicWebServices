package projetoengsof.engsoft.filters.medicoFiltros;


import org.springframework.stereotype.Service;
import projetoengsof.engsoft.filters.AndFilter;
import projetoengsof.engsoft.filters.FilterI;
import projetoengsof.engsoft.filters.FilterObject;
import projetoengsof.engsoft.models.Medico;

import java.util.Set;

@Service
public class MedicoFiltroService {

    public Set<Medico> filterMedico(Set<Medico> medicos, FilterObject filterObject) {



        FilterI<Medico>  horaFimFiltro = new HoraFimFiltro(filterObject.getHoraFim());
        FilterI<Medico>  horaInicioFiltro = new HoraInicioeFiltro(filterObject.getHoraInicio());
        FilterI<Medico> horaFimAndHoraInicioFiltro = new AndFilter<>(horaFimFiltro, horaInicioFiltro);

        FilterI<Medico>  especialidadeFilter = new EspecialidadeFiltro(filterObject.getEspecialidade());
        FilterI<Medico>  especialidadeAndHoraInicioAndHoraFim = new AndFilter<>(horaFimAndHoraInicioFiltro , especialidadeFilter);

        FilterI<Medico>  diaFiltro = new DiaFiltro(filterObject.getDia());
        FilterI<Medico> especialidadeAndHoraInicioAndHoraFimAndDia = new AndFilter<>(especialidadeAndHoraInicioAndHoraFim , diaFiltro);

        return especialidadeAndHoraInicioAndHoraFimAndDia.filter(medicos);
    }
}
