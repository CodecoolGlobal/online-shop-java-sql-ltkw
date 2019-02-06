package com.codecool.onlineshop.view;
import java.util.Scanner;

public class View {

    private Scanner scanner = new Scanner(System.in);

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

    

}
