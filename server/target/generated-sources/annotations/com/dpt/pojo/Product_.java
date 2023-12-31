package com.dpt.pojo;

import com.dpt.pojo.Category;
import com.dpt.pojo.Details;
import com.dpt.pojo.Review;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-24T08:55:44", comments="EclipseLink-2.7.10.v20211216-rNA")
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