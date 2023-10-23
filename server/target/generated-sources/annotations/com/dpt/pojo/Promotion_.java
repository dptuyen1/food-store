package com.dpt.pojo;

import com.dpt.pojo.Coupon;
import com.dpt.pojo.User;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-24T00:50:46", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Promotion.class)
public class Promotion_ { 

    public static volatile SingularAttribute<Promotion, Date> createdDate;
    public static volatile SingularAttribute<Promotion, Date> expiredDate;
    public static volatile SingularAttribute<Promotion, Integer> id;
    public static volatile SingularAttribute<Promotion, Coupon> couponId;
    public static volatile SingularAttribute<Promotion, User> userId;

}