package com.devstack.pos.bo.coustom.impl;

import com.devstack.pos.bo.coustom.ProductBo;
import com.devstack.pos.dao.customer.DaoFactory;
import com.devstack.pos.dao.customer.coustom.ProductDao;
import com.devstack.pos.dao.customer.coustom.impl.ProductDaoImpl;
import com.devstack.pos.dto.custom.ProductDto;
import com.devstack.pos.entity.Product;
import com.devstack.pos.enums.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBoImpl implements ProductBo {

    ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);

    @Override
    public boolean save(ProductDto productDto) throws SQLException, ClassNotFoundException {
        return productDao.save(new Product(productDto.getCode(), productDto.getDescription()));
    }

    @Override
    public ProductDto find(String id) throws SQLException, ClassNotFoundException {
        Product product = productDao.find(id);
        return new ProductDto(product.getCode(), product.getDescription());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return productDao.delete(id);
    }

    @Override
    public boolean update(ProductDto productDto) throws SQLException, ClassNotFoundException {
        return productDao.update(new Product(productDto.getCode(), productDto.getDescription()));
    }

    @Override
    public List<ProductDto> findAll() throws SQLException, ClassNotFoundException {
        List<Product> productList = productDao.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product Dao : productList
        ) {
            productDtoList.add(new ProductDto(Dao.getCode(), Dao.getDescription()));
        }
        return productDtoList;
    }

    @Override
    public int getProductId() throws SQLException, ClassNotFoundException {
        return ProductDaoImpl.getProductId();
    }
}
