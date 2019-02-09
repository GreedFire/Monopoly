package com.kodilla.game.player;

import com.kodilla.game.board.Board;
import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.BuyableCard;
import com.kodilla.game.cards.Card;
import com.kodilla.game.cards.buyableCards.CityCard;
import javafx.scene.paint.Color;
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
                    updateCashLabels(board);

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
                    CityCard cityCard;

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
                            buyBulding(cityCard, board);
                        }
                    }
                    //SELL A BUILDING
                    else if(board.getActionButton4().getFill().equals(Color.YELLOW)){
                        if(buyableCard instanceof CityCard){
                            cityCard = (CityCard) buyableCard;

                            if(cityCard.getNumberOfBuildings() > 0 && cityCard.getBelongsTo().equals(getPlayerColor())){
                                sellBuilding(cityCard, board);
                            }

                        }
                    }
                });
            }
        }
    }












}
