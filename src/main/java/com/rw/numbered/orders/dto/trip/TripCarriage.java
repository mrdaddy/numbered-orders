package com.rw.numbered.orders.dto.trip;

import lombok.Data;

@Data
public class TripCarriage {
    private String no;
    private String typeCode;
    private String typeName;
    private String typeDesc;
    private String serviceClassCode;
    private String serviceClassDesc;
    private String serviceClassIntCode;
}
