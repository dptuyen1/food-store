package com.dpt.pojo;

import com.dpt.pojo.Details;
import com.dpt.pojo.Payment;
import com.dpt.pojo.Shopping;
import com.dpt.pojo.Status;
import com.dpt.pojo.User;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-24T00:50:46", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Invoice.class)
public class Invoice_ { 

    public static volatile SingularAttribute<Invoice, Shopping> shoppingId;
    public static volatile SingularAttribute<Invoice, Integer> totalQuantity;
    public static volatile SingularAttribute<Invoice, Date> createdDate;
    public static volatile SingularAttribute<Invoice, Status> statusId;
    public static volatile SingularAttribute<Invoice, BigDecimal> totalPrice;
    public static volatile SingularAttribute<Invoice, Payment> paymentId;
    public static volatile SingularAttribute<Invoice, BigDecimal> discountPrice;
    public static volatile SetAttribute<Invoice, Details> detailsSet;
    public static volatile SingularAttribute<Invoice, Integer> id;
    public static volatile SingularAttribute<Invoice, User> userId;

}