package com.kodilla.game.player;

import com.kodilla.game.board.Board;
import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.BuyAbleCard;
import com.kodilla.game.cards.Card;
import com.kodilla.game.cards.buyableCards.CityCard;
import javafx.scene.paint.Color;
import java.util.Map;

public class Human extends Player {

    public Human(int playerPositionX, int playerPositionY, String playerColor) {
        super(playerPositionX, playerPositionY, playerColor);
    }

    public void purchaseCard(Board board) {
        if (board.getFieldsArray().get(getPlayerPositionNumber()).getCard() instanceof BuyAbleCard) {
            Card givenCard = board.getFieldsArray().get(getPlayerPositionNumber()).getCard();
            BuyAbleCard temporaryCityCard;
            if (givenCard instanceof BuyAbleCard) {
                temporaryCityCard = (BuyAbleCard) givenCard;
                BuyAbleCard purchasableCard = temporaryCityCard;

                if (purchasableCard.getBelongsTo().equals("nobody")) {
                    board.getBuyCardLayout().getBuyCardContentLayout().setVisible(true);
                    board.getDices().getEndTurnBtn().setDisable(true);
                }

                board.getBuyCardLayout().getBuyCardYesButton().setDisable(getCash() < purchasableCard.getFieldCost());

                board.getBuyCardLayout().getBuyCardYesButton().setOnMouseClicked(e -> {
                    purchasableCard.setBelongsTo(getPlayerColor());
                    board.getTable().putInfoToProcess("+ #" + getPlayerColor() + " bought the " + purchasableCard.getFieldName());
                    subtractCash(purchasableCard.getFieldCost());
                    board.getBuyCardLayout().getBuyCardContentLayout().setVisible(false);
                    purchasableCard.getBelongsIndicator().setVisible(true);
                    purchasableCard.setBelongsIndicatorColor();
                    board.getDices().getEndTurnBtn().setDisable(false);
                    updateCashLabels(board);

                });
                board.getBuyCardLayout().getBuyCardNoButton().setOnMouseClicked(e -> {
                    board.getBuyCardLayout().getBuyCardContentLayout().setVisible(false);
                    board.getTable().putInfoToProcess("+ #" + getPlayerColor() + " didn't buy the field");
                    board.getDices().getEndTurnBtn().setDisable(false);
                });

            }
        }
    }

    public void checkAndDoActions(Board board) {


        for (Map.Entry<Integer, BoardField> entry : board.getFieldsArray().entrySet()) {
            if (entry.getValue().getCard() instanceof BuyAbleCard) {
                entry.getValue().getRectangle().setOnMouseClicked(x -> {

                    BuyAbleCard buyableCard = (BuyAbleCard) entry.getValue().getCard();
                    CityCard cityCard;

                    // GIVE AWAY TO PLEDGE
                    if (board.getTable().getActionButton1().getFill().equals(Color.YELLOW)) {
                        if (buyableCard instanceof CityCard) {
                            cityCard = (CityCard) buyableCard;

                            if (!buyableCard.isOnPledge() && buyableCard.getBelongsTo().equals(getPlayerColor()) && cityCard.getNumberOfBuildings() == 0 && isYourTurn())
                                doPledge(board, buyableCard);

                        } else if (!buyableCard.isOnPledge() && buyableCard.getBelongsTo().equals(getPlayerColor()) && isYourTurn())
                            doPledge(board, buyableCard);
                    // PURCHASE FROM PLEDGE
                    }
                    else if (board.getTable().getActionButton2().getFill().equals(Color.YELLOW ) && isYourTurn()) {
                        purchaseFromPledge(board, buyableCard);
                    }
                    // BUY A BUILDING
                    else if(board.getTable().getActionButton3().getFill().equals(Color.YELLOW) && isYourTurn()){
                        if(buyableCard instanceof CityCard){
                            cityCard = (CityCard) buyableCard;
                            buyBuilding(cityCard, board);
                        }
                    }
                    //SELL A BUILDING
                    else if(board.getTable().getActionButton4().getFill().equals(Color.YELLOW) && isYourTurn()){
                        if(buyableCard instanceof CityCard){
                            cityCard = (CityCard) buyableCard;

                            if(cityCard.getNumberOfBuildings() > 0 && cityCard.getBelongsTo().equals(getPlayerColor())){
                                sellBuilding(cityCard, board);
                            }

                        }
                    }
                });
            }
            //if(board.getTable().getMenuButton3().getFill().equals(Color.WHITE))
              //  break;
        }
    }












}
