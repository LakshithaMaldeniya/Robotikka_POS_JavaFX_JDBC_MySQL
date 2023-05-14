package com.devstack.pos.bo.coustom;

import com.devstack.pos.bo.coustom.impl.CustomerBoImpl;
import com.devstack.pos.bo.coustom.impl.ProductBoImpl;
import com.devstack.pos.bo.coustom.impl.UserBoImpl;
import com.devstack.pos.dao.customer.DaoFactory;
import com.devstack.pos.dao.customer.coustom.impl.CustomerDaoImpl;
import com.devstack.pos.dao.customer.coustom.impl.ProductDaoImpl;
import com.devstack.pos.dao.customer.coustom.impl.UserDaoImpl;
import com.devstack.pos.enums.BoType;
import com.devstack.pos.enums.DaoType;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getInstance() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public <T> T getBo(BoType daoType) {
        switch (daoType) {
            case USER:
                return (T) new UserBoImpl();
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            case PRODUCT:
                return (T) new ProductBoImpl();
            default:
                return null;
        }
    }

}
