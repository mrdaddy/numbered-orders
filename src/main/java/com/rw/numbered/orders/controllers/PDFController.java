package com.rw.numbered.orders.controllers;

import com.rw.numbered.orders.security.User;
import com.rw.numbered.orders.service.PDFService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(value="orders/pdf", produces = MediaType.APPLICATION_PDF_VALUE, description="Сервис получения бланков нумерованных заказов и квитанций разных сборов в формате PDF", tags = "Получение бланков заказов и ЭПД в формате PDF", basePath="/orders/pdf")
@RequestMapping(path = "/${service.version}/numbered/orders")
@PreAuthorize("hasRole('U')")

public class PDFController extends BaseController {
    @Autowired
    PDFService pdfService;

    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}.pdf")
    @ApiOperation(value = "Получение бланка заказа в формате PDF", authorizations = @Authorization("jwt-auth"))
    public ResponseEntity<byte[]> getOrderPDF(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId,
                                              @RequestAttribute(value = "user", required = false) @ApiIgnore User user) {
        return pdfService.getOrderPDF(orderId, user);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}/tickets/{ticketId}.pdf")
    @ApiOperation(value = "Получение бланка ЭПД в формате PDF", authorizations = @Authorization("jwt-auth"))
    public
    ResponseEntity<byte[]> getTicketPDF(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId,
                                        @PathVariable(value = "ticketId", required = false) @ApiParam(example = "1", value = "Уникальный идентификатор записи ЭПД", required = true) long ticketId,
                                        @RequestAttribute(value = "user", required = false) @ApiIgnore User user) {
        return pdfService.getTicketPDF(orderId, ticketId, user);
    }
}
