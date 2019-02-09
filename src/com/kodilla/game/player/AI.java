package com.kodilla.game.player;

import com.kodilla.game.board.Board;
import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.BuyableCard;
import com.kodilla.game.cards.Card;
import com.kodilla.game.cards.buyableCards.CityCard;

import java.util.Map;

public class AI extends Player {

    public AI(int playerPositionX, int playerPositionY, String playerColor) {
        super(playerPositionX, playerPositionY, playerColor);
    }

    public void pledgeOrSell(Board board, int cashToPay) {
        for (Map.Entry<Integer, BoardField> entry : board.getFieldsArray().entrySet()) {
            if (entry.getValue().getCard() instanceof BuyableCard) {
                BuyableCard buyableCard = (BuyableCard) entry.getValue().getCard();
                CityCard cityCard;

                // PLEDGE
                if (buyableCard instanceof CityCard) {
                    cityCard = (CityCard) buyableCard;
                    if (!buyableCard.isOnPledge() && buyableCard.getBelongsTo().equals(getPlayerColor()) && cityCard.getNumberOfBuildings() == 0 && cashToPay > getCash())
                        doPledge(board, buyableCard);
                }
                 else if (!buyableCard.isOnPledge() && buyableCard.getBelongsTo().equals(getPlayerColor()) && cashToPay > getCash())
                doPledge(board, buyableCard);

                 // SELL BUILDINGS
                if(buyableCard instanceof CityCard) {
                    cityCard = (CityCard) buyableCard;

                        if(cityCard.getNumberOfBuildings() > 0 && cityCard.getBelongsTo().equals(getPlayerColor()) && cashToPay > getCash())
                            sellBuilding(cityCard, board);

                }
            }
        }
    }



    public void checkAndDoActions(Board board){
        for (Map.Entry<Integer, BoardField> entry : board.getFieldsArray().entrySet()) {
            if (entry.getValue().getCard() instanceof BuyableCard) {
                BuyableCard buyableCard = (BuyableCard) entry.getValue().getCard();
                CityCard cityCard;

                //Buy buildings
                if(buyableCard instanceof CityCard) {
                    cityCard = (CityCard) buyableCard;
                    buyBulding(cityCard, board);
                }

                //Buy from pledge
                if(buyableCard.isOnPledge() && buyableCard.getFieldCost() < getCash()) {
                    purchaseFromPledge(board, buyableCard);
                }
            }
        }
    }

    public void purchaseCard(Board board) {
        if (board.getFieldsArray().get(getPlayerPositionNumber()).getCard() instanceof BuyableCard) {
            Card givenCard = board.getFieldsArray().get(getPlayerPositionNumber()).getCard();
            BuyableCard temporaryCityCard;
            if (givenCard instanceof BuyableCard) {
                temporaryCityCard = (BuyableCard) givenCard;
                BuyableCard purchasableCard = temporaryCityCard;


                if (purchasableCard.getBelongsTo().equals("nobody")) {
                    if (getCash() >= purchasableCard.getFieldCost()) {
                        purchasableCard.setBelongsTo(getPlayerColor());
                        substractCash(purchasableCard.getFieldCost());
                        purchasableCard.setBelongsIndicatorColor();
                        purchasableCard.getBelongsIndicator().setVisible(true);
                        board.putInfoToProcess("+ #" + getPlayerColor() + " bought the " + purchasableCard.getFieldName());
                        updateCashLabels(board);
                    } else {
                        board.putInfoToProcess("+ #" + getPlayerColor() + " din't buy the field");
                    }
                }

            }
        }
    }
}
