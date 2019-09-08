package ws2.projetows2.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;




@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
public class Consulta extends BaseModel  {

  public LocalDateTime dataHora;

  public String gabinete;

  public String medico_nome;

  public float custo;

  @ManyToOne
  @EqualsAndHashCode.Exclude
  @JsonIgnore
  @ToString.Exclude
  public Utilizador utilizador;

  @ManyToOne
  @EqualsAndHashCode.Exclude
  @JsonIgnore
  @ToString.Exclude
  public Medico medico;



  public Consulta(LocalDateTime dataHora, String gabinete, float custo, Utilizador utilizador, Medico medico , String medico_nome) {
    this.dataHora = dataHora;
    this.gabinete = gabinete;
    this.custo = custo;
    this.utilizador = utilizador;
    this.medico  = medico;
    this.medico_nome = medico_nome;
    //this.medico_id = medico_id;
    //this.utilizador_id = utilizador_id;
  }
}