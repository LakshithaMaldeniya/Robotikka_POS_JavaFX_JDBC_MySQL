package com.devstack.pos.dao.customer;

import com.devstack.pos.dao.customer.coustom.impl.CustomerDaoImpl;
import com.devstack.pos.dao.customer.coustom.impl.ProductDaoImpl;
import com.devstack.pos.dao.customer.coustom.impl.UserDaoImpl;
import com.devstack.pos.enums.DaoType;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DaoFactory() : daoFactory;
    }

    public <T> T getDao(DaoType daoType) {
        switch (daoType) {
            case USER:
                return (T) new UserDaoImpl();
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case PRODUCT:
                return (T) new ProductDaoImpl();
            default:
                return null;
        }
    }

}
