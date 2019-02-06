package com.codecool.onlineshop.view;
import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner scanner = new Scanner(System.in);
    private TableClass table = new TableClass();

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

    // public void productsTable (List products){

    //     table.setShowVerticalLines(true);

    //     table.setHeaders("id","name","category","price","amount");   //tutaj umieszczamy naglowki, koniecznie jako string

    //     table.addRow(id,name,Cateogry, price, amount);               // ta funkcja sluzy do przyjmowania pojedynczego rzedu

    //     table.print();
    // }

    public void displayMainMenu() {
        showMessage("\n Welcome to our shop!" + 
                    "\n 1. Log in" + 
                    "\n 2. Create new user" + 
                    "\n 3. Exit.");
    }

    public void displayCustomerMenu() {
        showMessage("\n 1. Display my basket" + 
                    "\n 2. Add product" + 
                    "\n 3. Remove product" +
                    "\n 4. Place an order" +
                    "\n 5. Display my order history" +
                    "\n 6. Display all products" + 
                    "\n 7. Exit");
    }

    public void displayAdminMenu() {
        showMessage("\n 1. Add new product" + 
                    "\n 2. Edit product" + 
                    "\n 3. Disable product" +
                    "\n 4. Add new category" +
                    "\n 5. Edit a category" +
                    "\n 6. Display all orders" + 
                    "\n 7. Make a discount" +
                    "\n 8. Exit");
    }

    public int getIntegerInput() {
        while (!scanner.hasNextInt()) {
            showMessage("Wrong input.");
            scanner.nextLine();
        }
        int num = scanner.nextInt();
        scanner.nextLine();
        return num;
    }


}
