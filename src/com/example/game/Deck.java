package com.example.game;
import java.util.*;
/**
 * Created by jakeparham on 4/8/16.
 */
class Deck {
    ArrayList<Card> deck;

    private Deck(){
        this.deck = new ArrayList<>();
    }

    private static ArrayList<Card> generate(){
        ArrayList<Card> deck = new ArrayList<>();

        for(int i=0; i<4; ++i){
            for(int j=0; j<13; ++j){
                if(i==0) {
                    Card card = new Card(Suit.suit.SPADE, j+2, "PlayingCards/"+(j+2)+"_of_spades.png");
                    deck.add(card);
                } else if(i==1){
                    Card card = new Card(Suit.suit.CLUB, j+2, "PlayingCards/"+(j+2)+"_of_clubs.png");
                    deck.add(card);
                } else if(i==2) {
                    Card card = new Card(Suit.suit.HEART, j+2, "PlayingCards/"+(j+2)+"_of_hearts.png");
                    deck.add(card);
                } else if(i==3){
                    Card card = new Card(Suit.suit.DIAMOND, j+2, "PlayingCards/"+(j+2)+"_of_diamonds.png");
                    deck.add(card);
                }
            }
        }

        return deck;
    }

    public static ArrayList<Card> createGameDeck(int noOfDeck){
        ArrayList<Card> gameDeck = new ArrayList<>();
        ArrayList<Card> deck;

        for(int i=0; i<noOfDeck; ++i){
            deck = generate();
            gameDeck.addAll(deck);
        }

        return gameDeck;
    }

    public static void shuffle(ArrayList<Card> gameDeck){
        long seed = System.nanoTime();
        Collections.shuffle(gameDeck, new Random(seed));
    }
}
