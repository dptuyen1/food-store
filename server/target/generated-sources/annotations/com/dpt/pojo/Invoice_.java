package com.dpt.pojo;

import com.dpt.pojo.Details;
import com.dpt.pojo.Shopping;
import com.dpt.pojo.Status;
import com.dpt.pojo.User;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-16T12:38:21")
@StaticMetamodel(Invoice.class)
public class Invoice_ { 

    public static volatile SingularAttribute<Invoice, Shopping> shoppingId;
    public static volatile SingularAttribute<Invoice, Integer> totalQuantity;
    public static volatile SingularAttribute<Invoice, Date> createdDate;
    public static volatile SingularAttribute<Invoice, Status> statusId;
    public static volatile SingularAttribute<Invoice, BigDecimal> totalPrice;
    public static volatile SingularAttribute<Invoice, BigDecimal> discountPrice;
    public static volatile SetAttribute<Invoice, Details> detailsSet;
    public static volatile SingularAttribute<Invoice, Integer> id;
    public static volatile SingularAttribute<Invoice, User> userId;

}