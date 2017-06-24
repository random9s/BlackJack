package com.example.game;

import java.util.ArrayList;

/**
 * Created by jakeparham on 4/9/16.
 */
class Player {
    String name;
    ArrayList<Card> hand;
    boolean stand;
    boolean bust;

    public Player(String name, ArrayList<Card> hand){
        this.name = name;
        this.hand = hand;
    }

    public void setStand(boolean stand){ this.stand = stand; }
    public void setBust(boolean bust){ this.bust = bust; }

    @Override
    public String toString(){
        return this.name+"s hand is: "+this.hand.get(0)+" & "+this.hand.get(1);
    }
}
