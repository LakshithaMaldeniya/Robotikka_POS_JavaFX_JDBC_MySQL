package com.devstack.pos.bo.coustom.impl;

import com.devstack.pos.bo.coustom.ProductBo;
import com.devstack.pos.bo.coustom.ProductDetailsBo;
import com.devstack.pos.dao.customer.DaoFactory;
import com.devstack.pos.dao.customer.coustom.ProductDetailsDao;
import com.devstack.pos.dto.custom.ProductDetailsDto;
import com.devstack.pos.entity.ProductDetails;
import com.devstack.pos.enums.DaoType;

import java.sql.SQLException;
import java.util.List;

public class ProductDetailsBoImpl implements ProductDetailsBo {

    ProductDetailsDao productDetailsDao= DaoFactory.getInstance().getDao(DaoType.PRODUCT_DETAIL);
    @Override
    public boolean save(ProductDetailsDto productDetailsDto) throws SQLException, ClassNotFoundException {
        return productDetailsDao.save(
                new ProductDetails(
                        productDetailsDto.getCode(),
                        productDetailsDto.getBarcode(),
                        productDetailsDto.getQtyOnHand(),
                        productDetailsDto.getSellingPrice(),
                        productDetailsDto.isDiscountAvailability(),
                        productDetailsDto.getShowPrice(),
                        productDetailsDto.getProductCode(),
                        productDetailsDto.getBuyingPrice()));
    }

    @Override
    public List<ProductDetails> findAll() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

}
