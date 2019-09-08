package projetoengsof.engsoft.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projetoengsof.engsoft.filters.FilterObject;
import projetoengsof.engsoft.models.Medico;
import projetoengsof.engsoft.repositories.MedicoRepoI;
import projetoengsof.engsoft.services.MedicoServiceI;

import java.awt.image.MemoryImageSource;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;



@RestController
@RequestMapping("/medico")
public class MedicoController {


    private MedicoServiceI medicoService;

    private Logger logger= LoggerFactory.getLogger(MedicoController.class);

    public MedicoController(MedicoServiceI medicoService){
        this.medicoService = medicoService;
    }

    /*
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Medico> AllMedico(){
        return medicoService.getAllMedicos();
    }*/


    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Medico> getAllMedicos(@ModelAttribute FilterObject filterObject){
        logger.info(filterObject.toString());
        return medicoService.getFilteredMedicos(filterObject);
    }

    /*
    @RequestMapping(value="/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Medico getById(@PathVariable("id") Long id){
        Optional<Medico> medic = medicoService.getById(id);
        return  medic.orElse(null);
    }*/


    @RequestMapping(value="/nome/{nome}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Medico getById(@PathVariable("nome") String nome){
        return medicoService.getMedicoByName(nome);
    }



}
