package com.kodilla.game.player;

import com.kodilla.game.board.Board;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private int fieldPositionNumber = 0;
    private int playerPositionX;
    private int playerPositionY;
    private int cash = 1500;
    private String playerColor;
    private Circle pawn;
    private Circle pawnAfterImage;

    Player(int playerPositionX, int playerPositionY, String playerColor) {
        this.playerPositionX = playerPositionX;
        this.playerPositionY = playerPositionY;
        this.playerColor = playerColor;

        pawn = new Circle(getPlayerPositionX(), getPlayerPositionY(), 10);
        pawn.setStroke(Color.BLACK);

        pawnAfterImage = new Circle(getPlayerPositionX(), getPlayerPositionY(), 10);
        pawnAfterImage.setStroke(Color.BLACK);
        pawnAfterImage.setOpacity(0.35);

        if("red".equals(playerColor)) {
            pawn.setFill(Color.RED);
            pawnAfterImage.setFill(Color.RED);
        }

        else if("blue".equals(playerColor)) {
            pawn.setFill(Color.BLUE);
            pawnAfterImage.setFill(Color.BLUE);
        }
    }

    public void movePlayer(int dicesRoll, Board board){
        // Getting X and Y where player can stop his pawn afterimage from board
        setPlayersPositions(fieldPositionNumber, board);

        // Moving pawn afterimage
        pawnAfterImage.setCenterX(playerPositionX);
        pawnAfterImage.setCenterY(playerPositionY);

        if(fieldPositionNumber < 40) {
            fieldPositionNumber += dicesRoll;
            if(fieldPositionNumber >= 40) {
                addCash(200);
                fieldPositionNumber -= 40;
            }
        }

        // Getting X and Y where player can stop his pawn afterimage from board
        setPlayersPositions(fieldPositionNumber, board);

        // Moving pawn of player
        pawn.setCenterX(getPlayerPositionX());
        pawn.setCenterY(getPlayerPositionY());

        board.putInfoToProcess("+ #red moved to field #" + getFieldPositionNumber());

    }

    private void setPlayersPositions(int fieldPositionNumber, Board board){
        if("red".equals(playerColor)) {
            playerPositionX = board.getFieldsArray().get(fieldPositionNumber).getRedPlayerStopX();
            playerPositionY = board.getFieldsArray().get(fieldPositionNumber).getRedPlayerStopY();
        }
        else if("blue".equals(playerColor)){
            playerPositionX = board.getFieldsArray().get(fieldPositionNumber).getBluePlayerStopX();
            playerPositionY = board.getFieldsArray().get(fieldPositionNumber).getBluePlayerStopY();
        }
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

    public Circle getPawnAfterImage() {
        return pawnAfterImage;
    }

    public int getFieldPositionNumber() {
        return fieldPositionNumber;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public void substractCash(int number){
        cash -= number;
    }

    public void addCash(int number){
        cash += number;
    }
}
