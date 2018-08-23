package com.rw.numbered.orders.dto.trip;

import lombok.Data;

@Data
public class TripSeatDetail {
    public enum PART_TYPES {N, S, C}
    private String numFrom;
    private String numTo;
    private int downCount;
    private int upCount;
    private String sexCoupeType;
    private PART_TYPES partType;
    private boolean includedBedding;
}
