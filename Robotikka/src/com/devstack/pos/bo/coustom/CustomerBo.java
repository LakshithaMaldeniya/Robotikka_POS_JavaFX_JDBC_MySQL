package com.devstack.pos.bo.coustom;

import com.devstack.pos.dto.custom.CustomerDto;
import com.devstack.pos.dto.custom.UserDto;
import com.devstack.pos.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo {
    public  boolean save(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    public CustomerDto find(String id) throws SQLException, ClassNotFoundException;
    public  boolean delete(String id) throws SQLException, ClassNotFoundException;
    public  boolean update(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    public List<CustomerDto> findAll() throws SQLException, ClassNotFoundException;

    //====================
    public List<CustomerDto> searchCustomer(String id) throws SQLException, ClassNotFoundException;
}
