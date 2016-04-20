package data;

import data.OrderItem;
import data.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-18T22:23:37")
@StaticMetamodel(OrderClass.class)
public class OrderClass_ { 

    public static volatile CollectionAttribute<OrderClass, OrderItem> orderItemCollection;
    public static volatile SingularAttribute<OrderClass, Integer> orderId;
    public static volatile SingularAttribute<OrderClass, User> userId;

}