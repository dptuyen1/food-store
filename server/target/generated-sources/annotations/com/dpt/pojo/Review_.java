package com.dpt.pojo;

import com.dpt.pojo.Product;
import com.dpt.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-16T12:38:21")
@StaticMetamodel(Review.class)
public class Review_ { 

    public static volatile SingularAttribute<Review, Date> createdDate;
    public static volatile SingularAttribute<Review, Product> productId;
    public static volatile SingularAttribute<Review, Integer> id;
    public static volatile SingularAttribute<Review, Date> updatedDate;
    public static volatile SingularAttribute<Review, User> userId;
    public static volatile SingularAttribute<Review, String> content;

}