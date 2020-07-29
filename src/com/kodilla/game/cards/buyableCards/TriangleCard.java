package com.kodilla.game.cards.buyableCards;

import com.kodilla.game.cards.BuyAbleCard;

public class TriangleCard extends BuyAbleCard {

    public TriangleCard(String typeOfCard, String fieldName, int fieldCost) {
        super(typeOfCard, fieldName, fieldCost);
    }

    public int getOneTriangleFee() {
        return 5;
    }

    public int getTwoTrianglesFee() {
        return 10;
    }

    public String getBelongsTo() {
        return super.getBelongsTo();
    }
}
