package ws2.projetows2.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ws2.projetows2.models.Consulta;
import ws2.projetows2.models.JsonClinica;
import ws2.projetows2.models.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.*;


@Controller
public class ConsultaController {

    private Logger logger= LoggerFactory.getLogger(ConsultaController.class);

    @GetMapping(value = "/medico", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Set<JsonClinica> getAllMedicos() throws JsonParseException, IOException {

        Set<JsonClinica> medicosClinica = new HashSet<>();


        for(String key : Utils.getMap().keySet()) {

            JsonClinica medicosClinicaTemp = new JsonClinica();

            String path = Utils.getMap().get(key);
            path = path .concat("/medico/");


            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> nullBodyRequest = new HttpEntity<>(null, headers);

            ResponseEntity<String> responseEntity = makeRequest(path, HttpMethod.GET, nullBodyRequest, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(responseEntity.getBody());

            medicosClinicaTemp.setTipo(actualObj);
            medicosClinicaTemp.setNomeClinica(key);
            medicosClinica.add(medicosClinicaTemp);

        }
        return medicosClinica;
    }


    @GetMapping(value = "/consulta", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Set<JsonClinica> getAllConsultas() throws JsonParseException, IOException {

        Set<JsonClinica> consultaClinica = new HashSet<>();


        for(String key : Utils.getMap().keySet()) {

            JsonClinica consultaClinicaTemp = new JsonClinica();

            String path = Utils.getMap().get(key);
            path = path .concat("/consulta/");


            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> nullBodyRequest = new HttpEntity<>(null, headers);

            ResponseEntity<String> responseEntity = makeRequest(path, HttpMethod.GET, nullBodyRequest, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(responseEntity.getBody());

            consultaClinicaTemp.setTipo(actualObj);
            consultaClinicaTemp.setNomeClinica(key);
            consultaClinica.add(consultaClinicaTemp);

        }
        return consultaClinica;
    }



    @GetMapping(value = "/consulta/{nomeClinica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllConsultas(@PathVariable("nomeClinica") String nomeClinica) {
        //setExample();
        String path = Utils.getMap().get(nomeClinica) .concat("/consulta/");
        logger.info(path);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> nullBodyRequest = new HttpEntity<>(null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest(path, HttpMethod.GET, nullBodyRequest, Iterable.class);
        return responseEntity.getBody();
    }

    @GetMapping(value = "/medico/{nomeClinica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllMedicosClinica(@PathVariable("nomeClinica") String nomeClinica) {
        //setExample();
        String path = Utils.getMap().get(nomeClinica) .concat("/medico/");
        logger.info(path);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> nullBodyRequest = new HttpEntity<>(null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest(path, HttpMethod.GET, nullBodyRequest, Iterable.class);
        return responseEntity.getBody();
    }

    @GetMapping(value = "/filtro/{nomeClinica}/{filtro}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllFiltrosMedicosClinica(@PathVariable("nomeClinica") String nomeClinica , @PathVariable("filtro") String filtro) {

        String path = Utils.getMap().get(nomeClinica) .concat("/medico").concat("?").concat(filtro);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> nullBodyRequest = new HttpEntity<>(null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest(path, HttpMethod.GET, nullBodyRequest, Iterable.class);
        return responseEntity.getBody();
    }


    @PostMapping(value = "/marcar/{nomeClinica}/{id_utente}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> marcarConsulta(@RequestBody Consulta consulta , @PathVariable("nomeClinica") String nomeClinica , @PathVariable("id_utente") String id_utente){

        String path = Utils.getMap().get(nomeClinica) .concat("/consulta/marcar/").concat(id_utente);


        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Consulta> bodyRequest = new HttpEntity<>(consulta, headers);

        ResponseEntity<String> responseEntity = makeRequest(path, HttpMethod.POST, bodyRequest, String.class);


        //return responseEntity.getBody();
        return responseEntity;
    }







    private ResponseEntity makeRequest(String path, HttpMethod method, HttpEntity request, Class responseType) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(path, method, request, responseType);
    }

}