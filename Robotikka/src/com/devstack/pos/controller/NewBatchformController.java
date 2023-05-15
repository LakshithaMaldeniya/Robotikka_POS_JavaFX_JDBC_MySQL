package com.devstack.pos.controller;

import com.devstack.pos.bo.coustom.BoFactory;
import com.devstack.pos.bo.coustom.ProductDetailsBo;
import com.devstack.pos.dto.custom.ProductDetailsDto;
import com.devstack.pos.dto.custom.ProductDto;
import com.devstack.pos.entity.ProductDetails;
import com.devstack.pos.enums.BoType;
import com.devstack.pos.util.QrDataGenerator;
import com.devstack.pos.view.tm.ProductDetailsTm;
import com.devstack.pos.view.tm.ProductTm;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class NewBatchformController {
    public ImageView imgQr;
    public TextField txtQty;
    public TextField txtSellingPrice;
    public TextField txtBuyingPrice;
    public TextField txtShowPrice;
    public TextField txtProductId;
    public TextArea txtDescription;
    public RadioButton rbtnYes;
    public RadioButton rbtnNo;
    public AnchorPane context;
    String uniqueData = null;
    BufferedImage bufferedImage = null;
    Stage stage = null;

    ProductDetailsBo productDetailsBo = BoFactory.getInstance().getBo(BoType.PRODUCT_DETAIL);

    public void initialize() throws WriterException {
        setQrCode();
    }

    private void setQrCode() throws WriterException {
        uniqueData = QrDataGenerator.generateQr(25);
        //----------------------Gen QR
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        bufferedImage =
                MatrixToImageWriter.toBufferedImage(
                        qrCodeWriter.encode(
                                uniqueData,
                                BarcodeFormat.QR_CODE,
                                160, 160
                        )
                );
        //----------------------Gen QR
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        imgQr.setImage(image);
    }

    public void saveBatchOnAction(ActionEvent actionEvent) throws ClassNotFoundException, RuntimeException {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            javax.imageio.ImageIO.write(bufferedImage, "png", baos);
            byte[] arr = baos.toByteArray();

            boolean issaved = productDetailsBo.save(new ProductDetailsDto(
                    uniqueData,
                    Base64.encodeBase64String(arr),
                    Integer.parseInt(txtQty.getText()),
                    Double.parseDouble(txtSellingPrice.getText()),
                    rbtnYes.isSelected() ? true : false,
                    Double.parseDouble(txtShowPrice.getText()),
                    Integer.parseInt(txtProductId.getText()),
                    Double.parseDouble(txtBuyingPrice.getText())
            ));


            if (issaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Try Again");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void loadInitialData(int code, String description) {
        txtProductId.setText(String.valueOf(code));
        txtDescription.setText(description);
    }
}


