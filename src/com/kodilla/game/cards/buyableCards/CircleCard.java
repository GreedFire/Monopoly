package com.kodilla.game.cards.buyableCards;

import com.kodilla.game.cards.BuyAbleCard;

public class CircleCard extends BuyAbleCard {

    public CircleCard(String typeOfCard, String fieldName, int fieldCost) {
        super(typeOfCard, fieldName, fieldCost);
    }

    public int getOneCircleFee() {
        return 25;
    }

    public int getTwoCirclesFee() {
        return 50;
    }

    public int getThreeCirclesFee() {
        return 100;
    }

    public int getFourCirclesFee() {
        return 200;
    }

    public String getBelongsTo() {
        return super.getBelongsTo();
    }
}
