package com.dpt.pojo;

import com.dpt.pojo.Invoice;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-20T19:49:29", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Shopping.class)
public class Shopping_ { 

    public static volatile SetAttribute<Shopping, Invoice> invoiceSet;
    public static volatile SingularAttribute<Shopping, String> name;
    public static volatile SingularAttribute<Shopping, Integer> id;

}