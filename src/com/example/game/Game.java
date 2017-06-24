package com.example.game;
import javax.swing.*;
import java.util.*;
/**
 * Created by jakeparham on 4/8/16.
 */
public class Game extends JPanel{
    public static void main(String[] args){
        startGame(chooseGame());
    }

    public static int chooseGame(){
        ArrayList<Integer> choices = new ArrayList<>(Arrays.asList(1,2));
        int choice = 0;

        while(!choices.contains(choice)) {
            Scanner read = new Scanner(System.in);
            System.out.println("Choose card game:");
            System.out.println("1) BlackJack");
            System.out.println("2) Exit");
            System.out.println();
            System.out.print("Choice: ");

            choice = read.nextInt();
        }

        return choice;
    }

    public static void startGame(int choice){
        switch(choice){
            case 1:
                BlackJack.start();
                break;
            case 2:
                System.out.println("Exiting game...");
                System.exit(0);
                break;
            default:
                System.out.println("You didn't select a valid choice.");
        }
    }
}
