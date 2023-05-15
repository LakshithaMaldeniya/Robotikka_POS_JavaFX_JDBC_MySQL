package com.devstack.pos.dao.customer.coustom.impl;

import com.devstack.pos.dao.customer.CrudDao;
import com.devstack.pos.dao.customer.CrudUtil;
import com.devstack.pos.dao.customer.coustom.ProductDao;
import com.devstack.pos.dao.customer.coustom.ProductDetailsDao;
import com.devstack.pos.entity.Product;
import com.devstack.pos.entity.ProductDetails;

import java.sql.SQLException;
import java.util.List;

public class ProductDetailsDaoimpl implements ProductDetailsDao {

    @Override
    public boolean save(ProductDetails productDetails) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO product_detail VALUES (?,?,?,?,?,?,?,?)",
                productDetails.getCode(),
                productDetails.getBarcode(),
                productDetails.getQtyOnHand(),
                productDetails.getSellingPrice(),
                productDetails.isDiscountAvailability(),
                productDetails.getShowPrice(),
                productDetails.getProductCode(),
                productDetails.getBuyingPrice());
    }

    @Override
    public ProductDetails find(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ProductDetails productDetails) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<ProductDetails> findAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
