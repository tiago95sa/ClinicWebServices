package projetoengsof.engsoft.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projetoengsof.engsoft.models.Consulta;
import projetoengsof.engsoft.models.Medico;
import projetoengsof.engsoft.models.Utilizador;
import projetoengsof.engsoft.repositories.ConsultaRepoI;
import projetoengsof.engsoft.services.ConsultaServiceI;
import projetoengsof.engsoft.services.MedicoServiceI;
import projetoengsof.engsoft.services.UtilizadorServiceI;
import sun.reflect.misc.ConstructorUtil;


import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private ConsultaServiceI consultaService;
    private MedicoServiceI medicoService;
    private UtilizadorServiceI utilizadorService;


    private Logger logger= LoggerFactory.getLogger(ConsultaController.class);

   public ConsultaController(ConsultaServiceI consultaService , MedicoServiceI medicoService , UtilizadorServiceI utilizadorService){

       this.medicoService = medicoService;
       this.consultaService = consultaService;
       this.utilizadorService = utilizadorService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Consulta> AllConsultas(){
        return consultaService.getAllConsultas();
    }



    @PostMapping(value = "/marcar/{id_utente}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String marcarConsulta(@RequestBody Consulta consulta , @PathVariable("id_utente") Long id_utente){
        //ResponseEntity<Consulta>
        Medico medico = medicoService.getMedicoByName(consulta.getMedico_nome());

        //Optional<Utilizador> utilizador = utilizadorService.getById(consulta.getUtilizador().getId());
        Optional<Utilizador> utilizador = utilizadorService.getById(id_utente);


        if(consultaService.MarcarConsulta(consulta , medico)){

            medico.addConsulta(consulta);
            consulta.setMedico(medico);
            consulta.setUtilizador(utilizador.get());
            utilizador.get().addConsulta(consulta);
            consultaService.saveConsulta(consulta);
            //return ResponseEntity.ok(consulta);
            return "Adicionado com sucesso";

        }
        return "Nao adicionado";
        //return ResponseEntity.notFound().build();
   }


    @RequestMapping(value = "/apagar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Consulta> apagarConsulta(@PathVariable("id") Long id) {


        Optional<Consulta> cons = consultaService.getConsultaById(id);
        if (cons == null) {
            System.out.println("Consulta inexistente");
            return new ResponseEntity<Consulta>(HttpStatus.NOT_FOUND);
        }

        consultaService.apagarConsulta(id);
        return new ResponseEntity<Consulta>(HttpStatus.NO_CONTENT);
    }


    @PostMapping(value = "/atualizar/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> atualizarConsulta(@RequestBody Consulta consulta, @PathVariable("id") Long id){

        Optional<Consulta> cons = consultaService.getConsultaById(id);

        if(cons == null){
            System.out.println("Consulta inexistente");
            return new ResponseEntity<Consulta>(HttpStatus.NOT_FOUND);
        }

        Medico med = medicoService.getMedicoByName(consulta.getMedico_nome());

        cons.get().setCusto(consulta.getCusto());
        cons.get().setDataHora(consulta.getDataHora());
        cons.get().setGabinete(consulta.getGabinete());
        cons.get().setMedico_nome(consulta.getMedico_nome());
        cons.get().setMedico(med);


        System.out.printf("Consulta atualizada");
        return new ResponseEntity<Consulta>(cons.get(), HttpStatus.OK);
    }



}
