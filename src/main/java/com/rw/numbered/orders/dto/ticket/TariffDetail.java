package com.rw.numbered.orders.dto.ticket;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "Дополнительная информация о тарифе, заполняется только в случае, если указан параметр isFullData = true")
@NoArgsConstructor
@AllArgsConstructor
public class TariffDetail {

    @ApiModelProperty(required = false, example = "23.45", value = "Тариф (билет, плацкарта) по проездному документу", dataType = "double")
    private double tariff;

    @ApiModelProperty(required = false, example = "23.45", value = "Стоимость билетной части", dataType = "double")
    private double ticketTariff;

    @ApiModelProperty(required = false, example = "23.45", value = "Стоимость плацкарты", dataType = "double")
    private double reservedSeatTariff;

    @ApiModelProperty(required = false, example = "23.45", value = "Стоимость страхового сбора по проездному документу", dataType = "double")
    private double insuranceTariff;

    @ApiModelProperty(required = false, example = "23.45", value = "Стоимость за предоставляемый сервис по проездному документу", dataType = "double")
    private double serviceTariff;

    @ApiModelProperty(required = false, example = "23.45", value = "НДС по стоимости перевозки по проездному документу", dataType = "double")
    private double tariffVat;

    @ApiModelProperty(required = false, example = "23.45", value = "НДС по стоимости сервиса по проездному документу", dataType = "double")
    private double serviceTariffVat;

    @ApiModelProperty(required = false, example = "23.45", value = "Комиссионный сбор", dataType = "double")
    private double commissionFee;

    @ApiModelProperty(required = false, example = "23.45", value = "НДС по комиссионному сбору", dataType = "double")
    private double commissionVat;
}
