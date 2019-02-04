package com.kodilla.game.player;

import com.kodilla.game.board.Board;
import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.BuyableCard;
import com.kodilla.game.cards.Card;
import com.kodilla.game.cards.buyableCards.CityCard;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Map;

public class Human extends Player {

    public Human(int playerPositionX, int playerPositionY, String playerColor) {
        super(playerPositionX, playerPositionY, playerColor);
    }

    public void purchaseCard(Board board) {
        if (board.getFieldsArray().get(getPlayerPositionNumber()).getCard() instanceof BuyableCard) {
            Card givenCard = board.getFieldsArray().get(getPlayerPositionNumber()).getCard();
            BuyableCard temporaryCityCard;
            if (givenCard instanceof BuyableCard) {
                temporaryCityCard = (BuyableCard) givenCard;
                BuyableCard purchasableCard = temporaryCityCard;


                if (purchasableCard.getBelongsTo().equals("nobody")) {
                    board.getBuyCardContentLayout().setVisible(true);
                    board.getEndTurnBtn().setDisable(true);
                }

                if (getCash() < purchasableCard.getFieldCost())
                    board.getBuyCardYesButton().setDisable(true);
                else {
                    board.getBuyCardYesButton().setDisable(false);
                }

                board.getBuyCardYesButton().setOnMouseClicked(e -> {
                    purchasableCard.setBelongsTo(getPlayerColor());
                    board.putInfoToProcess("+ #" + getPlayerColor() + " bought the " + purchasableCard.getFieldName());
                    substractCash(purchasableCard.getFieldCost());
                    board.getBuyCardContentLayout().setVisible(false);
                    purchasableCard.getBelongsIndicator().setVisible(true);
                    purchasableCard.setBelongsIndicatorColor();
                    board.getEndTurnBtn().setDisable(false);
                    if (getPlayerColor().equals("red"))
                        board.setPlayerRedLabel(getCash());
                    else if (getPlayerColor().equals("blue"))
                        board.setPlayerBlueLabel(getCash());

                });
                board.getBuyCardNoButton().setOnMouseClicked(e -> {
                    board.getBuyCardContentLayout().setVisible(false);
                    board.putInfoToProcess("+ #" + getPlayerColor() + " din't buy the field");
                    board.getEndTurnBtn().setDisable(false);
                });

            }
        }
    }

    public void checkAndDoActions(Board board) {

        for (Map.Entry<Integer, BoardField> entry : board.getFieldsArray().entrySet()) {
            if (entry.getValue().getCard() instanceof BuyableCard) {
                entry.getValue().getRectangle().setOnMouseClicked(x -> {

                    BuyableCard buyableCard = (BuyableCard) entry.getValue().getCard();
                    CityCard cityCard = null;

                    // GIVE AWAY TO PLEDGE
                    if (board.getActionButton1().getFill().equals(Color.YELLOW)) {
                        if (buyableCard instanceof CityCard) {
                            cityCard = (CityCard) buyableCard;

                            if (!buyableCard.isOnPledge() && buyableCard.getBelongsTo().equals(getPlayerColor()) && cityCard.getNumberOfBuildings() == 0)
                                doPledge(board, buyableCard);

                        } else if (!buyableCard.isOnPledge() && buyableCard.getBelongsTo().equals(getPlayerColor()))
                            doPledge(board, buyableCard);
                    // PURCHASE FROM PLEDGE
                    }
                    else if (board.getActionButton2().getFill().equals(Color.YELLOW)) {
                        purchaseFromPledge(board, buyableCard);
                    }
                    // BUY A BUILDING
                    else if(board.getActionButton3().getFill().equals(Color.YELLOW)){
                        if(buyableCard instanceof CityCard){
                            cityCard = (CityCard) buyableCard;
                            boolean canBuild = checkIfPlayerCanBuildOnField(board, cityCard);
                            if(canBuild && cityCard.getNumberOfBuildings() < 5 && getCash() >= cityCard.getBuildCost() && !cityCard.isOnPledge()){
                                    substractCash(cityCard.getBuildCost());
                                    cityCard.setbuildingsPlusOne();
                                    setImageOfBuildings(board, cityCard, cityCard.getNumberOfBuildings());
                                    cityCard.getPledgeAndBuildingsIndicator().setVisible(true);
                                    board.putInfoToProcess("+ #" + getPlayerColor() + " bought a building on " + buyableCard.getFieldName() + " for " + cityCard.getBuildCost() + "$");
                                    updateCashLabels(board);

                            }


                        }
                    }
                    else if(board.getActionButton4().getFill().equals(Color.YELLOW)){
                        if(buyableCard instanceof CityCard){
                            cityCard = (CityCard) buyableCard;

                            if(cityCard.getNumberOfBuildings() > 0 && cityCard.getBelongsTo().equals(getPlayerColor())){
                                addCash(cityCard.getBuildCost());
                                cityCard.setbuildingsMinusOne();
                                setImageOfBuildings(board, cityCard, cityCard.getNumberOfBuildings());
                                cityCard.getPledgeAndBuildingsIndicator().setVisible(true);
                                board.putInfoToProcess("+ #" + getPlayerColor() + " sold a building on " + buyableCard.getFieldName() + " for " + cityCard.getBuildCost() + "$");
                                updateCashLabels(board);
                            }

                        }
                    }


                });
            }
        }
    }

    private void setImageOfBuildings(Board board, CityCard givenCard, int numberOfBuildings){
        switch(numberOfBuildings){
            case 0: givenCard.setPledgeAndBuildingsIndicator(board.getZeroBuildingsImage()); break;
            case 1: givenCard.setPledgeAndBuildingsIndicator(board.getOneBuildingImage()); break;
            case 2: givenCard.setPledgeAndBuildingsIndicator(board.getTwoBuildingsImage()); break;
            case 3: givenCard.setPledgeAndBuildingsIndicator(board.getThreeBuildingsImage()); break;
            case 4: givenCard.setPledgeAndBuildingsIndicator(board.getFourBuildingsImage()); break;
            case 5: givenCard.setPledgeAndBuildingsIndicator(board.getFiveBuildingsImage()); break;
        }
    }

    private boolean checkIfPlayerCanBuildOnField(Board board, CityCard givenCard){

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


    private void doPledge(Board board, BuyableCard buyableCard) {
        buyableCard.setPledgeAndBuildingsIndicator(board.getPledgeImage());
        buyableCard.getPledgeAndBuildingsIndicator().setVisible(true);
        addCash(buyableCard.getFieldCost());
        board.putInfoToProcess("+ #" + getPlayerColor() + " pledged " + buyableCard.getFieldName() + " for " + buyableCard.getFieldCost() + "$");
        buyableCard.setOnPledge(true);

        updateCashLabels(board);
    }

    private void purchaseFromPledge(Board board, BuyableCard buyableCard) {
        if (buyableCard.isOnPledge() && buyableCard.getBelongsTo().equals(getPlayerColor()) && (getCash() >= buyableCard.getFieldCost()) ) {
            buyableCard.getPledgeAndBuildingsIndicator().setVisible(false);
            substractCash(buyableCard.getFieldCost());
            board.putInfoToProcess("+ #" + getPlayerColor() + " pucharsed from pledge " + buyableCard.getFieldName() + " for " + buyableCard.getFieldCost() + "$");
            buyableCard.setOnPledge(false);

            updateCashLabels(board);
        }

    }

    private void updateCashLabels(Board board) {
        switch (getPlayerColor()) {
            case "red":
                board.setPlayerRedLabel(getCash());
                break;
            case "blue":
                board.setPlayerBlueLabel(getCash());
                break;
        }
    }

}
