package pe.edu.cibertec.DSWII_CL2API_AbelPerez.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
