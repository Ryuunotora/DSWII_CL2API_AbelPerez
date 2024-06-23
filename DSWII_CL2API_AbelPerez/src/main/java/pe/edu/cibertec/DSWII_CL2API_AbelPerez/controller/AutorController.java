package pe.edu.cibertec.DSWII_CL2API_AbelPerez.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII_CL2API_AbelPerez.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII_CL2API_AbelPerez.model.bd.Autor;
import pe.edu.cibertec.DSWII_CL2API_AbelPerez.service.AutorService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/autor")
public class AutorController {

    private AutorService autorService;

    @GetMapping("")
    public ResponseEntity<List<Autor>> listarAutores(){
        List<Autor> autorList = new ArrayList<>(autorService.listarAutores());
        if(autorList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(autorList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> obtenerAutorXId(
            @PathVariable Integer id){
        Autor autor = autorService
                .obtenerAutorxId(id).orElseThrow(
                        () -> new ResourceNotFoundException("El Autor con Id " +
                                id + " no existe"));
        return new ResponseEntity<>(autor, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Autor> registrarAutor(
            @RequestBody Autor autor
    ){
        return new ResponseEntity<>(
                autorService.guardarAutor(autor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> actualizarAutor(
            @PathVariable Integer id,
            @RequestBody Autor autor
    ){
        Autor newAutor = autorService.obtenerAutorxId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Autor con Id "
                        + id +" no existe"));
        newAutor.setNomautor(autor.getNomautor());
        newAutor.setApeautor(autor.getApeautor());
        newAutor.setFechnacautor(autor.getFechnacautor());
        return new ResponseEntity<>(
                autorService.guardarAutor(newAutor),
                HttpStatus.OK);
    }



}
