package com.devstack.pos.bo.coustom.impl;

import com.devstack.pos.bo.coustom.UserBo;
import com.devstack.pos.dao.customer.DaoFactory;
import com.devstack.pos.dao.customer.coustom.UserDao;
import com.devstack.pos.dto.custom.UserDto;
import com.devstack.pos.entity.User;
import com.devstack.pos.enums.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {

    UserDao userDao= DaoFactory.getInstance().getDao(DaoType.USER);
    @Override
    public boolean save(UserDto userDto) throws SQLException, ClassNotFoundException {
        return userDao.save(new User(userDto.getEmail(),userDto.getPassword()));
    }

    @Override
    public UserDto find(String id) throws SQLException, ClassNotFoundException {
        User user = userDao.find(id);
        return new UserDto(user.getEmail(),user.getPassword());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return userDao.delete(id);
    }

    @Override
    public boolean update(UserDto userDto) throws SQLException, ClassNotFoundException {
        return userDao.update(new User(userDto.getEmail(),userDto.getPassword()));
    }

    @Override
    public List<UserDto> findAll() throws SQLException, ClassNotFoundException {
        List<User> userList = userDao.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User us:userList
             ) {
            new UserDto(us.getEmail(),us.getPassword());
        }
        return userDtoList;
    }
}
