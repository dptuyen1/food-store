/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.service.impl;

import com.dpt.pojo.Review;
import com.dpt.pojo.User;
import com.dpt.repository.ReviewRepository;
import com.dpt.repository.UserRepository;
import com.dpt.service.ReviewService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author dptuy
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Review> getReviews(Map<String, String> params) {
        return this.reviewRepository.getReviews(params);
    }

    @Override
    public Review getById(int id) {
        return this.reviewRepository.getById(id);
    }

    @Override
    public Review add(Review review) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.getByUsername(authentication.getName());

        review.setCreatedDate(new Date());
        review.setUserId(user);

        return this.reviewRepository.addOrUpdate(review);
    }

    @Override
    public Review update(int id, Review review) {
        Review r = this.reviewRepository.getById(id);

        r.setContent(review.getContent());
        r.setUpdatedDate(new Date());

        return this.reviewRepository.addOrUpdate(review);
    }

    @Override
    public int delete(int id) {
        Review review = this.reviewRepository.getById(id);

        return this.reviewRepository.delete(review);
    }
}
