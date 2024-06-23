package pe.edu.cibertec.DSWII_CL2API_AbelPerez.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {

    void validarYGuardarArchivo(MultipartFile archivo) throws Exception;

    void validarYGuardarArchivos(List<MultipartFile> archivosList) throws Exception;

}
