package com.kodilla.game.player;

import com.kodilla.game.board.Board;
import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.BuyableCard;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Map;

public abstract class Player {
    private int playerPositionNumber = 0;
    private int playerPositionX;
    private int playerPositionY;
    private int cash = 15000;
    private String playerColor;
    private Circle pawn;
    private Circle pawnAfterImage;
    private boolean isPlayerTurn = false;
    private boolean inPrison = false;
    private int inPrisonTurnCounter = 3;
    private boolean defeated = false;

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
        else if("green".equals(playerColor)) {
            pawn.setFill(Color.GREEN);
            pawnAfterImage.setFill(Color.GREEN);
        }
        else if("yellow".equals(playerColor)) {
            pawn.setFill(Color.YELLOW);
            pawnAfterImage.setFill(Color.YELLOW);
        }
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    public void checkAndSetPrison(){
        inPrisonTurnCounter--;
        if(inPrisonTurnCounter == 0){
            inPrison = false;
            inPrisonTurnCounter = 3;
        }
    }

    public boolean isInPrison() {
        return inPrison;
    }

    public void setInPrison(boolean inPrison) {
        this.inPrison = inPrison;
    }

    public int getInPrisonTurnCounter() {
        return inPrisonTurnCounter;
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

    public void movePlayer(Board board){
        // Getting X and Y where player can stop his pawn afterimage from board
        setPlayersPositions(playerPositionNumber, board);

        // Moving pawn afterimage
        pawnAfterImage.setCenterX(playerPositionX);
        pawnAfterImage.setCenterY(playerPositionY);

        // Getting X and Y where player can stop his pawn afterimage from board
        setPlayersPositions(playerPositionNumber, board);

        // Moving pawn of player
        pawn.setCenterX(getPlayerPositionX());
        pawn.setCenterY(getPlayerPositionY());

        board.putInfoToProcess("+ #" + getPlayerColor() + " moved to field #" + getPlayerPositionNumber());
    }

    public void setPlayersPositions(int fieldPositionNumber, Board board){
        if("red".equals(playerColor)) {
            playerPositionX = board.getFieldsArray().get(fieldPositionNumber).getRedPlayerStopX();
            playerPositionY = board.getFieldsArray().get(fieldPositionNumber).getRedPlayerStopY();
        }
        else if("blue".equals(playerColor)){
            playerPositionX = board.getFieldsArray().get(fieldPositionNumber).getBluePlayerStopX();
            playerPositionY = board.getFieldsArray().get(fieldPositionNumber).getBluePlayerStopY();
        }
        else if("green".equals(playerColor)){
            playerPositionX = board.getFieldsArray().get(fieldPositionNumber).getGreenPlayerStopX();
            playerPositionY = board.getFieldsArray().get(fieldPositionNumber).getGreenPlayerStopY();
        }
        else if("yellow".equals(playerColor)){
            playerPositionX = board.getFieldsArray().get(fieldPositionNumber).getYellowPlayerStopX();
            playerPositionY = board.getFieldsArray().get(fieldPositionNumber).getYellowPlayerStopY();
        }
    }

    public void giveMeAllFields(Board board, Player player) {
        for (Map.Entry<Integer, BoardField> entry : board.getFieldsArray().entrySet()) {
            if (board.getFieldsArray().get(entry.getKey()).getCard() instanceof BuyableCard) {
                BuyableCard givenCard = (BuyableCard) board.getFieldsArray().get(entry.getKey()).getCard();

                givenCard.setBelongsTo(player.playerColor);
                givenCard.getBelongsIndicator().setVisible(true);
                givenCard.setBelongsIndicatorColor();

            }
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
