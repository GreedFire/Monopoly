package com.kodilla.game;

import com.kodilla.game.cards.BuyableCard;
import com.kodilla.game.cards.Card;
import com.kodilla.game.cards.unBuyableCards.TaxCard;
import com.kodilla.game.player.AI;
import com.kodilla.game.player.Human;
import com.kodilla.game.board.Board;
import com.kodilla.game.player.Player;

import java.util.Random;

class GameControl {

    private Board board = new Board();
    private Player redPlayer;
    private Player bluePlayer;

    boolean gameEnd = false;

    GameControl(){
       redPlayer = new Human(board.getFieldsArray().get(0).getRedPlayerStopX(), board.getFieldsArray().get(0).getRedPlayerStopY(), "red");
        board.setPlayerRedLabel(redPlayer.getCash());
       bluePlayer = new AI(board.getFieldsArray().get(0).getBluePlayerStopX(), board.getFieldsArray().get(0).getBluePlayerStopY(), "blue");
        board.setPlayerBlueLabel(bluePlayer.getCash());

    }

    void gameFlow(){
            playersTurns();
    }

    void showInfo(){
        board.showFieldInfo();
    }

    private void playersTurns(){

        board.getEndTurnBtn().setVisible(false);

        //==============================================================================
        //RED TURN STARTS HERE:
        //==============================================================================
        board.getDiceRollBtn().setOnMouseClicked(e -> {

            redPlayer.getPawnAfterImage().setVisible(true);
            bluePlayer.getPawnAfterImage().setVisible(false);
            board.getDiceRollBtn().setVisible(false);

            redPlayer.movePlayer(useDice(), board);
            purchaseCard(redPlayer);

            checkTax(redPlayer);

            board.setPlayerRedLabel(redPlayer.getCash());

            if(!board.getDiceRollBtn().isVisible())
                board.getEndTurnBtn().setVisible(true);

        });
        //==============================================================================
        //BLUE TURN STARTS HERE:
        //==============================================================================
          board.getEndTurnBtn().setOnMouseClicked(e -> {

              redPlayer.getPawnAfterImage().setVisible(false);
              bluePlayer.getPawnAfterImage().setVisible(true);
              board.getEndTurnBtn().setVisible(false);

              bluePlayer.movePlayer(useDice(), board);
                board.putInfoToProcess("+ #blue moved to field #" + bluePlayer.getFieldPositionNumber());

              board.setPlayerBlueLabel(bluePlayer.getCash());


              board.getDiceRollBtn().setVisible(true);

          });

    }

    private void checkTax(Player player){

        if(player.getFieldPositionNumber() == 4 || player.getFieldPositionNumber() == 38) {
            TaxCard tempCard = (TaxCard) board.getFieldsArray().get(redPlayer.getFieldPositionNumber()).getCard();
            int cashToPay = tempCard.pickAndPayTax();
            player.substractCash(cashToPay);
            board.putInfoToProcess("+ #" + player.getPlayerColor() + " pays tax of " + cashToPay + "$");
        }

    }

    private void purchaseCard(Player player){
            if(board.getFieldsArray().get(player.getFieldPositionNumber()).getCard() instanceof BuyableCard){
            Card givenCard = board.getFieldsArray().get(player.getFieldPositionNumber()).getCard();
            BuyableCard temporaryCityCard = null;
            if (givenCard instanceof BuyableCard)
                temporaryCityCard = (BuyableCard) givenCard;
            BuyableCard purchasableCard = temporaryCityCard;


            if (purchasableCard.getBelongsTo().equals("nobody"))
                board.getBuyCardContentLayout().setVisible(true);

            board.getBuyCardYesButton().setOnMouseClicked(e -> {
                purchasableCard.setBelongsTo(player.getPlayerColor());
                board.putInfoToProcess("+ #" + player.getPlayerColor() + " bought the " + purchasableCard.getFieldName());
                player.substractCash(purchasableCard.getFieldCost());
                board.getBuyCardContentLayout().setVisible(false);
                purchasableCard.getBelongsIndicator().setVisible(true);
                purchasableCard.setBelongsIndicatorColor();
                board.setPlayerRedLabel(redPlayer.getCash());

            });
            board.getBuyCardNoButton().setOnMouseClicked(e -> {
                board.getBuyCardContentLayout().setVisible(false);
                board.putInfoToProcess("+ #" + player.getPlayerColor() + " din't buy the field");
            });

        }
    }

    private int useDice(){

        int firstDiceResult = diceRoll();
        int secondDiceResult = diceRoll();

        switch (firstDiceResult){
            case 1: board.getFirstDiceShow().setImage(board.getDice1()); break;
            case 2: board.getFirstDiceShow().setImage(board.getDice2()); break;
            case 3: board.getFirstDiceShow().setImage(board.getDice3()); break;
            case 4: board.getFirstDiceShow().setImage(board.getDice4()); break;
            case 5: board.getFirstDiceShow().setImage(board.getDice5()); break;
            case 6: board.getFirstDiceShow().setImage(board.getDice6()); break;
        }

        switch (secondDiceResult){
            case 1: board.getSecondDiceShow().setImage(board.getDice1()); break;
            case 2: board.getSecondDiceShow().setImage(board.getDice2()); break;
            case 3: board.getSecondDiceShow().setImage(board.getDice3()); break;
            case 4: board.getSecondDiceShow().setImage(board.getDice4()); break;
            case 5: board.getSecondDiceShow().setImage(board.getDice5()); break;
            case 6: board.getSecondDiceShow().setImage(board.getDice6()); break;
        }

        return firstDiceResult + secondDiceResult;
    }

    private int diceRoll(){
        Random rand = new Random();
        return rand.nextInt(6)+1;
    }


    Player getRed() {
        return redPlayer;
    }

    Player getBlue() {
        return bluePlayer;
    }

    Board getBoard() {
        return board;
    }
}
