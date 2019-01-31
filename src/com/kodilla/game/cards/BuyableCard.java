package com.kodilla.game.cards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BuyableCard extends Card {

    private final String fieldName;
    private final int fieldCost;
    private String belongsTo = "nobody";
    private Rectangle belongsIndicator;
    private Image pledge = new Image("file:resources/pledge.png");
    private ImageView pledgeAndBuildingsIndicator = new ImageView(pledge);

    private boolean onPledge = false;

    public BuyableCard(String typeOfCard, String fieldName, int fieldCost) {
        super(typeOfCard);
        this.fieldName = fieldName;
        this.fieldCost = fieldCost;
        belongsIndicator = new Rectangle(10,10, Color.GRAY);
        belongsIndicator.setStroke(Color.WHITE);
    }

    public String getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Rectangle getBelongsIndicator() {
        return belongsIndicator;
    }

    public int getFieldCost() {
        return fieldCost;
    }

    public void setBelongsIndicatorColor(){
        if("red".equals(belongsTo))
            belongsIndicator.setFill(Color.RED);
        else if("blue".equals(belongsTo))
            belongsIndicator.setFill(Color.BLUE);
    }

    public boolean isOnPledge() {
        return onPledge;
    }

    public void setOnPledge(boolean onPledge) {
        this.onPledge = onPledge;
    }

    public ImageView getPledgeAndBuildingsIndicator() {
        return pledgeAndBuildingsIndicator;
    }

    public void setPledgeAndBuildingsIndicator(ImageView pledgeAndBuildingsIndicator) {
        this.pledgeAndBuildingsIndicator = pledgeAndBuildingsIndicator;
    }
}
