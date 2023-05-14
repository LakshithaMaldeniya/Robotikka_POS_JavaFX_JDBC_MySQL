package com.devstack.pos.view.tm;

import javafx.scene.control.Button;

public class ProductTm {
    private int code;
    private String description;

   private Button shoeMorebtn;
   private Button deletebtn;

    public ProductTm() {
    }

    public ProductTm(int code, String description, Button shoeMorebtn, Button deletebtn) {
        this.code = code;
        this.description = description;
        this.shoeMorebtn = shoeMorebtn;
        this.deletebtn = deletebtn;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Button getShoeMorebtn() {
        return shoeMorebtn;
    }

    public void setShoeMorebtn(Button shoeMorebtn) {
        this.shoeMorebtn = shoeMorebtn;
    }

    public Button getDeletebtn() {
        return deletebtn;
    }

    public void setDeletebtn(Button deletebtn) {
        this.deletebtn = deletebtn;
    }
}
