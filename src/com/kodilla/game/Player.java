package com.kodilla.game;

import com.kodilla.game.board.Board;

import java.util.Random;

public class Player {
    private int fieldPositionNumber = 0;
    private int playerPositionX;
    private int playerPositionY;
    private int cash = 1000;
    private String playerColor;

    public Player(int playerPositionX, int playerPositionY, String playerColor) {
        this.playerPositionX = playerPositionX;
        this.playerPositionY = playerPositionY;
        this.playerColor = playerColor;
    }

    public int diceRoll(){

        Random rand = new Random();
        int dice1 = rand.nextInt(6)+1;
        int dice2 = rand.nextInt(6)+1;

        return dice1 + dice2;
    }

    public void movePlayer(){
        fieldPositionNumber = diceRoll();
        playerPositionX = Board.fieldsArray.get(fieldPositionNumber).getRedPlayerStopX();
        playerPositionY = Board.fieldsArray.get(fieldPositionNumber).getRedPlayerStopY();
    }

    public int getFieldPositionNumber() {
        return fieldPositionNumber;
    }

    public int getPlayerPositionX() {
        return playerPositionX;
    }

    public int getPlayerPositionY() {
        return playerPositionY;
    }

    public String getPlayerColor() {
        return playerColor;
    }
}
