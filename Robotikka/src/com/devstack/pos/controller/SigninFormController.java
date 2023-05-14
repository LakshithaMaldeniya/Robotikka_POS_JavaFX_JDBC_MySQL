package com.devstack.pos.controller;

import com.devstack.pos.bo.coustom.BoFactory;
import com.devstack.pos.bo.coustom.UserBo;
import com.devstack.pos.dao.customer.DaoFactory;
import com.devstack.pos.dao.customer.coustom.impl.UserDaoImpl;
import com.devstack.pos.dto.custom.UserDto;
import com.devstack.pos.entity.User;
import com.devstack.pos.enums.BoType;
import com.devstack.pos.enums.DaoType;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SigninFormController {
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public AnchorPane context;

    UserBo userBo= BoFactory.getInstance().getBo(BoType.USER);

    public void btnRegisterNowOnAction(ActionEvent actionEvent) {
        try {
            UserDto user=new UserDto(txtEmail.getText(), txtPassword.getText());
            if (userBo.save(user)){
                new Alert(Alert.AlertType.CONFIRMATION, "User Saved!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnAlreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    private void setUi(String url) throws IOException {
        Stage stage = (Stage)context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+url+".fxml")))
        );
        stage.centerOnScreen();
    }
}
