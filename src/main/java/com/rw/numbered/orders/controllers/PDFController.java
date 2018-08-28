package com.rw.numbered.orders.controllers;

import com.rw.numbered.orders.dto.ErrorMessage;
import com.rw.numbered.orders.service.PDFService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value="orders/pdf", description="Сервис получения бланков нумерованных заказов и квитанций разных сборов в формате PDF", tags = "Получение бланков заказов и ЭПД в формате PDF", basePath="/orders/pdf")
@RequestMapping(produces = MediaType.APPLICATION_PDF_VALUE, path = "/${service.version}/numbered/orders")

public class PDFController extends BaseController {
    @Autowired
    PDFService pdfService;

    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}.pdf")
    @ApiOperation(value = "Получение бланка заказа в формате PDF")
    public @ResponseBody byte[] returnOrder(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId) {
        return pdfService.getOrderPDF(orderId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}/tickets/{ticketId}.pdf")
    @ApiOperation(value = "Получение бланка ЭПД в формате PDF")
    public @ResponseBody byte[] returnTicket(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId, @PathVariable(value = "ticketId", required = false) @ApiParam(example = "1", value = "Уникальный идентификатор записи ЭПД", required = true) long ticketId) {
        return pdfService.getTicketPDF(orderId, ticketId);
    }
}
