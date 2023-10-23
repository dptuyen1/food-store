package com.dpt.pojo;

import com.dpt.pojo.Invoice;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-24T00:50:46", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SetAttribute<Payment, Invoice> invoiceSet;
    public static volatile SingularAttribute<Payment, String> name;
    public static volatile SingularAttribute<Payment, Integer> id;

}