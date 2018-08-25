package com.rw.numbered.orders.dto.order;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "Информация об электронной регистрации из заказа")
public class OrderRegistration {
    private boolean isAllowed;
    private boolean isNeeded;
    private String status;
    private Date regTime;
    private Date stopTime;
    private boolean isWithASMBP;
}
