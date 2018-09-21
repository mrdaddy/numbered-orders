package com.rw.numbered.orders.service;

import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.security.User;
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
    public ResponseEntity<byte[]> getOrderPDF(@Valid @Min(1) long orderId, @Valid User user) {
        Path path = Paths.get("1.pdf");
        String filename = "order_"+orderId+".pdf";
        return getInputStreamEntity(path, filename);
    }

    public ResponseEntity<byte[]> getTicketPDF(@Valid @Min(1) long orderId, long ticketId, @Valid User user) {
        Path path = Paths.get("1.pdf");
        String filename = "ticket_"+ticketId+".pdf";
        return getInputStreamEntity(path, filename);
    }

    private ResponseEntity<byte[]> getInputStreamEntity(Path path, String filename) {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentDispositionFormData("attachment", filename);
            responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
            byte[] fileContent = Files.readAllBytes(path);
            return new ResponseEntity<>(fileContent,
                    responseHeaders,
                    HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
