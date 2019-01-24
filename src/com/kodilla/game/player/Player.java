package com.kodilla.game.player;

import com.kodilla.game.board.Board;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private int fieldPositionNumber = 0;
    private int playerPositionX;
    private int playerPositionY;
    private int cash = 1000;
    private String playerColor;
    private Circle pawn;

    Player(int playerPositionX, int playerPositionY, String playerColor) {
        this.playerPositionX = playerPositionX;
        this.playerPositionY = playerPositionY;
        this.playerColor = playerColor;

        pawn = new Circle(getPlayerPositionX(), getPlayerPositionY(), 10);
        pawn.setStroke(Color.BLACK);
        if(playerColor.equals("red"))
            pawn.setFill(Color.RED);
        else if(playerColor.equals("blue"))
            pawn.setFill(Color.BLUE);
    }


    public void movePlayer(int dicesRoll, Board board){

        if(fieldPositionNumber < 40) {
            fieldPositionNumber += dicesRoll;
            if(fieldPositionNumber >= 40) {
                cash += 400;
                fieldPositionNumber -= 40;

            }
        }

        // Getting X and Y where player can stop his pawn from board
        playerPositionX = board.getFieldsArray().get(fieldPositionNumber).getRedPlayerStopX();
        playerPositionY = board.getFieldsArray().get(fieldPositionNumber).getRedPlayerStopY();

        // Moving pawn of player
        pawn.setCenterX(getPlayerPositionX());
        pawn.setCenterY(getPlayerPositionY());
    }


    private int getPlayerPositionX() {
        return playerPositionX;
    }

    private int getPlayerPositionY() {
        return playerPositionY;
    }

    public Circle getPawn() {
        return pawn;
    }

    public int getCash() {
        return cash;
    }
}
