package com.example.souq;

public class Seller extends User{
   String product_type;
    String id;




    public Seller(String name, String mail, String phone, String password, String product_type, String id ) {
        super(name, mail, phone, password);
        this.product_type = product_type;
        this.id = id;

    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
