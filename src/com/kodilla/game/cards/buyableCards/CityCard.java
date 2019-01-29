package com.kodilla.game.cards.buyableCards;

import com.kodilla.game.cards.BuyableCard;

public class CityCard extends BuyableCard {
    private final int group;
    private final int neededCardsFromGroupToBuild;

    private String cardColor;

    private final int buildCost;

    private final int zeroBuildingsFee;
    private final int oneBuildingsFee;
    private final int twoBuildingsFee;
    private final int threeBuildingsFee;
    private final int fourBuildingsFee;
    private final int fiveBuildingsFee;

    private int numberOfBuildings = 0;

    public CityCard(String typeOfCard, String fieldName ,int group, int neededCardsFromGroupToBuild, int fieldCost, int zeroBuildingsFee, int oneBuildingsFee, int twoBuildingsFee,
                    int threeBuildingsFee, int fourBuildingsFee, int fiveBuildingsFee, int buildCost, String cardColor) {
        super(typeOfCard, fieldName, fieldCost);
        this.group = group;
        this.neededCardsFromGroupToBuild = neededCardsFromGroupToBuild;
        this.zeroBuildingsFee = zeroBuildingsFee;
        this.oneBuildingsFee = oneBuildingsFee;
        this.twoBuildingsFee = twoBuildingsFee;
        this.threeBuildingsFee = threeBuildingsFee;
        this.fourBuildingsFee = fourBuildingsFee;
        this.fiveBuildingsFee = fiveBuildingsFee;
        this.buildCost = buildCost;
        this.cardColor = cardColor;
    }

    public int getFieldCost() {
        return super.getFieldCost();
    }

    public int getBuildCost() {
        return buildCost;
    }

    public int getZeroBuildingsFee() {
        return zeroBuildingsFee;
    }

    public int getOneBuildingsFee() {
        return oneBuildingsFee;
    }

    public int getTwoBuildingsFee() {
        return twoBuildingsFee;
    }

    public int getThreeBuildingsFee() {
        return threeBuildingsFee;
    }

    public int getFourBuildingsFee() {
        return fourBuildingsFee;
    }

    public int getFiveBuildingsFee() {
        return fiveBuildingsFee;
    }

    public String getCardColor() {
        return cardColor;
    }

    public String getFieldName(){
        return super.getFieldName();
    }
}
