package com.kodilla.game.cards.buyableCards;

import com.kodilla.game.cards.BuyableCard;

public class CircleCard extends BuyableCard {

    private int oneCircleFee = 25;
    private int twoCirclesFee = 50;
    private int threeCirclesFee = 100;
    private int fourCirclesFee = 200;

    public CircleCard(String typeOfCard, String fieldName, int fieldCost) {
        super(typeOfCard, fieldName, fieldCost);
    }

    public int getOneCircleFee() {
        return oneCircleFee;
    }

    public int getTwoCirclesFee() {
        return twoCirclesFee;
    }

    public int getThreeCirclesFee() {
        return threeCirclesFee;
    }

    public int getFourCirclesFee() {
        return fourCirclesFee;
    }

    public String getBelongsTo() {
        return super.getBelongsTo();
    }
}
