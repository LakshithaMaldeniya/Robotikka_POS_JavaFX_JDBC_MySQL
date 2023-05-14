package com.devstack.pos.bo.coustom;

import com.devstack.pos.db.DbConnection;
import com.devstack.pos.dto.custom.ProductDto;
import com.devstack.pos.dto.custom.UserDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ProductBo {
    public  boolean save(ProductDto productDto) throws SQLException, ClassNotFoundException;
    public ProductDto find(String id) throws SQLException, ClassNotFoundException;
    public  boolean delete(String id) throws SQLException, ClassNotFoundException;
    public  boolean update(ProductDto productDto) throws SQLException, ClassNotFoundException;
    public List<ProductDto> findAll() throws SQLException, ClassNotFoundException;

    public int getProductId() throws SQLException, ClassNotFoundException;
}
