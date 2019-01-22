package com.kodilla.game.board;

import com.kodilla.game.cards.Card;
import com.kodilla.game.cards.CityCard;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardField {

    private String typeOfField = "Nothing";
    private Card card = null;

    private int redPlayerStopX;
    private int redPlayerStopY;
    private int bluePlayerStopX;
    private int bluePlayerStopY;

    private Rectangle rectangle;

    public BoardField(String typeOfField, int redPlayerStopX, int redPlayerStopY, int bluePlayerStopX, int bluePlayerStopY, int recX, int recY, Card card) {
        this.typeOfField = typeOfField;
        this.redPlayerStopX = redPlayerStopX;
        this.redPlayerStopY = redPlayerStopY;
        this.bluePlayerStopX = bluePlayerStopX;
        this.bluePlayerStopY = bluePlayerStopY;
        this.rectangle = new Rectangle(recX, recY, Color.TRANSPARENT);
        this.card = card;

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

    public BoardField(String typeOfField, int redPlayerStopX, int redPlayerStopY, int bluePlayerStopX, int bluePlayerStopY, int recX, int recY) {
        this.typeOfField = typeOfField;
        this.redPlayerStopX = redPlayerStopX;
        this.redPlayerStopY = redPlayerStopY;
        this.bluePlayerStopX = bluePlayerStopX;
        this.bluePlayerStopY = bluePlayerStopY;
        this.rectangle = new Rectangle(recX, recY, Color.TRANSPARENT);
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
