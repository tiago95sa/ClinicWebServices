package projetoengsof.engsoft.filters.medicoFiltros;

import projetoengsof.engsoft.filters.FilterI;
import projetoengsof.engsoft.models.Medico;

import java.util.Set;

public interface MedicoFiltro extends FilterI<Medico> {
    Set<Medico> filter(Set<Medico> entities);
}
