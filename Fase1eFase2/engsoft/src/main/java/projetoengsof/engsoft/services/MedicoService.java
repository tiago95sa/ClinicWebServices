package projetoengsof.engsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoengsof.engsoft.filters.FilterObject;
import projetoengsof.engsoft.filters.medicoFiltros.MedicoFiltroService;
import projetoengsof.engsoft.models.Medico;
import projetoengsof.engsoft.repositories.MedicoRepoI;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class MedicoService implements MedicoServiceI{


    private MedicoRepoI medicoRepo;
    private MedicoFiltroService medicoFiltroService;

    public MedicoService(MedicoRepoI medicoRepo, MedicoFiltroService medicoFiltroService) {
        this.medicoRepo = medicoRepo;
        this.medicoFiltroService = medicoFiltroService;
    }


    @Override
    public Optional<Medico> getMedicoById(Long id){
        Optional<Medico> med = medicoRepo.findById(id);
        return med;
    }


    @Override
    public Set<Medico> getAllMedicos(){
        Set<Medico> medicos=new HashSet<>();
        for(Medico medico:this.medicoRepo.findAll()){
            medicos.add(medico);
        }
        return Collections.unmodifiableSet(medicos);
    }

    @Override
    public boolean existeMedicoByNumero(String numero){
        for(Medico medico: this.medicoRepo.findAll()){
            if(medico.getNumeroMedico().equalsIgnoreCase(numero)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Medico getMedicoByName(String name){
        for(Medico medico:getAllMedicos()){
            if (medico.getNome().equalsIgnoreCase(name)){
                return medico;
            }
        }
        return null;
    }

    @Override
    public Set<Medico> getFilteredMedicos(FilterObject filterObject){
        return medicoFiltroService.filterMedico(getAllMedicos(),filterObject);
    }

    @Override
    public void saveMedico(Medico medico){
        medicoRepo.save(medico);
    }

    @Override
    public void apagarById(Long id){
        medicoRepo.deleteById(id);
    }

}
