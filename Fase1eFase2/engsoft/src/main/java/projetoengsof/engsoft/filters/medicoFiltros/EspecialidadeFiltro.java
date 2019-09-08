package projetoengsof.engsoft.filters.medicoFiltros;

import projetoengsof.engsoft.models.Medico;

import java.util.Set;
import java.util.stream.Collectors;

public class EspecialidadeFiltro implements MedicoFiltro{

    private String especialidade;

    public EspecialidadeFiltro(String especialidade){
        this.especialidade = especialidade;
    }

    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if(especialidade==null)return medicos;
        return medicos.stream()
                .filter(medico -> medico.getEspecialidade().equalsIgnoreCase(especialidade))
                .collect(Collectors.toSet());
    }


}
