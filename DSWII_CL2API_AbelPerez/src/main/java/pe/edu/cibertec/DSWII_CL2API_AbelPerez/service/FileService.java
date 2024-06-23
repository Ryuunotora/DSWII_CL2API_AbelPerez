package pe.edu.cibertec.DSWII_CL2API_AbelPerez.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileService implements IFileService {

    private final Path pathFolder = Paths.get("uploads");

    @Override
    public void validarYGuardarArchivo(MultipartFile archivo) throws Exception {
        validarExtensionArchivo(archivo);
        validarTamanoArchivo(archivo);
        guardarArchivo(archivo);
    }

    @Override
    public void validarYGuardarArchivos(List<MultipartFile> archivosList) throws Exception {
        for (MultipartFile archivo : archivosList) {
            validarExtensionArchivo(archivo);
        }
        for (MultipartFile archivo : archivosList) {
            guardarArchivo(archivo);
        }
    }

    private void guardarArchivo(MultipartFile archivo) throws Exception {
        if (!Files.exists(pathFolder)) {
            Files.createDirectories(pathFolder);
        }
        Files.copy(archivo.getInputStream(), this.pathFolder.resolve(archivo.getOriginalFilename()));
    }

    private void validarExtensionArchivo(MultipartFile archivo) throws Exception {
        String nombreArchivo = archivo.getOriginalFilename();
        if (nombreArchivo == null || (!nombreArchivo.endsWith(".pdf") && !nombreArchivo.endsWith(".png") && !nombreArchivo.endsWith(".docx"))) {
            throw new IllegalArgumentException("Solo se permiten archivos con extensiones .pdf, .png, .docx");
        }
    }

    private void validarTamanoArchivo(MultipartFile archivo) throws Exception {
        if (archivo.getSize() > 25 * 1024 * 1024) {
            throw new IllegalArgumentException("El archivo excede el tamaño máximo permitido de 25MB");
        }
    }
}
