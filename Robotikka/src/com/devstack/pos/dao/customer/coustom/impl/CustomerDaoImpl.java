package com.devstack.pos.dao.customer.coustom.impl;

import com.devstack.pos.dao.customer.CrudUtil;
import com.devstack.pos.dao.customer.coustom.CustomerDao;
import com.devstack.pos.db.DbConnection;
import com.devstack.pos.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?)",customer.getEmail(),customer.getName(),customer.getContactNumber(),customer.getSalary());
    }

    @Override
    public Customer find(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer WHERE email=?", id);
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer WHERE email=?", id);
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE customer SET name=?, contact=?, salary=? WHERE email=?", customer.getName(), customer.getContactNumber(), customer.getSalary(), customer.getEmail());
    }

    @Override
    public List<Customer> findAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");
        List<Customer> customerList = new ArrayList<>();

        while (resultSet.next()) {
            customerList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));

        }
        return customerList;
    }

    @Override
    public List<Customer> searchCustomer(String searchText) throws SQLException, ClassNotFoundException {
        searchText = "%" + searchText + "%";
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer WHERE email LIKE ? || name LIKE ?", searchText, searchText);

        List<Customer> dtos = new ArrayList<>();
        while (resultSet.next()) {
            dtos.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return dtos;
    }

}
