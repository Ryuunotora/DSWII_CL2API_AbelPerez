package pe.edu.cibertec.DSWII_CL2API_AbelPerez.service;

import pe.edu.cibertec.DSWII_CL2API_AbelPerez.model.bd.Autor;

import java.util.List;
import java.util.Optional;

public interface IAutorService {

    List<Autor> listarAutores();
    Autor guardarAutor(Autor autor);
    Optional<Autor> obtenerAutorxId(Integer id);

}
