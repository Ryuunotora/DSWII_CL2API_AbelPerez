package pe.edu.cibertec.DSWII_CL2API_AbelPerez.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.DSWII_CL2API_AbelPerez.model.dto.ArchivoDto;
import pe.edu.cibertec.DSWII_CL2API_AbelPerez.service.FileService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v3/files")
public class FileController {
    private final FileService fileService;

    @PostMapping("/multiple")
    public ResponseEntity<ArchivoDto> subirArchivos(
            @RequestParam("files") List<MultipartFile> multipartFileList
    ) throws Exception {
        if (multipartFileList.size() != 3) {
            return new ResponseEntity<>(ArchivoDto.builder()
                    .mensaje("Debe subir exactamente 3 archivos").build(), HttpStatus.BAD_REQUEST);
        }
        fileService.validarYGuardarArchivos(multipartFileList);
        return new ResponseEntity<>(ArchivoDto.builder()
                .mensaje("Archivos subidos correctamente").build(), HttpStatus.OK);
    }

    @PostMapping("/single")
    public ResponseEntity<ArchivoDto> subirArchivo(
            @RequestParam("file") MultipartFile multipartFile
    ) throws Exception {
        fileService.validarYGuardarArchivo(multipartFile);
        return new ResponseEntity<>(ArchivoDto.builder()
                .mensaje("Archivo subido correctamente").build(), HttpStatus.OK);
    }
}
