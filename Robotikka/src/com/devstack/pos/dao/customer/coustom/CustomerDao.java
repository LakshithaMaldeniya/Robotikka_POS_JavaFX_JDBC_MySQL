package com.devstack.pos.dao.customer.coustom;

import com.devstack.pos.dao.customer.CrudDao;
import com.devstack.pos.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao extends CrudDao<Customer, String> {
//    public  boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException;
//    public  Customer findCustomer(String id) throws SQLException, ClassNotFoundException;
//    public  boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
//    public  boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException;
//    public List<Customer> findAllCustomer(String id) throws SQLException, ClassNotFoundException;


    public List<Customer> searchCustomer(String id) throws SQLException, ClassNotFoundException;
}
