package com.dpt.pojo;

import com.dpt.pojo.Category;
import com.dpt.pojo.Details;
import com.dpt.pojo.Review;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-16T12:38:21")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, String> image;
    public static volatile SingularAttribute<Product, BigDecimal> price;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SetAttribute<Product, Review> reviewSet;
    public static volatile SingularAttribute<Product, Boolean> active;
    public static volatile SetAttribute<Product, Details> detailsSet;
    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SingularAttribute<Product, Category> categoryId;

}