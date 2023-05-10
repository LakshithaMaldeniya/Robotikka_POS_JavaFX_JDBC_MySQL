package com.devstack.pos.dao;

import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.dto.UserDto;
import com.devstack.pos.util.DbConnection;
import com.devstack.pos.util.PasswordManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccessCode {

    // ---------- UserManagement ---------------------------------

    public static boolean createUser(String email, String password) throws SQLException, ClassNotFoundException {

        String sql="INSERT INTO user VALUES (?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, PasswordManager.encryptPassword(password));
        return preparedStatement.executeUpdate()>0;
    }

    public static UserDto findUser(String email) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, email);

        ResultSet set = preparedStatement.executeQuery();
        if (set.next()){
            return new UserDto(set.getString(1),set.getString(2));
        }else {
            return null;
        }
    }

    //-------------------------- Customer Management ----------------------------

    public static boolean createCustomer(String email, String name, String contact, double salary ) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO customer VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, contact);
        preparedStatement.setDouble(4, salary);
        return preparedStatement.executeUpdate()>0;
    }
    public static boolean updateCustomer(String email, String name, String contact, double salary ) throws SQLException, ClassNotFoundException {
        String sql="UPDATE customer SET name=?, contact=?, salary=? WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, contact);
        preparedStatement.setDouble(3, salary);
        preparedStatement.setString(4, email);
        return preparedStatement.executeUpdate()>0;

    }
    public static CustomerDto findCustomer(String email ) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM customer WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet= preparedStatement.executeQuery();
        if (resultSet.next()){
            return new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;

    }
    public static boolean deleteCustomer(String email ) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM Customer WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, email);
        return preparedStatement.executeUpdate()>0;
    }
    public static List<CustomerDto> findAllCustomer() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM customer";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet= preparedStatement.executeQuery();
        System.out.println();
        List<CustomerDto> dtos= new ArrayList<>();

        while (resultSet.next()){
            dtos.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));

        }
        System.out.println(dtos);
        return dtos;


    }
    public static List<CustomerDto> searchCustomer(String searchText) throws SQLException, ClassNotFoundException {

        searchText="%"+searchText+"%";

        String sql="SELECT * FROM customer WHERE email LIKE ? || name LIKE ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,searchText);
        preparedStatement.setString(2,searchText);
        ResultSet resultSet= preparedStatement.executeQuery();

        List<CustomerDto> dtos= new ArrayList<>();
        while (resultSet.next()){
            dtos.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return dtos;
    }
}