package com.devstack.pos.bo.coustom;

import com.devstack.pos.dto.custom.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserBo {
    public  boolean save(UserDto userDto) throws SQLException, ClassNotFoundException;
    public UserDto find(String id) throws SQLException, ClassNotFoundException;
    public  boolean delete(String id) throws SQLException, ClassNotFoundException;
    public  boolean update(UserDto userDto) throws SQLException, ClassNotFoundException;
    public List<UserDto> findAll() throws SQLException, ClassNotFoundException;

}
