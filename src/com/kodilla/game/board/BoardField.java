package com.kodilla.game.board;

import com.kodilla.game.cards.Card;

public class BoardField {

    private String typeOfField = "Nothing";
    private Card card = null;

    private int redPlayerStopX;
    private int redPlayerStopY;
    private int bluePlayerStopX;
    private int bluePlayerStopY;

    public BoardField(String typeOfField, int redPlayerStopX, int redPlayerStopY, int bluePlayerStopX, int bluePlayerStopY, Card card) {
        this.typeOfField = typeOfField;
        this.redPlayerStopX = redPlayerStopX;
        this.redPlayerStopY = redPlayerStopY;
        this.bluePlayerStopX = bluePlayerStopX;
        this.bluePlayerStopY = bluePlayerStopY;
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

    public BoardField(String typeOfField, int redPlayerStopX, int redPlayerStopY, int bluePlayerStopX, int bluePlayerStopY) {
        this.typeOfField = typeOfField;
        this.redPlayerStopX = redPlayerStopX;
        this.redPlayerStopY = redPlayerStopY;
        this.bluePlayerStopX = bluePlayerStopX;
        this.bluePlayerStopY = bluePlayerStopY;
    }
}
