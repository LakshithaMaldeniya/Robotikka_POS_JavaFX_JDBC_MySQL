package com.devstack.pos.dao.customer.coustom.impl;

import com.devstack.pos.dao.customer.CrudUtil;
import com.devstack.pos.dao.customer.coustom.ProductDao;
import com.devstack.pos.db.DbConnection;
import com.devstack.pos.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    public static int getProductId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT code FROM product ORDER BY code DESC LIMIT 1");

        if (resultSet.next()) {
            int resultSetInt = resultSet.getInt(1);
            resultSetInt++;
            return resultSetInt;
        }
        return 1;
    }
    @Override
    public boolean save(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO product VALUES (?,?)",product.getCode(),product.getDescription());
    }

    @Override
    public Product find(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM product WHERE code=?",id);
        if (resultSet.next()) {
            return new Product(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM product WHERE code=?",id);
    }

    @Override
    public boolean update(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE product SET description=? WHERE code=?",product.getDescription(),product.getCode());
    }

    @Override
    public List<Product> findAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM product");
        List<Product> products = new ArrayList<>();

        while (resultSet.next()) {
            products.add(new Product(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            ));

        }
        return products;
    }
}
