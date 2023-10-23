package com.dpt.pojo;

import com.dpt.pojo.Product;
import com.dpt.pojo.User;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-24T00:50:46", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Review.class)
public class Review_ { 

    public static volatile SingularAttribute<Review, Date> createdDate;
    public static volatile SingularAttribute<Review, Product> productId;
    public static volatile SingularAttribute<Review, Integer> id;
    public static volatile SingularAttribute<Review, Date> updatedDate;
    public static volatile SingularAttribute<Review, User> userId;
    public static volatile SingularAttribute<Review, String> content;

}