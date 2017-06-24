package com.example.game;

import com.apple.eawt.AppEvent;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;


/**
 * Created by jakeparham on 4/11/16.
 */
public class BlackJack extends Game{
    public static ArrayList<Player> players = PlayerFactory.createPlayers();
    public static ArrayList<Card> deck = Deck.createGameDeck(2);

    public static void start(){
        JFrame frame = new JFrame("BlackJack");
        BlackJack game = new BlackJack();
        frame.add(game);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Deck.shuffle(BlackJack.deck);
        deal(BlackJack.players, BlackJack.deck, 2);

        while (true) {
            game.repaint();
            try {
                Thread.sleep(10);
            } catch(InterruptedException e){
                System.out.println(e);
            }
        }

//        //Play Game
//        while(!(allDone(players))){
//            for(Player curr : players){
//                showHands(players);
//                playerTurn(curr, deck);
//            }
//        }
//
//        System.out.println("We out!");
//        //Finish Game
//        Player winner = determineWinner(players);
//        System.out.println("Winner is: "+winner.name);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x = 15;
        int y = 15;

        Graphics2D g2d = (Graphics2D) g;

        for(Player player : BlackJack.players){
            for(Card card : player.hand){
                try {
                    BufferedImage image = ImageIO.read(new File(card.img));
                    image.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
                    g2d.drawImage(image, null, x, y);
                } catch(IOException e){
                    System.out.println(e + " " + card.img);
                }
                x += 50;
            }
            y += 100;
        }
    }

    private static void showHands(ArrayList<Player> players){
        System.out.println("-------------Players hands-------------");
        for(Player showPlayer : players){
            //Print the dealers cards
            if(showPlayer.name.equals("Dealer")){
                System.out.print("Dealer: ");
                for(Card showCard : showPlayer.hand) {
                    System.out.print(showCard + " ");
                }
                System.out.print("\n");
                //Print everyone elses cards
            } else {
                System.out.print(showPlayer.name + ": ");
                for(Card showCard : showPlayer.hand){
                    if(showCard.equals(showPlayer.hand.get(0))) {
                        System.out.print("Hidden Card - ");
                    } else {
                        System.out.print(showCard + " ");
                    }
                }
                System.out.print("\n");
            }
        }
        System.out.println("---------------------------------------");
    }

    private static void playerTurn(Player curr, ArrayList<Card> deck){
        if(!curr.stand && !curr.bust) {
            System.out.println(curr.name + "s turn");
            System.out.print("Your hand is: ");
            for (Card card : curr.hand) {
                System.out.print(card + " - ");
            }
            System.out.print("\n");
            System.out.println("Hand total: " + convertHand(curr));
            int action = promptAction();
            resolveAction(action, curr, deck);
        }
    }

    private static boolean allDone(ArrayList<Player> players){
        boolean allPlayersStand = true;

        for(Player curr : players){
            if(!curr.stand && !curr.bust){
                allPlayersStand = false;
            }
        }
        return allPlayersStand;
    }

    private static int convertHand(Player curr){
        int total = 0;
        Scanner read = new Scanner(System.in);

        for(Card card : curr.hand){
            switch(card.rank){
                case 2:
                    total += 2;
                    break;
                case 3:
                    total += 3;
                    break;
                case 4:
                    total += 4;
                    break;
                case 5:
                    total += 5;
                    break;
                case 6:
                    total += 6;
                    break;
                case 7:
                    total += 7;
                    break;
                case 8:
                    total += 8;
                    break;
                case 9:
                    total += 9;
                    break;
                case 10:
                    total += 10;
                    break;
                case 11:
                    total += 10;
                    break;
                case 12:
                    total += 10;
                    break;
                case 13:
                    total += 10;
                    break;
                case 14:
                    if(!(card.aceVal == 1 || card.aceVal == 11)) {
                        System.out.println("You have an ace! Assign value of 1 or 11");
                        int aceVal = read.nextInt();
                        while(!(aceVal == 1 || aceVal == 11)){
                            System.out.println("Incorrect value assignment! You chose "+aceVal+", it must be 1 or 11.");
                            aceVal = read.nextInt();
                        }
                        card.setAceVal(aceVal);
                        total += card.aceVal;
                    } else {
                        total += card.aceVal;
                    }
                    break;
            }
        }

        return total;
    }

    private static int promptAction(){
        int action = 0;
        ArrayList<Integer> choices = new ArrayList<>(Arrays.asList(1,2, 3, 4));

        while(!(choices.contains(action))){
            Scanner read = new Scanner(System.in);
            System.out.println("What do you want to do?");
            System.out.println("1) Hit");
            System.out.println("2) Stand");
            System.out.println("3) Double Down");
            System.out.println("4) Split");
            System.out.println();
            System.out.print("Action: ");
            action = read.nextInt();
        }

        return action;
    }

    private static void resolveAction(int action, Player curr, ArrayList<Card> deck){
        switch(action){
            case 1:
                hit(curr, deck);
                break;
            case 2:
                stand(curr);
                break;
            case 3:
                doubledown(curr);
                break;
            case 4:
                split(curr);
                break;
            default:
                System.out.println("You didn't select a valid choice.");
        }
    }

    private static Player determineWinner(ArrayList<Player> players){
        int topHand = convertHand(players.get(0));
        Player currWinner = players.get(0);

        for(Player curr : players){
            if(convertHand(curr) > topHand){
                topHand = convertHand(curr);
                currWinner = curr;
            }
        }

        return currWinner;
    }

    //Action choices
    private static void hit(Player curr, ArrayList<Card> deck){
        curr.hand.add(deck.remove(getRandIndx(deck.size())));
        int total;

        if((total = convertHand(curr)) > 21){
            curr.setBust(true);
            System.out.println(curr.name+" busts with "+total);
        }
    }

    private static void stand(Player curr){ curr.setStand(true); }
    private static void doubledown(Player curr){}
    private static void split(Player curr){}


    public static void deal(ArrayList<Player> players, ArrayList<Card> deck, int cardsToDeal){
        for(int i=0; i<cardsToDeal; ++i){
            for(Player curr : players) {
                curr.hand.add(deck.remove(getRandIndx(deck.size())));
            }
        }
    }

    private static int getRandIndx(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }
}
