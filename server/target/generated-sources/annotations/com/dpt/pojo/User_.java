package com.dpt.pojo;

import com.dpt.pojo.Invoice;
import com.dpt.pojo.Promotion;
import com.dpt.pojo.Review;
import com.dpt.pojo.Role;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-24T08:55:44", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SetAttribute<User, Invoice> invoiceSet;
    public static volatile SingularAttribute<User, String> address;
    public static volatile SetAttribute<User, Promotion> promotionSet;
    public static volatile SingularAttribute<User, Role> roleId;
    public static volatile SetAttribute<User, Review> reviewSet;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phoneNumber;
    public static volatile SingularAttribute<User, Date> createdDate;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}