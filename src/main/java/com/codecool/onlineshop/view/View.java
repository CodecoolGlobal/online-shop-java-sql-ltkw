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

    public void productsTable (List products){

        table.setShowVerticalLines(true);

        table.setHeaders("id","name","category","price","amount");   //tutaj umieszczamy naglowki, koniecznie jako string

        table.addRow(id,name,Cateogry, price, amount);               // ta funkcja sluzy do przyjmowania pojedynczego rzedu

        table.print();
    }


}
