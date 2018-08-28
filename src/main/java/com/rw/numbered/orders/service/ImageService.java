package com.rw.numbered.orders.service;

import org.springframework.stereotype.Service;

@Service
public class ImageService {
    public byte[] get1DCode(long orderId, long ticketId, int dpi, int columns) {
        return new byte[200];
    }

    public byte[] get2DCode(long orderId, long ticketId, int dpi, int height) {
        return new byte[200];
    }

}
