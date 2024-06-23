package pe.edu.cibertec.DSWII_CL2API_AbelPerez.service;


import pe.edu.cibertec.DSWII_CL2API_AbelPerez.model.bd.Publicacion;

import java.util.List;
import java.util.Optional;

public interface IPublicacionService {

    List<Publicacion> listarPublicaciones();
    Publicacion guardarPublicacion(Publicacion publicacion);
    Optional<Publicacion> obtenerPublicacionxId(Integer id);
}
