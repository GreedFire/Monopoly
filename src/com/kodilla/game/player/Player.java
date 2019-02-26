package com.kodilla.game.player;

import com.kodilla.game.board.Board;
import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.BuyableCard;
import com.kodilla.game.cards.buyableCards.CityCard;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.util.Map;

public abstract class Player {

    public static final String RED = "red";
    public static final String BLUE = "blue";
    public static final String GREEN = "green";
    public static final String YELLOW = "yellow";

    private int playerPositionNumber = 0;
    private int playerPositionX;
    private int playerPositionY;
    private int cash = 1500;
    private String playerColor;
    private Circle pawn;
    private Circle pawnAfterImage;
    private boolean inPrison = false;
    private int inPrisonTurnCounter = 3;
    private boolean defeated = false;
    private boolean yourTurn = false;

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

        if(RED.equals(playerColor)) {
            pawn.setFill(Color.RED);
            pawnAfterImage.setFill(Color.RED);
        }

        else if(BLUE.equals(playerColor)) {
            pawn.setFill(Color.BLUE);
            pawnAfterImage.setFill(Color.BLUE);
        }
        else if(GREEN.equals(playerColor)) {
            pawn.setFill(Color.GREEN);
            pawnAfterImage.setFill(Color.GREEN);
        }
        else if(YELLOW.equals(playerColor)) {
            pawn.setFill(Color.YELLOW);
            pawnAfterImage.setFill(Color.YELLOW);
        }
    }



    protected void buyBulding(CityCard cityCard, Board board){
            boolean canBuild = checkIfPlayerCanBuildOnField(board, cityCard);
            if(canBuild && cityCard.getNumberOfBuildings() < 5 && getCash() >= cityCard.getBuildCost() && !cityCard.isOnPledge()) {
                substractCash(cityCard.getBuildCost());
                cityCard.setbuildingsPlusOne();
                setImageOfBuildings(board, cityCard, cityCard.getNumberOfBuildings());
                cityCard.getPledgeAndBuildingsIndicator().setVisible(true);
                board.getTable().putInfoToProcess("+ #" + getPlayerColor() + " bought a building on " + cityCard.getFieldName() + " for " + cityCard.getBuildCost() + "$");
                updateCashLabels(board);
            }
    }

    public abstract void checkAndDoActions(Board board);

    public boolean isYourTurn() {
        return yourTurn;
    }

    public void setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
    }

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

        board.getTable().putInfoToProcess("+ #" + getPlayerColor() + " moved to field #" + getPlayerPositionNumber());

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

        board.getTable().putInfoToProcess("+ #" + getPlayerColor() + " moved to field #" + getPlayerPositionNumber());
    }

    public void setPlayersPositions(int fieldPositionNumber, Board board){
        if(RED.equals(playerColor)) {
            playerPositionX = board.getFieldsArray().get(fieldPositionNumber).getRedPlayerStopX();
            playerPositionY = board.getFieldsArray().get(fieldPositionNumber).getRedPlayerStopY();
        }
        else if(BLUE.equals(playerColor)){
            playerPositionX = board.getFieldsArray().get(fieldPositionNumber).getBluePlayerStopX();
            playerPositionY = board.getFieldsArray().get(fieldPositionNumber).getBluePlayerStopY();
        }
        else if(GREEN.equals(playerColor)){
            playerPositionX = board.getFieldsArray().get(fieldPositionNumber).getGreenPlayerStopX();
            playerPositionY = board.getFieldsArray().get(fieldPositionNumber).getGreenPlayerStopY();
        }
        else if(YELLOW.equals(playerColor)){
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

    protected void doPledge(Board board, BuyableCard buyableCard) {
        buyableCard.setPledgeAndBuildingsIndicator(board.getBelongsIndicators().getPledgeImage());
        buyableCard.getPledgeAndBuildingsIndicator().setVisible(true);
        addCash(buyableCard.getFieldCost());
        board.getTable().putInfoToProcess("+ #" + getPlayerColor() + " pledged " + buyableCard.getFieldName() + " for " + buyableCard.getFieldCost() + "$");
        buyableCard.setOnPledge(true);

        updateCashLabels(board);
    }

    public static String getRED() {
        return RED;
    }

    public static String getBLUE() {
        return BLUE;
    }

    public static String getGREEN() {
        return GREEN;
    }

    public static String getYELLOW() {
        return YELLOW;
    }

    protected void updateCashLabels(Board board) {
        switch (getPlayerColor()) {
            case RED:
                board.getCashLabels().setPlayerRedLabel(getCash());
                break;
            case BLUE:
                board.getCashLabels().setPlayerBlueLabel(getCash());
                break;
            case GREEN:
                board.getCashLabels().setPlayerGreenLabel(getCash());
                break;
            case YELLOW:
                board.getCashLabels().setPlayerYellowLabel(getCash());
                break;
        }
    }

    protected void purchaseFromPledge(Board board, BuyableCard buyableCard) {
        if (buyableCard.isOnPledge() && buyableCard.getBelongsTo().equals(getPlayerColor()) && (getCash() >= buyableCard.getFieldCost()) ) {
            buyableCard.getPledgeAndBuildingsIndicator().setVisible(false);
            substractCash(buyableCard.getFieldCost());
            board.getTable().putInfoToProcess("+ #" + getPlayerColor() + " pucharsed from pledge " + buyableCard.getFieldName() + " for " + buyableCard.getFieldCost() + "$");
            buyableCard.setOnPledge(false);

            updateCashLabels(board);
        }

    }

    protected boolean checkIfPlayerCanBuildOnField(Board board, CityCard givenCard){

        boolean result = false;
        ArrayList<CityCard> listOfCityCards = new ArrayList<>();
        CityCard cityCard;

        for (Map.Entry<Integer, BoardField> entry : board.getFieldsArray().entrySet()){
            if(entry.getValue().getCard() instanceof CityCard) {
                cityCard = (CityCard) entry.getValue().getCard();
                if(cityCard.getNumberOfCountry() == givenCard.getNumberOfCountry() && cityCard.getBelongsTo().equals(getPlayerColor()))
                    listOfCityCards.add(cityCard);
            }

        }

        int numberOfSameCards = listOfCityCards.size();

        if(givenCard.getNumberOfCountry() == 1 && numberOfSameCards == 2)
            result = true;
        else if(givenCard.getNumberOfCountry() == 2 && numberOfSameCards == 3)
            result = true;
        else if(givenCard.getNumberOfCountry() == 3 && numberOfSameCards == 3)
            result = true;
        else if(givenCard.getNumberOfCountry() == 4 && numberOfSameCards == 3)
            result = true;
        else if(givenCard.getNumberOfCountry() == 5 && numberOfSameCards == 3)
            result = true;
        else if(givenCard.getNumberOfCountry() == 6 && numberOfSameCards == 3)
            result = true;
        else if(givenCard.getNumberOfCountry() == 7 && numberOfSameCards == 3)
            result = true;
        else if(givenCard.getNumberOfCountry() == 8 && numberOfSameCards == 2)
            result = true;

        return result;
    }

    protected void setImageOfBuildings(Board board, CityCard givenCard, int numberOfBuildings){
        switch(numberOfBuildings){
            case 0: givenCard.setPledgeAndBuildingsIndicator(board.getBelongsIndicators().getZeroBuildingsImage()); break;
            case 1: givenCard.setPledgeAndBuildingsIndicator(board.getBelongsIndicators().getOneBuildingImage()); break;
            case 2: givenCard.setPledgeAndBuildingsIndicator(board.getBelongsIndicators().getTwoBuildingsImage()); break;
            case 3: givenCard.setPledgeAndBuildingsIndicator(board.getBelongsIndicators().getThreeBuildingsImage()); break;
            case 4: givenCard.setPledgeAndBuildingsIndicator(board.getBelongsIndicators().getFourBuildingsImage()); break;
            case 5: givenCard.setPledgeAndBuildingsIndicator(board.getBelongsIndicators().getFiveBuildingsImage()); break;
        }
    }

    protected void sellBuilding(CityCard cityCard, Board board){
        addCash(cityCard.getBuildCost());
        cityCard.setbuildingsMinusOne();
        setImageOfBuildings(board, cityCard, cityCard.getNumberOfBuildings());
        cityCard.getPledgeAndBuildingsIndicator().setVisible(true);
        board.getTable().putInfoToProcess("+ #" + getPlayerColor() + " sold a building on " + cityCard.getFieldName() + " for " + cityCard.getBuildCost() + "$");
        updateCashLabels(board);
    }

    public void checkAndSetPrison(){
        inPrisonTurnCounter--;
        if(inPrisonTurnCounter == 0){
            inPrison = false;
            inPrisonTurnCounter = 3;
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

    public void setCash(int cash) {
        this.cash = cash;
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
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

}
