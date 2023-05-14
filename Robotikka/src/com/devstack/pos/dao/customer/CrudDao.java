package com.devstack.pos.dao.customer;

import com.devstack.pos.entity.SuperEntity;
import com.devstack.pos.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao <T extends SuperEntity, ID> extends SuperDao{
    public  boolean save(T t) throws SQLException, ClassNotFoundException;
    public T find(ID id) throws SQLException, ClassNotFoundException;
    public  boolean delete(ID id) throws SQLException, ClassNotFoundException;
    public  boolean update(T t) throws SQLException, ClassNotFoundException;
    public List<T> findAll() throws SQLException, ClassNotFoundException;
}
