package com.example.game;

/**
 * Created by jakeparham on 4/8/16.
 */
class Card {
    Suit.suit suit;
    int rank;
    String img;
    int aceVal;

    public Card(Suit.suit suit, int rank, String img){
        this.suit = suit;
        this.rank = rank;
        this.img = img;
    }

    public void setAceVal(int aceVal){
        this.aceVal = aceVal;
    }

    @Override
    public String toString() {
        if(this.rank == 11){
            return ""+this.suit+"|Jack";
        } else if(this.rank == 12){
            return ""+this.suit+"|Queen";
        } else if(this.rank == 13){
            return ""+this.suit+"|King";
        } else if(this.rank == 14){
            return ""+this.suit+"|Ace";
        } else {
            return this.suit + "|" + this.rank;
        }
    }
}
