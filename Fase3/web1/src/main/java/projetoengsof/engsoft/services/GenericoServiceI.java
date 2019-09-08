package projetoengsof.engsoft.services;

import projetoengsof.engsoft.filters.FilterObject;
import projetoengsof.engsoft.models.Generico;

import java.util.Set;

public interface GenericoServiceI {
    public void saveGenerico(Generico pessoa);
    public Set<Generico> getAllGenericos();
    Generico getGenericoById(Long id , String profissao);
    Set<Generico> getSetGenericosByProfissao(String profissao);
    Set<Generico> getFilteredGenericos(FilterObject filterObject , String profissao);
    Generico getGenById(Long id);
    Set<Generico> getALL();
}
