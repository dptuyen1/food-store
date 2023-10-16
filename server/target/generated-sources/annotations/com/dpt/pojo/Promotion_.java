package com.dpt.pojo;

import com.dpt.pojo.Coupon;
import com.dpt.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-16T12:38:21")
@StaticMetamodel(Promotion.class)
public class Promotion_ { 

    public static volatile SingularAttribute<Promotion, Date> createdDate;
    public static volatile SingularAttribute<Promotion, Date> expiredDate;
    public static volatile SingularAttribute<Promotion, Integer> id;
    public static volatile SingularAttribute<Promotion, Coupon> couponId;
    public static volatile SingularAttribute<Promotion, User> userId;

}