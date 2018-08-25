package com.rw.numbered.orders.dto.order;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Информация о вагоне из заказа")
public class OrderCarriage {
    private String no;
    private String typeCode;
    private String typeName;
    private String typeDesc;
    private String serviceClassCode;
    private String serviceClassDesc;
    private String serviceClassIntCode;
    private String owner;
    private String carrier;
    private String genderSign;
    private String genderSignAdd;
    private String addSign;
}
