package com.kodilla.game.cards.unBuyableCards;

import com.kodilla.game.cards.Card;

import java.util.Random;

public class TaxCard extends Card {

    public TaxCard(String typeOfCard) {
        super(typeOfCard);
    }

    public int pickAndPayTax(){
        int result = 0;

        Random randGen = new Random();
        int picker = randGen.nextInt(4);

        switch(picker){
            case 0: result = 75; break;
            case 1: result = 100; break;
            case 2: result = 150; break;
            case 3: result = 200; break;
        }

        return result;
    }

}
