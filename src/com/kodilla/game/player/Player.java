package com.kodilla.game.player;

import com.kodilla.game.board.Board;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Player {
    private int playerPositionNumber = 0;
    private int playerPositionX;
    private int playerPositionY;
    private int cash = 5000;
    private String playerColor;
    private Circle pawn;
    private Circle pawnAfterImage;
    private boolean isPlayerTurn = false;

    Player(int playerPositionX, int playerPositionY, String playerColor) {
        this.playerPositionX = playerPositionX;
        this.playerPositionY = playerPositionY;
        this.playerColor = playerColor;

        pawn = new Circle(getPlayerPositionX(), getPlayerPositionY(), 10);
        pawn.setStroke(Color.BLACK);
        pawn.setDisable(true);

        pawnAfterImage = new Circle(getPlayerPositionX(), getPlayerPositionY(), 10);
        pawnAfterImage.setStroke(Color.BLACK);
        pawnAfterImage.setOpacity(0.35);
        pawnAfterImage.setDisable(true);

        if("red".equals(playerColor)) {
            pawn.setFill(Color.RED);
            pawnAfterImage.setFill(Color.RED);
        }

        else if("blue".equals(playerColor)) {
            pawn.setFill(Color.BLUE);
            pawnAfterImage.setFill(Color.BLUE);
        }
    }

    public abstract void checkAndDoActions(Board board);

    public void movePlayer(int dicesRoll, Board board){
        // Getting X and Y where player can stop his pawn afterimage from board
        setPlayersPositions(playerPositionNumber, board);

        // Moving pawn afterimage
        pawnAfterImage.setCenterX(playerPositionX);
        pawnAfterImage.setCenterY(playerPositionY);

        if(playerPositionNumber < 40) {
            playerPositionNumber += dicesRoll;
            if(playerPositionNumber >= 40) {
                addCash(200);
                playerPositionNumber -= 40;
            }
        }

        // Getting X and Y where player can stop his pawn afterimage from board
        setPlayersPositions(playerPositionNumber, board);

        // Moving pawn of player
        pawn.setCenterX(getPlayerPositionX());
        pawn.setCenterY(getPlayerPositionY());

        board.putInfoToProcess("+ #" + getPlayerColor() + " moved to field #" + getPlayerPositionNumber());

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

    public abstract void purchaseCard(Board board);

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

    public int getPlayerPositionNumber() {
        return playerPositionNumber;
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

    public void setPlayerPositionNumber(int fieldPositionNumber) {
        this.playerPositionNumber = fieldPositionNumber;
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }
}
