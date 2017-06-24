package com.example.game;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jakeparham on 4/10/16.
 */
class PlayerFactory {
    public static ArrayList<Player> createPlayers(){
        ArrayList<Player> players = new ArrayList<>();
        Scanner read = new Scanner(System.in);
        System.out.print("How many people are playing? ");
        int playerCount = read.nextInt();
        read.nextLine();

        //Create the players
        while(playerCount!=0){
            System.out.print("Enter player name: ");
            String name = read.nextLine();
            ArrayList<Card> hand = new ArrayList<>();

            Player player = new Player(name, hand);
            players.add(player);
            playerCount--;
        }

        ArrayList<Card> hand = new ArrayList<>();
        Player player = new Player("Dealer", hand);
        players.add(player);

        return players;
    }
}
