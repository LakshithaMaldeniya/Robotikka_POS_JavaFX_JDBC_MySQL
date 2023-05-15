package com.devstack.pos.bo.coustom;

import com.devstack.pos.dto.custom.CustomerDto;
import com.devstack.pos.dto.custom.ProductDetailsDto;
import com.devstack.pos.dto.custom.ProductDto;
import com.devstack.pos.entity.ProductDetails;

import java.sql.SQLException;
import java.util.List;

public interface ProductDetailsBo {
    public  boolean save(ProductDetailsDto productDetailsDto) throws SQLException, ClassNotFoundException;

    public List<ProductDetails> findAll();

    public boolean delete(String id);
}
