package projetoengsof.engsoft.services;

import projetoengsof.engsoft.models.Consulta;
import projetoengsof.engsoft.models.Utilizador;

import java.util.Optional;
import java.util.Set;

public interface UtilizadorServiceI {

    Set<Utilizador> getAllUtilizadores();
    Optional<Utilizador> getUtilizadorByNumeroUtente(String numeroUtente);
    Optional<Utilizador> getById(Long id);
    Set<Consulta> getConsultasUtilizador(Long id);
}
