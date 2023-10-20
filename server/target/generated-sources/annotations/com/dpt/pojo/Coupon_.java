package com.dpt.pojo;

import com.dpt.pojo.Promotion;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-20T19:49:29", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Coupon.class)
public class Coupon_ { 

    public static volatile SingularAttribute<Coupon, String> code;
    public static volatile SetAttribute<Coupon, Promotion> promotionSet;
    public static volatile SingularAttribute<Coupon, Integer> id;
    public static volatile SingularAttribute<Coupon, BigDecimal> value;

}