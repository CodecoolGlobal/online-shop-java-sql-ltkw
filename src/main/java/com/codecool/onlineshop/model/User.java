
package com.codecool.onlineshop.model;

public class User {

    private Integer id;
    private String name;
    private String password;
    private String userType;
    private Basket basket;


    public User (Integer id, String name, String password, String userType){
        this.id = id;
        this.name = name;
        this.password = password;
        this.userType = userType;
        this.basket = new Basket();
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    public Basket getBasket() {
        return this.basket;
    }

}