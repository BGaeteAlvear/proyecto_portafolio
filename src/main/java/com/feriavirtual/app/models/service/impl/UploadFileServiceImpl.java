package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.service.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
    private final Logger log= LoggerFactory.getLogger(getClass());
    private final static String UPLOADS_FOLDER = "uploads";


    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathImagen = getPath(filename);
        log.info("pathImagen: " + pathImagen);
        Resource recurso =null;


            recurso = new UrlResource(pathImagen.toUri());

            if (!recurso.exists() || !recurso.isReadable()){
                throw new RuntimeException("Error: No se puede cargar la imagen: "+ pathImagen.toString());
            }


        return recurso;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {

        String uniqueFilename = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
        Path rootPath = getPath(uniqueFilename);

        log.info("rootPath: "+ rootPath);

            Files.copy(file.getInputStream(), rootPath);

        return uniqueFilename;
    }

    @Override
    public boolean delete(String filename) {

        Path rootPath =getPath(filename);
        File archivo = rootPath.toFile();

        if (archivo.exists() && archivo.canRead()){
            if(archivo.delete()){
             }
        }
        return true;
    }


    public Path getPath(String filename){
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }
}
