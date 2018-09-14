package com.rw.numbered.orders.controllers;

import com.rw.numbered.orders.service.ImageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value="orders/image", produces = MediaType.IMAGE_PNG_VALUE, description="Сервис получения штриховых кодов ЭПД (штриховой код проездного документа и 2D штриховой код документа)", tags = "Получение штриховых кодов ЭПД", basePath="/orders/image")
@RequestMapping(path = "/${service.version}/numbered/orders")
@PreAuthorize("hasRole('U')")

public class ImageController extends BaseController {
    @Autowired
    ImageService imageService;

    @RequestMapping(method = RequestMethod.GET, path = "/1d/{orderId}/tickets/{ticketId}.png")
    @ApiOperation(value = "Получение штрихкода ЭПД", authorizations = @Authorization("jwt-auth"))
    public @ResponseBody ResponseEntity<InputStreamResource> get1DCode(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId, @PathVariable(value = "ticketId", required = false) @ApiParam(example = "1", value = "Уникальный идентификатор записи ЭПД", required = true) long ticketId, @RequestParam (value = "dpi", required = false, defaultValue = "150") @ApiParam(example = "200", value = "Количество точек на дюйм") int dpi, @RequestParam (value = "height)", required = false, defaultValue = "11") @ApiParam(example = "12", value = "Высота баркода") int height) {
        return imageService.get1DCode(orderId, ticketId, dpi, height);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/2d/{orderId}/tickets/{ticketId}.png")
    @ApiOperation(value = "Получение 2D кода ЭПД", authorizations = @Authorization("jwt-auth"))
    public @ResponseBody
    ResponseEntity<InputStreamResource> get2DCode(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId, @PathVariable(value = "ticketId", required = false) @ApiParam(example = "1", value = "Уникальный идентификатор записи ЭПД", required = true) long ticketId, @RequestParam (value = "dpi", required = false, defaultValue = "150") @ApiParam(example = "200", value = "Количество точек на дюйм") int dpi, @RequestParam (value = "columns", required = false, defaultValue = "6") @ApiParam(example = "7", value = "Высота баркода") int columns) {
        return imageService.get2DCode(orderId, ticketId, dpi, columns);
    }

}
