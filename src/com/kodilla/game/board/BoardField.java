package com.kodilla.game.board;

import com.kodilla.game.cards.Card;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardField {

    private String typeOfField = "Nothing";

    private Card card;
    private int redPlayerStopX;
    private int redPlayerStopY;
    private int bluePlayerStopX;
    private int bluePlayerStopY;
    private int greenPlayerStopX;
    private int greenPlayerStopY;
    private int yellowPlayerStopX;
    private int yellowPlayerStopY;

    private Rectangle rectangle;

    BoardField(String typeOfField, int redPlayerStopX, int redPlayerStopY, int bluePlayerStopX, int bluePlayerStopY, int greenPlayerStopX, int greenPlayerStopY,int yellowPlayerStopX,int yellowPlayerStopY, int recX, int recY, Card card) {
        this.typeOfField = typeOfField;
        this.redPlayerStopX = redPlayerStopX;
        this.redPlayerStopY = redPlayerStopY;
        this.bluePlayerStopX = bluePlayerStopX;
        this.bluePlayerStopY = bluePlayerStopY;
        this.greenPlayerStopX = greenPlayerStopX;
        this.greenPlayerStopY = greenPlayerStopY;
        this.yellowPlayerStopX = yellowPlayerStopX;
        this.yellowPlayerStopY = yellowPlayerStopY;
        this.rectangle = new Rectangle(recX, recY, Color.TRANSPARENT);
        this.card = card;

    }

    BoardField(String typeOfField, int redPlayerStopX, int redPlayerStopY, int bluePlayerStopX, int bluePlayerStopY, int greenPlayerStopX, int greenPlayerStopY,int yellowPlayerStopX,int yellowPlayerStopY, int recX, int recY) {
        this.typeOfField = typeOfField;
        this.redPlayerStopX = redPlayerStopX;
        this.redPlayerStopY = redPlayerStopY;
        this.bluePlayerStopX = bluePlayerStopX;
        this.bluePlayerStopY = bluePlayerStopY;
        this.greenPlayerStopX = greenPlayerStopX;
        this.greenPlayerStopY = greenPlayerStopY;
        this.yellowPlayerStopX = yellowPlayerStopX;
        this.yellowPlayerStopY = yellowPlayerStopY;
        this.rectangle = new Rectangle(recX, recY, Color.TRANSPARENT);
    }


    public int getRedPlayerStopX() {
        return redPlayerStopX;
    }

    public int getRedPlayerStopY() {
        return redPlayerStopY;
    }

    public int getBluePlayerStopX() {
        return bluePlayerStopX;
    }

    public int getBluePlayerStopY() {
        return bluePlayerStopY;
    }

    public int getGreenPlayerStopX() {
        return greenPlayerStopX;
    }

    public int getGreenPlayerStopY() {
        return greenPlayerStopY;
    }

    public int getYellowPlayerStopX() {
        return yellowPlayerStopX;
    }

    public int getYellowPlayerStopY() {
        return yellowPlayerStopY;
    }

    public Card getCard() {
        return card;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public String getTypeOfField() {
        return typeOfField;
    }
}
