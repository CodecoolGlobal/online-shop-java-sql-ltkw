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

    public void productsTable (ArrayList<ArrayList> products){

        table.setShowVerticalLines(true);

        table.setHeaders("id","name","category","price","amount");
        for (ArrayList l: products){
            table.addRow(l.get(0).toString(),l.get(1).toString(),l.get(2).toString(),l.get(3).toString(),l.get(4).toString());
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
