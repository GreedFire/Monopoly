package com.kodilla.game.cards;

public class CityCard extends Card {
    private final String fieldName;
    private final int group;
    private final int neededCardsFromGroupToBuild;

    private String cardColor;
    private final int fieldCost;
    private final int buildCost;

    private final int zeroBuildingsFee;
    private final int oneBuildingsFee;
    private final int twoBuildingsFee;
    private final int threeBuildingsFee;
    private final int fourBuildingsFee;
    private final int fiveBuildingsFee;

    //Zastaw
    //belongsindicator X + Y
    //building X + Y

    private int numberOfBuildings = 0;
    private String belongsTo = "Nobody";

    public CityCard(String typeOfCard, String fieldName ,int group, int neededCardsFromGroupToBuild, int fieldCost, int zeroBuildingsFee, int oneBuildingsFee, int twoBuildingsFee,
                    int threeBuildingsFee, int fourBuildingsFee, int fiveBuildingsFee, int buildCost, String cardColor) {
        super(typeOfCard);
        this.fieldName = fieldName;
        this.group = group;
        this.neededCardsFromGroupToBuild = neededCardsFromGroupToBuild;
        this.fieldCost = fieldCost;
        this.zeroBuildingsFee = zeroBuildingsFee;
        this.oneBuildingsFee = oneBuildingsFee;
        this.twoBuildingsFee = twoBuildingsFee;
        this.threeBuildingsFee = threeBuildingsFee;
        this.fourBuildingsFee = fourBuildingsFee;
        this.fiveBuildingsFee = fiveBuildingsFee;
        this.buildCost = buildCost;
        this.cardColor = cardColor;
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getFieldCost() {
        return fieldCost;
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
}
