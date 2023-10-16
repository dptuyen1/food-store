/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.api;

import com.dpt.dto.InvoiceDTO;
import com.dpt.pojo.Invoice;
import com.dpt.service.InvoiceService;
import java.util.Arrays;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dptuy
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class InvoiceApi {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/invoices")
    public ResponseEntity<List<InvoiceDTO>> list() {
        List<Invoice> invoices = this.invoiceService.getByUser();
        List<InvoiceDTO> invoicesDTO = Arrays.asList(modelMapper.map(invoices, InvoiceDTO[].class));

        return new ResponseEntity<>(invoicesDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InvoiceDTO> add(@RequestBody Invoice invoice) {
        Invoice i = this.invoiceService.add(invoice);
        InvoiceDTO invoiceDTO = modelMapper.map(i, InvoiceDTO.class);

        return new ResponseEntity<>(invoiceDTO, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/invoices/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InvoiceDTO> update(@PathVariable(value = "id") int id, @RequestBody Invoice invoice) {
        Invoice i = this.invoiceService.update(id, invoice);
        InvoiceDTO invoiceDTO = modelMapper.map(i, InvoiceDTO.class);

        return new ResponseEntity<>(invoiceDTO, HttpStatus.OK);
    }

    @DeleteMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int delete(@PathVariable(value = "id") int id) {
        return this.invoiceService.delete(id);
    }
}
