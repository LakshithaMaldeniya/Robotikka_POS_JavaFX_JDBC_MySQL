package com.devstack.pos.controller;

import com.devstack.pos.bo.coustom.BoFactory;
import com.devstack.pos.bo.coustom.CustomerBo;
import com.devstack.pos.dao.customer.DaoFactory;
import com.devstack.pos.dao.customer.coustom.impl.CustomerDaoImpl;
import com.devstack.pos.dto.custom.CustomerDto;
import com.devstack.pos.entity.Customer;
import com.devstack.pos.enums.BoType;
import com.devstack.pos.enums.DaoType;
import com.devstack.pos.view.tm.CustomerTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerFormController {
    public AnchorPane context;
    public JFXTextField txtemail;
    public JFXTextField txtName;
    public JFXTextField txtContactNumber;
    public JFXTextField txtSalary;
    public TextField txtSearch;
    public TableView<CustomerTm> tblCoustomer;
    public TableColumn colId;
    public TableColumn colEmail;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colSalary;
    public TableColumn colOption;
    public JFXButton btnSaveUpdate;

    private String searchText="";

    CustomerBo customerBo= BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        loadAllCustomers(searchText);

        tblCoustomer.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue!=null){
                        setData(newValue);
                    }
                });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            try {
                loadAllCustomers(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

    }
    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashbordForm");
    }

    public void LoyaltyCardManagentOnAction(ActionEvent actionEvent) {
    }

    public void NewCoustomerOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void saveCoustomerOnAction(ActionEvent actionEvent){
        CustomerDaoImpl customerDao= DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
        try{
            CustomerDto customer=new CustomerDto(txtemail.getText(),txtName.getText(),txtContactNumber.getText(),Double.parseDouble(txtSalary.getText()));
            if (btnSaveUpdate.getText().equals("Save Customer")){
                if (customerBo.save(customer)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved!").show();
                    clearFields();
                    loadAllCustomers(txtSearch.getText());
                }else{
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }
            }else{
                if (customerBo.update(customer)){
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated!").show();
                    clearFields();
                    loadAllCustomers(txtSearch.getText());
                    //---------
                    txtemail.setEditable(true);
                    btnSaveUpdate.setText("Save Customer");
                }else{
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        clearFields();
    }

    private void clearFields() {
        txtemail.clear();
        txtName.clear();
        txtContactNumber.clear();
        txtSalary.clear();
    }

    private void setUi(String url) throws IOException {
        Stage stage = (Stage)context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+url+".fxml")))
        );
        stage.centerOnScreen();
    }

    private void  loadAllCustomers(String searchText) throws SQLException, ClassNotFoundException {
        ObservableList<CustomerTm> observableList = FXCollections.observableArrayList();
        int counter=1;

        for (CustomerDto customer : searchText.length()>0?customerBo.searchCustomer(searchText):customerBo.findAll()){
            Button btn = new Button("Delete");
            CustomerTm tm = new CustomerTm(
                    counter, customer.getEmail(), customer.getName(), customer.getContact(), customer.getSalary(),
                    btn
            );
            observableList.add(tm);
            counter++;

            btn.setOnAction((e)->{
                try{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?", ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> selectedButtonType = alert.showAndWait();
                    if (selectedButtonType.get().equals(ButtonType.YES)){
                        if (customerBo.delete(txtemail.getText())){
                            new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!").show();
                            loadAllCustomers(searchText);
                        }else{
                            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                        }
                    }
                }catch (SQLException | ClassNotFoundException exception){
                    exception.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
                }
            });

        }
        tblCoustomer.setItems(observableList);
    }

    private void setData(CustomerTm newValue) {
        txtemail.setEditable(false);
        btnSaveUpdate.setText("Update Customer");
        txtemail.setText(newValue.getEmail());
        txtName.setText(newValue.getName());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
        // txtSalary.setText(""+newValue.getSalary());
        txtContactNumber.setText(newValue.getContact());
    }

}
