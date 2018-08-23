package com.rw.numbered.orders.dto.passenger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@ApiModel(description = "Данные о пассажире")
public class Passenger {
    @ApiModelProperty(example = "1", required = false, value = "Уникальный идентификатор пассажира из СППД. Если пассажир вводится первый раз - заполнять не нужно", dataType = "long")
    private long id;

    @ApiModelProperty(example = "Иван", required = true, value = "Имя пассажира (при покупке по глобальной цене допускается только латиница)", dataType = "String")
    @Size(min = 1, max = 32)
    private String firstName;
    @ApiModelProperty(example = "Иванович", required = true, value = "Отчество пассажира (при покупке по глобальной цене не заполняется)", dataType = "String")
    @Size(max = 32)
    private String patronymic;
    @ApiModelProperty(example = "Иванов", required = true, value = "Фамилия пассажира (при покупке по глобальной цене допускается только латиница)", dataType = "String")
    @Size(min = 1, max = 32)
    private String lastName;

    @ApiModelProperty(example = "1", required = true, value = "Количество мест для данного пассажира", dataType = "String")
    @Max(4)
    private int placesCount;

    private String sex;

    private Date birthday;
    private String country = "BLR";
    private String documentType ;
    private String documentNumber;
}
