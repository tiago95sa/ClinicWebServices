package projetoengsof.engsoft.services;


import org.springframework.stereotype.Service;
import projetoengsof.engsoft.models.Consulta;
import projetoengsof.engsoft.models.Utilizador;
import projetoengsof.engsoft.repositories.UtilizadorRepoI;


import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UtilizadorService implements UtilizadorServiceI {

    private UtilizadorRepoI utilizadorRepo;


    public UtilizadorService(UtilizadorRepoI utilizadorRepo) {
        this.utilizadorRepo = utilizadorRepo;
    }


    @Override
    public Set<Utilizador> getAllUtilizadores() {
        Set<Utilizador> utilizadores = new HashSet<>();
        for (Utilizador utilizador : this.utilizadorRepo.findAll()) {
            utilizadores.add(utilizador);
        }
        return Collections.unmodifiableSet(utilizadores);
    }

    @Override
    public Optional<Utilizador> getUtilizadorByNumeroUtente(String numeroUtente) {
        System.out.println("aquuiiiiiiiiiiiii");
        for (Utilizador utilizador : this.utilizadorRepo.findAll()) {
            System.out.println("entrouuuu");
            if (utilizador.getNumeroUtente().equalsIgnoreCase(numeroUtente)) {
                System.out.println("ENTROU IF");
                return Optional.of(utilizador);
            }
        }
        return Optional.empty();
    }


    @Override
    public Optional<Utilizador> getById(Long id) {

        return utilizadorRepo.findById(id);

    }


    public Set<Consulta> getConsultasUtilizador(Long id){

        Optional<Utilizador> utilizador = getById(id);

        return utilizador.get().getConsultas();

    }




}

