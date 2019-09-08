package projetoengsof.engsoft.services;

import projetoengsof.engsoft.filters.FilterObject;
import projetoengsof.engsoft.models.Medico;

import java.util.Optional;
import java.util.Set;

public interface MedicoServiceI {

    Set<Medico> getAllMedicos();
    Medico getMedicoByName(String name);
    //Optional<Medico> getById(Long id);
    Set<Medico> getFilteredMedicos(FilterObject filterObject);
    boolean existeMedicoByNumero(String numero);
    void saveMedico(Medico medico);
    Optional<Medico> getMedicoById(Long id);
    void apagarById(Long id);
}
