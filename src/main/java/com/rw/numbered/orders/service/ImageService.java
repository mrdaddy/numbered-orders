package com.rw.numbered.orders.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class ImageService {
    public byte[] get1DCode(@Valid @Min(1) long orderId,@Valid  @Min(1) long ticketId, int dpi, int columns) {
        return new byte[200];
    }

    public byte[] get2DCode(@Valid @Min(1) long orderId,@Valid @Min(1)  long ticketId, int dpi, int height) {
        return new byte[200];
    }

}
