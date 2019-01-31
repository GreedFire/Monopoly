package com.kodilla.game.player;

import com.kodilla.game.board.Board;
import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.BuyableCard;
import com.kodilla.game.cards.Card;
import com.kodilla.game.cards.buyableCards.CityCard;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Map;

public class Human extends Player {

    public Human(int playerPositionX, int playerPositionY, String playerColor){
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
                else{
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

    //POPRAW
    public void giveAwayOnPledge(Board board) {
        if (board.getActionButton1().getFill().equals(Color.YELLOW) && isPlayerTurn()) {
            for (Map.Entry<Integer, BoardField> entry : board.getFieldsArray().entrySet()) {
                if (entry.getValue().getCard() instanceof BuyableCard)
                    entry.getValue().getRectangle().setOnMouseClicked(x -> {

                        BuyableCard buyableCard = (BuyableCard) entry.getValue().getCard();
                        CityCard citycard = (CityCard) buyableCard;

                        System.out.println("xxx");

                        if( !buyableCard.isOnPledge()
                                && citycard.getNumberOfBuildings() == 0
                                && buyableCard.getBelongsTo().equals(getPlayerColor())
                                 ){

                            addCash(buyableCard.getFieldCost());
                            buyableCard.getPledgeAndBuildingsIndicator().setVisible(true);
                            buyableCard.setPledgeAndBuildingsIndicator(new ImageView(board.getPledgeImage()));
                            buyableCard.setOnPledge(true);
                            board.putInfoToProcess("+ #" + getPlayerColor() + " pledged " + buyableCard.getFieldName() + " for " + buyableCard.getFieldCost() + "$");
                        }


                    });
            }

        }
    }
}
