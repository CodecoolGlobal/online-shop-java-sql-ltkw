package com.codecool.onlineshop.view;

import com.codecool.onlineshop.model.Order;
import com.codecool.onlineshop.model.Product;
import com.codecool.onlineshop.model.User;
import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner scanner = new Scanner(System.in);

    public final String ENTERPRODUCTID = "Enter ID of Product to edit: ";
    public final String ID = "Enter ID of Product to buy: ";
    public final String ENTERPRODUCT = "Enter name of Product: ";
    public final String ENTERCATEGORY = "Enter category of Product: ";
    public final String ENTERPRICE = "Enter price of product: ";
    public final String ENTERAMOUNT = "Enter amount of product: ";
    public final String DELETEPRODUCT = "Enter ID of product to delete: ";
    public final String ENTERNAME = "Enter name of product: ";
    public final String ENTERUSERID = "Enter ID of a user: ";
    public final String ENTERNEWVALUE = "Enter new value: ";
    public final String WRONGID = "Wrong id of product, try again!";
    public final String WRONGAMOUNT = "Wrong amount of product or not enough amount of product in shop, try again!!";
    public final String UPDATED = "Successfully updated.";
    public final String NOOPTION = "There is no such option.";

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String userText (){
        return scanner.nextLine();
    }

    public void showMessage(String message){
        System.out.println(message);
    }

    public void productsTable(List<Product> products){
        TableClass table = new TableClass();
        table.setShowVerticalLines(true);
        table.setHeaders("id","name","category","price","amount","rating");
        for (Product p: products){
            String.valueOf(p.getId());
            table.addRow(String.valueOf(p.getId()),
                         p.getName(),
                         p.getCategory(),
                         String.valueOf(p.getPrice()),
                         String.valueOf(p.getAmount()),
                         String.format("%.2f", p.calculateRating()));
        }
        table.print();
    }
    public void ordersTableUser(List<Order> orderList){
        TableClass table = new TableClass();
        table.setShowVerticalLines(true);
        table.setHeaders("order id","product name","product amount","price");

        for (Order o: orderList){
            table.addRow(String.valueOf(o.getOrderId()),
                         String.valueOf(o.getProductName()),
                         String.valueOf(o.getProductAmount()),
                         String.valueOf(o.getProductAmountPrice()));
        }
        table.print();
    }

    public void ordersTableAdmin(List<Order> orderList){
        TableClass table = new TableClass();
        table.setShowVerticalLines(true);
        table.setHeaders("order id","product id", "product name","product amount","price","user id");

        for (Order o: orderList){
            table.addRow(String.valueOf(o.getOrderId()),
                         String.valueOf(o.getProductId()),
                         String.valueOf(o.getProductName()),
                         String.valueOf(o.getProductAmount()),
                         String.valueOf(o.getProductAmountPrice()),
                         String.valueOf(o.getUserId()));
        }
        table.print();
    }

    public void ordersHistoryTable(List<Order> orderHistoryList) {
        TableClass table = new TableClass();
        table.setShowVerticalLines(true);
        table.setHeaders("order id", "date", "user id","price", "status");

        for (Order oh: orderHistoryList) {
            table.addRow(String.valueOf(oh.getOrderId()),
                         String.valueOf(oh.getDate()),
                         String.valueOf(oh.getUserId()),
                         String.valueOf(oh.getTotalPrice()),
                         String.valueOf(oh.getStatus()));
        }
        table.print();
    }

    public void ordersUserHistoryTable(List<Order> orderHistoryList) {
        TableClass table = new TableClass();
        table.setShowVerticalLines(true);
        table.setHeaders("order id", "date","price", "status");

        for (Order oh: orderHistoryList) {
            table.addRow(String.valueOf(oh.getOrderId()),
                         String.valueOf(oh.getDate()),
                         String.valueOf(oh.getTotalPrice()),
                         String.valueOf(oh.getStatus()));
        }
        table.print();
    }

    public void usersTable(List<User> users){
        TableClass table = new TableClass();
        table.setShowVerticalLines(true);
        table.setHeaders("id","name","password","user type");
        for (User u: users) {
            table.addRow(String.valueOf(u.getId()),
                         String.valueOf(u.getName()),
                         String.valueOf(u.getPassword()),
                         String.valueOf(u.getUserType()));
        }
        table.print();
    }

    public void displayMainMenu() {
        showMessage("\n Welcome to our shop!" + 
                    "\n 1. Log in" + 
                    "\n 2. Create new user" + 
                    "\n 3. Exit.");
    }

    public void displayCustomerMenu() {
        showMessage("Customer menu:" + 
                    "\n  1. Display my basket" +
                    "\n  2. Add product to my basket" +
                    "\n  3. Remove product" +
                    "\n  4. Place an order" +
                    "\n  5. Display my order history" +
                    "\n  6. Display all products" +
                    "\n  7. Display all products from given category"+
                    "\n  8. Edit products quantity"+
                    "\n  9. Rate product"+
                    "\n 10. Change password"+
                    "\n 11. Exit");
    }

    public void displayAdminMenu() {
        showMessage("Admin menu:" +
                    "\n  1. Add new product" +
                    "\n  2. Delete product" +
                    "\n  3. Display products" +
                    "\n  4. Edit product price" +
                    "\n  5. Edit product name" +
                    "\n  6. Edit an amount" +
                    "\n  7. Display all orders" +
                    "\n  8. Make a discount" +
                    "\n  9. Edit user details" +
                    "\n 10. Delete user" +
                    "\n 11. Update order status" +
                    "\n 12. Exit");
    }

    public void displayUpdateStatusMenu() {
        showMessage("Update status to: " +
                    "\n 1. Pending" +
                    "\n 2. Sent" +
                    "\n 3. Delivered" +
                    "\n 4. Canceled" +
                    "\n 0. Exit");
    }

    public void displayUpdateUserDetailsMenu() {
        showMessage("Update: " +
                "\n 1. Name" +
                "\n 2. Password xD" +
                "\n 3. User Type" +
                "\n 0. Exit");
    }

    public int getIntegerInput() {
        while (!scanner.hasNextInt()) {
            showMessage("Wrong input, try again!");
            scanner.nextLine();
        }
        int num = scanner.nextInt();
        scanner.nextLine();
        return num;
    }

    public String getStringInput(){
        return scanner.nextLine();
    }

    public void displayNotImplementedMessage() {
        showMessage("Not implemented yet");
        getEmptyInput();

    }

    public void getEmptyInput() {
        showMessage("Press enter to continue ");
        String input = scanner.nextLine();
    }
}
