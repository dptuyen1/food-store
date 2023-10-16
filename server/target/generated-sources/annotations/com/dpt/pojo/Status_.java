package com.dpt.pojo;

import com.dpt.pojo.Invoice;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-15T20:59:33")
@StaticMetamodel(Status.class)
public class Status_ { 

    public static volatile SetAttribute<Status, Invoice> invoiceSet;
    public static volatile SingularAttribute<Status, String> name;
    public static volatile SingularAttribute<Status, Integer> id;

}