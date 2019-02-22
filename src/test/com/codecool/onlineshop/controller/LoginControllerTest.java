package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private List<User> fakeCustomerUsers = new ArrayList<>();
    private List<User> fakeAdminUsers = new ArrayList<>();
    LoginController loginController = new LoginController();

    @BeforeEach
    public void initUsersList(){
        fakeCustomerUsers.add(new User(1,"Kamil1","Kamil1","CUSTOMER"));
        fakeAdminUsers.add(new User(1,"Liz1","Liz1","ADMIN"));
    }

    @Test
    public void IfHandleLoginMethodFindsCustomerUsers_true(){
        User actualluser = loginController.handleLogin(fakeCustomerUsers);
        assertEquals(fakeCustomerUsers.get(0),actualluser);

    }
    @Test
    public void IfHandleLoginMethodFindsAdminCustomerusers_true(){
        User actuallUser = loginController.handleLogin(fakeAdminUsers);
        assertEquals(fakeAdminUsers.get(0),actuallUser);


    }


}