package com.dpt.pojo;

import com.dpt.pojo.Invoice;
import com.dpt.pojo.Product;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-16T12:38:21")
@StaticMetamodel(Details.class)
public class Details_ { 

    public static volatile SingularAttribute<Details, BigDecimal> unitPrice;
    public static volatile SingularAttribute<Details, Integer> quantity;
    public static volatile SingularAttribute<Details, Product> productId;
    public static volatile SingularAttribute<Details, Invoice> invoiceId;
    public static volatile SingularAttribute<Details, Integer> id;

}