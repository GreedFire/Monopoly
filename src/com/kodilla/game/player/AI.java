package com.kodilla.game.player;

import com.kodilla.game.board.Board;
import com.kodilla.game.cards.BuyableCard;
import com.kodilla.game.cards.Card;

public class AI extends Player {

    public AI(int playerPositionX, int playerPositionY, String playerColor) {
        super(playerPositionX, playerPositionY, playerColor);
    }

    public void giveAwayOnPledge(Board board){

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
                        if (getPlayerColor().equals("red"))
                            board.setPlayerRedLabel(getCash());
                        else if (getPlayerColor().equals("blue"))
                            board.setPlayerBlueLabel(getCash());
                    } else {
                        board.putInfoToProcess("+ #" + getPlayerColor() + " din't buy the field");
                    }
                }

            }
        }
    }
}
