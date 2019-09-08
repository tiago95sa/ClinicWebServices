package projetoengsof.engsoft.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import projetoengsof.engsoft.models.*;
import projetoengsof.engsoft.repositories.HorarioRepoI;
import projetoengsof.engsoft.services.FuncionarioServiceI;

import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    FuncionarioServiceI funcionarioService;
    //MedicoServiceI medicoServiceI;

    HorarioRepoI horarioRepoI;

    public FuncionarioController(FuncionarioServiceI funcionarioService , HorarioRepoI horarioRepoI){
        this.funcionarioService = funcionarioService;
       // this.medicoServiceI = medicoServiceI;
        this.horarioRepoI = horarioRepoI;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Funcionario> AllFuncionarios(){
        return funcionarioService.getAllFuncionarios();
    }


    @RequestMapping(value="/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Funcionario getById(@PathVariable("id") Long id){

        return funcionarioService.getFuncionarioById(id);
    }



}
