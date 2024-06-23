package pe.edu.cibertec.DSWII_CL2API_AbelPerez.model.bd;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idautor;
    private String nomautor;
    private String apeautor;
    private Date fechnacautor;


}
