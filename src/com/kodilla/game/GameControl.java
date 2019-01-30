package com.kodilla.game;

import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.BuyableCard;
import com.kodilla.game.cards.Card;
import com.kodilla.game.cards.buyableCards.CircleCard;
import com.kodilla.game.cards.buyableCards.CityCard;
import com.kodilla.game.cards.buyableCards.TriangleCard;
import com.kodilla.game.cards.unBuyableCards.EventCard;
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

    private int firstDiceResult;
    private int secondDiceResult;

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

            useDice();
            redPlayer.movePlayer(sumDicesResult(), board);
            redPlayer.purchaseCard(board);
            payFee(redPlayer);


            checkTaxCard(redPlayer);
            checkEventCard(redPlayer);

            updatePlayerCashInLabels();

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

              useDice();
              bluePlayer.movePlayer(sumDicesResult(), board);
              bluePlayer.purchaseCard(board);
              payFee(bluePlayer);

              checkTaxCard(bluePlayer);
              checkEventCard(bluePlayer);

              updatePlayerCashInLabels();

              board.getDiceRollBtn().setVisible(true);

          });

    }

    private void updatePlayerCashInLabels(){
        board.setPlayerRedLabel(redPlayer.getCash());
        board.setPlayerBlueLabel(bluePlayer.getCash());
    }

    private void checkTaxCard(Player player){

        if(player.getPlayerPositionNumber() == 4 || player.getPlayerPositionNumber() == 38) {
            TaxCard tempCard = (TaxCard) board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard();
            int cashToPay = tempCard.pickAndPayTax();
            player.substractCash(cashToPay);
            board.putInfoToProcess("+ #" + player.getPlayerColor() + " pays tax of " + cashToPay + "$");
        }

    }

    private void checkEventCard(Player player){
        if(player.getPlayerPositionNumber() == 2 ||
                player.getPlayerPositionNumber() == 7 ||
                player.getPlayerPositionNumber() == 17 ||
                player.getPlayerPositionNumber() == 22 ||
                player.getPlayerPositionNumber() == 33 ||
                player.getPlayerPositionNumber() == 36) {

            EventCard eventCard = (EventCard) board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard();
            eventCard.pickEvent(player, board);
        }
    }

    public void payFee(Player player){

        if(board.getFieldsArray().get(player.getPlayerPositionNumber()).getTypeOfField().equals("Card")) {

            BuyableCard temporaryCard = (BuyableCard) board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard();
            String enemyPlayerColor = temporaryCard.getBelongsTo();
            int sumOfFee = 0;

            if (board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard() instanceof CityCard) {
                CityCard cityCard = (CityCard) board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard();
                if (!cityCard.getBelongsTo().equals(player.getPlayerColor())) {
                    switch (cityCard.getNumberOfBuildings()) {
                        case 0:
                            sumOfFee = cityCard.getZeroBuildingsFee();
                            break;
                        case 1:
                            sumOfFee = cityCard.getOneBuildingsFee();
                            break;
                        case 2:
                            sumOfFee = cityCard.getTwoBuildingsFee();
                            break;
                        case 3:
                            sumOfFee = cityCard.getThreeBuildingsFee();
                            break;
                        case 4:
                            sumOfFee = cityCard.getFourBuildingsFee();
                            break;
                        case 5:
                            sumOfFee = cityCard.getFiveBuildingsFee();
                            break;
                    }
                }
            } else if (board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard() instanceof TriangleCard) {
                TriangleCard triangleCard = (TriangleCard) board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard();
                if (!triangleCard.getBelongsTo().equals(player.getPlayerColor())) {
                    int sumOfTriangleCards = 0;
                    for(int i = 12; i<=26; i+=16){
                        TriangleCard checkedTriangleCard = (TriangleCard) board.getFieldsArray().get(i).getCard();
                        if(checkedTriangleCard.getBelongsTo().equals(enemyPlayerColor))
                            sumOfTriangleCards++;
                    }

                    switch (sumOfTriangleCards){
                        case 1: sumOfFee = (firstDiceResult + secondDiceResult) * 5; break;
                        case 2: sumOfFee = (firstDiceResult + secondDiceResult) * 10; break;
                    }

                }

            } else if (board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard() instanceof CircleCard) {
                CircleCard circleCard = (CircleCard) board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard();
                if (!circleCard.getBelongsTo().equals(player.getPlayerColor())) {
                    int sumOfCircleCards = 0;
                    for(int i = 5; i<=35; i+=10) {
                        CircleCard checkedCircleCard = (CircleCard) board.getFieldsArray().get(i).getCard();
                        if (checkedCircleCard.getBelongsTo().equals(enemyPlayerColor))
                            sumOfCircleCards++;
                    }

                    switch(sumOfCircleCards){
                        case 1: sumOfFee = 25; break;
                        case 2: sumOfFee = 50; break;
                        case 3: sumOfFee = 100; break;
                        case 4: sumOfFee = 200; break;
                    }

                }
            }

            player.substractCash(sumOfFee);
            switch (enemyPlayerColor) {
                case "red":
                    redPlayer.addCash(sumOfFee);
                    board.setPlayerRedLabel(redPlayer.getCash());
                    break;
                case "blue":
                    bluePlayer.addCash(sumOfFee);
                    board.setPlayerRedLabel(bluePlayer.getCash());
                    break;
            }
            if(!temporaryCard.getBelongsTo().equals("nobody") && sumOfFee != 0)
            board.putInfoToProcess("+ #" + player.getPlayerColor() + " pays the player #" + enemyPlayerColor + " " + sumOfFee + "$");

            switch (player.getPlayerColor()) {
                case "red":
                    board.setPlayerRedLabel(player.getCash());
                    break;
                case "blue":
                    board.setPlayerBlueLabel(player.getCash());
                    break;
            }
        }
    }

    private void useDice(){

        int diceResult1 = diceRoll();
        int diceResult2 = diceRoll();

        switch (diceResult1){
            case 1: board.getFirstDiceShow().setImage(board.getDice1()); break;
            case 2: board.getFirstDiceShow().setImage(board.getDice2()); break;
            case 3: board.getFirstDiceShow().setImage(board.getDice3()); break;
            case 4: board.getFirstDiceShow().setImage(board.getDice4()); break;
            case 5: board.getFirstDiceShow().setImage(board.getDice5()); break;
            case 6: board.getFirstDiceShow().setImage(board.getDice6()); break;
        }

        switch (diceResult2){
            case 1: board.getSecondDiceShow().setImage(board.getDice1()); break;
            case 2: board.getSecondDiceShow().setImage(board.getDice2()); break;
            case 3: board.getSecondDiceShow().setImage(board.getDice3()); break;
            case 4: board.getSecondDiceShow().setImage(board.getDice4()); break;
            case 5: board.getSecondDiceShow().setImage(board.getDice5()); break;
            case 6: board.getSecondDiceShow().setImage(board.getDice6()); break;
        }

        firstDiceResult = diceResult1;
        secondDiceResult = diceResult2;
    }

    private int sumDicesResult(){
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
