package com.devstack.pos.controller;

import com.devstack.pos.bo.coustom.BoFactory;
import com.devstack.pos.bo.coustom.ProductBo;
import com.devstack.pos.dao.customer.DaoFactory;
import com.devstack.pos.dao.customer.coustom.impl.ProductDaoImpl;
import com.devstack.pos.dto.custom.ProductDto;
import com.devstack.pos.entity.Product;
import com.devstack.pos.enums.BoType;
import com.devstack.pos.enums.DaoType;
import com.devstack.pos.view.tm.ProductTm;
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

import static java.lang.Integer.parseInt;

public class ProductMainPageController {

    public JFXTextField txtId;
    public TextArea txtDescription;

    public AnchorPane context;
    public JFXButton btnSaveProduct;
    public TextField txtSearch;
    public TableView<ProductTm> tblProduct;
    public TableColumn colId;
    public TableColumn colDesc;
    public TableColumn colShowMore;
    public TableColumn colDelete;
    public TextField txtProductCode2;
    public TextArea txtDescription2;
    public TableView tblbatches;
    public TableColumn col2Number;
    public TableColumn col2Qty;
    public TableColumn col2SellingPrice;
    public TableColumn col2BuyingPrice;
    public TableColumn col2Dav;
    public TableColumn col2ShowPrice;
    public TableColumn col2Dele;

    private String searchText = "";

    ProductBo productBo= BoFactory.getInstance().getBo(BoType.PRODUCT);

    public void initialize() throws SQLException, ClassNotFoundException {

        colId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colShowMore.setCellValueFactory(new PropertyValueFactory<>("shoeMorebtn"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deletebtn"));
        loadAllProducts(txtSearch.getText());
        loadProductId();


        tblProduct.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setData(newValue);
                    }
                });
    }


    public void saveProductOnAction(ActionEvent actionEvent) {
        try {
            ProductDto product = new ProductDto(Integer.parseInt(txtId.getText()), txtDescription.getText());
            if (btnSaveProduct.getText().equals("Save Product")) {
                if (
                        productBo.save(product)
                ) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Product Saved!").show();
                    clearUi();
                    loadAllProducts(txtSearch.getText());
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }
            } else {
                if (
                        productBo.update(product)
                ) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Product Updated!").show();
                    clearFields();
                    loadAllProducts(txtSearch.getText());
                    //---------
                    txtId.setEditable(true);
                    btnSaveProduct.setText("Save Product");
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        clearUi();
    }

    public void newProductOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        clearUi();
        loadProductId();

    }

    private void setUi(String url) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/" + url + ".fxml")))
        );
        stage.centerOnScreen();
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashbordForm");
    }

    private void clearUi() {
        txtDescription.clear();
        txtId.clear();
    }

    private void loadAllProducts(String searchTest) throws SQLException, ClassNotFoundException {
        ObservableList<ProductTm> obList = FXCollections.observableArrayList();
        for (ProductDto product : productBo.findAll()) {
            Button buttonShowMore = new Button("Show more");
            Button buttondelete = new Button("delete");
            ProductTm tm = new ProductTm(product.getCode(), product.getDescription(), buttonShowMore, buttondelete);
            obList.add(tm);

            buttondelete.setOnAction((e) -> {
                try {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> selectedButtonType = alert.showAndWait();
                    if (selectedButtonType.get().equals(ButtonType.YES)) {
                        if (productBo.delete(txtId.getText())) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Product Deleted!").show();
                            loadAllProducts(searchText);
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                        }
                    }
                } catch (SQLException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
                }
            });

        }
        tblProduct.setItems(obList);
    }

    private void clearFields() {
        txtId.clear();
        txtDescription.clear();
    }

    private void loadProductId() throws SQLException, ClassNotFoundException {

        int productId = (productBo.getProductId());
        txtId.setText(String.valueOf(productId));
    }

    private void setData(ProductTm newValue) {
        txtProductCode2.setText(String.valueOf(newValue.getCode()));
        txtDescription2.setText(newValue.getDescription());

    }

    public void newBatchOnAction(ActionEvent actionEvent) {
    }
}
