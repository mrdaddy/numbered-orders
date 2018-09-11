package com.rw.numbered.orders.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Validated
public class ImageService {
    public ResponseEntity<InputStreamResource> get1DCode(@Valid @Min(1) long orderId,@Valid  @Min(1) long ticketId, int dpi, int columns) {
        Path path = Paths.get("1.png");
        String filename = "1d_"+ticketId+".png";
        return getInputStreamEntity(path, filename);
    }

    public ResponseEntity<InputStreamResource> get2DCode(@Valid @Min(1) long orderId,@Valid @Min(1)  long ticketId, int dpi, int height) {
        Path path = Paths.get("1.png");
        String filename = "2d_"+ticketId+".png";
        return getInputStreamEntity(path, filename);
    }

    private ResponseEntity<InputStreamResource> getInputStreamEntity(Path path, String filename) {
        try {
            InputStream inputStream = Files.newInputStream(path);
            HttpHeaders responseHeaders = new HttpHeaders();
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            responseHeaders.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(inputStreamResource,
                    responseHeaders,
                    HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
