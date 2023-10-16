/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpt.repository;

import com.dpt.pojo.Review;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dptuy
 */
public interface ReviewRepository {

    List<Review> getReviews(Map<String, String> params);

    Review getById(int id);

    Review addOrUpdate(Review review);

    int delete(Review review);
}
