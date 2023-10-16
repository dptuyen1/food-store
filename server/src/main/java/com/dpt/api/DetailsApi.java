/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.api;

import com.dpt.dto.DetailsDTO;
import com.dpt.dto.ReceiptDTO;
import com.dpt.pojo.Details;
import com.dpt.service.DetailsService;
import com.dpt.service.InvoiceService;
import com.dpt.service.ProductService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dptuy
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class DetailsApi {

    @Autowired
    private DetailsService detailsService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/details")
    public ResponseEntity<List<DetailsDTO>> list(@RequestParam Map<String, String> params) {
        List<Details> details = this.detailsService.getDetails(params);
        List<DetailsDTO> detailsDTO = Arrays.asList(modelMapper.map(details, DetailsDTO[].class));

        return new ResponseEntity<>(detailsDTO, HttpStatus.OK);
    }

//    @PostMapping(path = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> add(@RequestBody Map<String, CartDTO> carts, @RequestBody int id) {
//        this.detailsService.add(carts, id);
//
//        return new ResponseEntity<>("Created!", HttpStatus.CREATED);
//    }
    @PostMapping(path = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody ReceiptDTO test) {
        this.detailsService.add(test.getCarts(), test.getInvoiceId());

        return new ResponseEntity<>("Created!", HttpStatus.CREATED);
    }
}
