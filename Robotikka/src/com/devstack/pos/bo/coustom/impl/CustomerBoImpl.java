package com.devstack.pos.bo.coustom.impl;

import com.devstack.pos.bo.coustom.CustomerBo;
import com.devstack.pos.dao.customer.DaoFactory;
import com.devstack.pos.dao.customer.coustom.CustomerDao;
import com.devstack.pos.dto.custom.CustomerDto;
import com.devstack.pos.entity.Customer;
import com.devstack.pos.enums.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    CustomerDao customerDao= DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    @Override
    public boolean save(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDao.save(new Customer(customerDto.getEmail(),customerDto.getName(),customerDto.getContact(),customerDto.getSalary()));
    }

    @Override
    public CustomerDto find(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDao.find(id);
        return new CustomerDto(customer.getEmail(),customer.getName(),customer.getContactNumber(),customer.getSalary());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public boolean update(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(customerDto.getEmail(),customerDto.getName(),customerDto.getContact(),customerDto.getSalary()));
    }

    @Override
    public List<CustomerDto> findAll() throws SQLException, ClassNotFoundException {
        List<Customer> customerList = customerDao.findAll();
        List<CustomerDto> customerDtoList=new ArrayList<>();
        for (Customer dao:customerList
             ) {
            customerDtoList.add(new CustomerDto(dao.getEmail(),dao.getName(),dao.getContactNumber(),dao.getSalary()));
        }
        return customerDtoList;
    }

    @Override
    public List<CustomerDto> searchCustomer(String id) throws SQLException, ClassNotFoundException {
        List<Customer> customerList = customerDao.searchCustomer(id);
        List<CustomerDto> customerDtoList=new ArrayList<>();
        for (Customer dao:customerList
        ) {
            customerDtoList.add(new CustomerDto(dao.getEmail(),dao.getName(),dao.getContactNumber(),dao.getSalary()));
        }
        return customerDtoList;
    }
}

