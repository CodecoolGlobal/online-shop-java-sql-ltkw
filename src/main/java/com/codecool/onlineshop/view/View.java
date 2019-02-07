package com.codecool.onlineshop.view;
import com.codecool.onlineshop.model.Product;

import java.util.ArrayList;
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

    public void productsTable (ArrayList<Product> products){

        table.setShowVerticalLines(true);

        table.setHeaders("id","name","category","price","amount");
        for (Product p: products){
            table.addRow(p.getId(),p.getName());
        }
        table.print();
    }

    /* sample usage

    public void test(){
        View view = new View();
        ArrayList<ArrayList> nested = new ArrayList<ArrayList>();
        for (Product p: products) {
            nested.add(p.productParamToString());


        }
        view.productsTable(nested);
    }
     */


}
