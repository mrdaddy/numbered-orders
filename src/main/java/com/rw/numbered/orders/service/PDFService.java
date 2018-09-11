package com.rw.numbered.orders.service;

import com.rw.numbered.orders.dto.order.Order;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Validated
public class PDFService {
    public ResponseEntity<InputStreamResource> getOrderPDF(@Valid @Min(1) long orderId) {
        Path path = Paths.get("1.pdf");
        String filename = "order_"+orderId+".pdf";
        return getInputStreamEntity(path, filename);
    }

    public ResponseEntity<InputStreamResource> getTicketPDF(@Valid @Min(1) long orderId, long ticketId) {
        Path path = Paths.get("1.pdf");
        String filename = "ticket_"+ticketId+".pdf";
        return getInputStreamEntity(path, filename);
    }

    private ResponseEntity<InputStreamResource> getInputStreamEntity(Path path, String filename) {
        try {
            InputStream inputStream = Files.newInputStream(path);
            HttpHeaders responseHeaders = new HttpHeaders();
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            responseHeaders.setContentDispositionFormData("attachment", filename);
            responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
            return new ResponseEntity<>(inputStreamResource,
                    responseHeaders,
                    HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
