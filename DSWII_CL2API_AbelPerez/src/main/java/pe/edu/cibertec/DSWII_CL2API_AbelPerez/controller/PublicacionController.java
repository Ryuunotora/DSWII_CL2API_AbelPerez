package pe.edu.cibertec.DSWII_CL2API_AbelPerez.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII_CL2API_AbelPerez.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII_CL2API_AbelPerez.model.bd.Publicacion;
import pe.edu.cibertec.DSWII_CL2API_AbelPerez.service.PublicacionService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v2/publicacion")
public class PublicacionController {

    private PublicacionService publicacionService;


    @GetMapping("")
    public ResponseEntity<List<Publicacion>> listarPublicaciones(){
        List<Publicacion> publicacionList = new ArrayList<>(publicacionService.listarPublicaciones());
        if(publicacionList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(publicacionList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> obtenerPublicacionXId(
            @PathVariable Integer id){
        Publicacion publicacion = publicacionService
                .obtenerPublicacionxId(id).orElseThrow(
                        () -> new ResourceNotFoundException("La Publicacion con Id " +
                                id + " no existe"));
        return new ResponseEntity<>(publicacion, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Publicacion> registrarPublicacion(
            @RequestBody Publicacion publicacion
    ){
        return new ResponseEntity<>(
                publicacionService.guardarPublicacion(publicacion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publicacion> actualizarPublicacion(
            @PathVariable Integer id,
            @RequestBody Publicacion publicacion
    ){
        Publicacion newPublicacion = publicacionService.obtenerPublicacionxId(id)
                .orElseThrow(() -> new ResourceNotFoundException("La Publicacion con Id "
                        + id +" no existe"));
        newPublicacion.setTitulo(publicacion.getTitulo());
        newPublicacion.setResumen(publicacion.getResumen());
        newPublicacion.setFechpublicacion(publicacion.getFechpublicacion());
        newPublicacion.setIdautor(publicacion.getIdautor());
        return new ResponseEntity<>(
                publicacionService.guardarPublicacion(newPublicacion),
                HttpStatus.OK);
    }


}
