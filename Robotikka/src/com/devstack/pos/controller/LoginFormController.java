package com.devstack.pos.controller;

import com.devstack.pos.bo.coustom.BoFactory;
import com.devstack.pos.bo.coustom.UserBo;
import com.devstack.pos.dao.customer.DaoFactory;
import com.devstack.pos.dao.customer.coustom.impl.UserDaoImpl;
import com.devstack.pos.dto.custom.UserDto;
import com.devstack.pos.entity.User;
import com.devstack.pos.enums.BoType;
import com.devstack.pos.enums.DaoType;
import com.devstack.pos.util.PasswordManager;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginFormController {
    public JFXTextField txtEmail;

    public AnchorPane context;
    public JFXPasswordField txtPassword;

    UserBo userBo= BoFactory.getInstance().getBo(BoType.USER);

    public void btnSignInOnAction(ActionEvent actionEvent) {
        try {
            UserDto ud= userBo.find(txtEmail.getText());
            if (null!=ud) {
                if (PasswordManager.checkPassword(txtPassword.getText(), ud.getPassword())) {
                    System.out.println("Completed");
                    setUi("DashbordForm");
                } else {
                    new Alert(Alert.AlertType.WARNING, "check your password and try again!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "User email not found!").show();
            }


        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void btnCreateAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SigninForm");
    }

    private void setUi(String url) throws IOException {
        Stage stage = (Stage)context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+url+".fxml")))
        );
        stage.centerOnScreen();
    }

}
