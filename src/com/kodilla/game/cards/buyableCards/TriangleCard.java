package com.kodilla.game.cards.buyableCards;

import com.kodilla.game.cards.BuyableCard;

public class TriangleCard extends BuyableCard {

    private int oneTriangleFee = 5;
    private int twoTrianglesFee = 10;

    public TriangleCard(String typeOfCard, String fieldName, int fieldCost) {
        super(typeOfCard, fieldName, fieldCost);
    }

    public int getOneTriangleFee() {
        return oneTriangleFee;
    }

    public int getTwoTrianglesFee() {
        return twoTrianglesFee;
    }

    public String getBelongsTo() {
        return super.getBelongsTo();
    }
}
