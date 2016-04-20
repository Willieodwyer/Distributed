package data;

import data.OrderClass;
import data.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-18T22:23:37")
@StaticMetamodel(OrderItem.class)
public class OrderItem_ { 

    public static volatile SingularAttribute<OrderItem, Integer> quantity;
    public static volatile SingularAttribute<OrderItem, Product> productId;
    public static volatile SingularAttribute<OrderItem, OrderClass> orderId;
    public static volatile SingularAttribute<OrderItem, Integer> orderitemId;

}