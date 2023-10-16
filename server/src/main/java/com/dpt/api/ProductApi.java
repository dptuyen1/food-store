/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.api;

import com.dpt.dto.ProductDTO;
import com.dpt.pojo.Product;
import com.dpt.service.ProductService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dptuy
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductApi {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> list(@RequestParam Map<String, String> params) {
        List<Product> products = this.productService.getProducts(params);
        List<ProductDTO> productsDTO = Arrays.asList(modelMapper.map(products, ProductDTO[].class));

        return new ResponseEntity<>(productsDTO, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> details(@PathVariable(value = "id") int id) {
        Product product = this.productService.getById(id);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int delete(@PathVariable(value = "id") int id) {
        return this.productService.delete(id);
    }
}
