/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.api;

import com.dpt.dto.ReviewDTO;
import com.dpt.pojo.Review;
import com.dpt.service.ReviewService;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class ReviewApi {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDTO>> list(@RequestParam Map<String, String> params) {
        List<Review> reviews = this.reviewService.getReviews(params);
        List<ReviewDTO> reviewsDTO = Arrays.asList(modelMapper.map(reviews, ReviewDTO[].class));

        return new ResponseEntity<>(reviewsDTO, HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<ReviewDTO> add(@RequestBody Review review) {
        Review r = this.reviewService.add(review);
        ReviewDTO reviewDTO = modelMapper.map(r, ReviewDTO.class);

        return new ResponseEntity<>(reviewDTO, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/reviews/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") int id, @RequestBody Review review) {
        Review r = this.reviewService.update(id, review);
        ReviewDTO reviewDTO = modelMapper.map(r, ReviewDTO.class);

        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int delete(@PathVariable(value = "id") int id) {
        return this.reviewService.delete(id);
    }
}
