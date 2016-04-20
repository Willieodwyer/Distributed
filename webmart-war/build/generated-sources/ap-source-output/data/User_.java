package data;

import data.OrderClass;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-18T22:23:37")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Integer> admin;
    public static volatile SingularAttribute<User, String> bio;
    public static volatile SingularAttribute<User, Integer> userId;
    public static volatile CollectionAttribute<User, OrderClass> orderClassCollection;
    public static volatile SingularAttribute<User, String> username;

}