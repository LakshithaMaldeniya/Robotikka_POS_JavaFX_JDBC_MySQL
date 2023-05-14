package com.devstack.pos.dao.customer.coustom.impl;

import com.devstack.pos.dao.customer.CrudUtil;
import com.devstack.pos.dao.customer.coustom.UserDao;
import com.devstack.pos.db.DbConnection;
import com.devstack.pos.entity.User;
import com.devstack.pos.util.PasswordManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(User use) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO user VALUES (?,?)",use.getEmail(),PasswordManager.encryptPassword(use.getPassword()));
    }

    @Override
    public User find(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM user WHERE email=?",id);
        if (set.next()) {
            return new User(set.getString(1), set.getString(2));
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
