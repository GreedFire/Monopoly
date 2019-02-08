package com.kodilla.game;

import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.BuyableCard;
import com.kodilla.game.cards.buyableCards.CircleCard;
import com.kodilla.game.cards.buyableCards.CityCard;
import com.kodilla.game.cards.buyableCards.TriangleCard;
import com.kodilla.game.cards.unBuyableCards.EventCard;
import com.kodilla.game.cards.unBuyableCards.TaxCard;
import com.kodilla.game.player.AI;
import com.kodilla.game.player.Human;
import com.kodilla.game.board.Board;
import com.kodilla.game.player.Player;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

class GameControl {

    private Board board = new Board();
    private Player redPlayer;
    private Player bluePlayer;
    private Player greenPlayer;
    private Player yellowPlayer;

    private int firstDiceResult;
    private int secondDiceResult;
    private ArrayList<Player> playersList = new ArrayList<>();
    private int numberOfPlayers;
    private int playerPicker = 0;

    GameControl() {

    }

    void createPlayers(){

        redPlayer = new Human(board.getFieldsArray().get(0).getRedPlayerStopX(), board.getFieldsArray().get(0).getRedPlayerStopY(), "red");
        board.setPlayerRedLabel(redPlayer.getCash());
        playersList.add(redPlayer);

        if(!board.getBluePlayerButton().getText().equals("AI"))
            bluePlayer = new Human(board.getFieldsArray().get(0).getBluePlayerStopX(), board.getFieldsArray().get(0).getBluePlayerStopY(), "blue");
        else
            bluePlayer = new AI(board.getFieldsArray().get(0).getBluePlayerStopX(), board.getFieldsArray().get(0).getBluePlayerStopY(), "blue");
        board.setPlayerBlueLabel(bluePlayer.getCash());
        playersList.add(bluePlayer);

         if(!board.getGreenPlayerButton().getText().equals("AI"))
             greenPlayer = new Human(board.getFieldsArray().get(0).getGreenPlayerStopX(), board.getFieldsArray().get(0).getGreenPlayerStopY(), "green");
         else if(!board.getGreenPlayerButton().getText().equals("Human"))
             greenPlayer = new AI(board.getFieldsArray().get(0).getGreenPlayerStopX(), board.getFieldsArray().get(0).getGreenPlayerStopY(), "green");
         if(!board.getGreenPlayerButton().getText().equals("noone")) {
             board.setPlayerGreenLabel(greenPlayer.getCash());
             playersList.add(greenPlayer);
         }
         else {
            greenPlayer.getPawn().setVisible(false);
            greenPlayer.setCash(0);
         }


         if(!board.getYellowPlayerButton().getText().equals("AI"))
             yellowPlayer = new Human(board.getFieldsArray().get(0).getYellowPlayerStopX(), board.getFieldsArray().get(0).getYellowPlayerStopY(), "yellow");
         else if(!board.getYellowPlayerButton().getText().equals("Human"))
             yellowPlayer = new AI(board.getFieldsArray().get(0).getYellowPlayerStopX(), board.getFieldsArray().get(0).getYellowPlayerStopY(), "yellow");
         if(!board.getYellowPlayerButton().getText().equals("noone")) {
             board.setPlayerYellowLabel(yellowPlayer.getCash());
             playersList.add(yellowPlayer);
         }
         else {
             yellowPlayer.getPawn().setVisible(false);
             yellowPlayer.setCash(0);
         }


        numberOfPlayers = playersList.size();

    }

    void gameFlow() {
        playersTurns(playersList.get(playerPicker));
    }

    void showInfo() {
        board.showFieldInfo();
    }

    private void playersTurns(Player player) {

        checkWinner();

        if(player.isDefeated()){
            playersTurns(choosePlayerDependingOnTurn());
        }else {


            if (!player.isInPrison()) {
                player.setPlayerTurn(true);
                board.getDiceRollBtn().setVisible(true);

                redPlayer.getPawnAfterImage().setVisible(false);
                bluePlayer.getPawnAfterImage().setVisible(false);
                greenPlayer.getPawnAfterImage().setVisible(false);
                yellowPlayer.getPawnAfterImage().setVisible(false);


                if (player instanceof Human) {
                    board.getDiceRollBtn().setOnMouseClicked(e -> {

                        board.getDiceRollBtn().setVisible(false);
                        player.getPawnAfterImage().setVisible(true);


                        playerActions(player);


                        if (!board.getDiceRollBtn().isVisible())
                            board.getEndTurnBtn().setVisible(true);

                    });

                    board.getEndTurnBtn().setOnMouseClicked(x -> {
                        board.getEndTurnBtn().setVisible(false);
                        playersTurns(choosePlayerDependingOnTurn());
                    });
                } else if (player instanceof AI) {
                    player.getPawnAfterImage().setVisible(true);

                    playerActions(player);

                    playersTurns(choosePlayerDependingOnTurn());
                }
            } else {
                board.putInfoToProcess("+ " + player.getPlayerColor() + " is in prison for " + player.getInPrisonTurnCounter() + " turns");
                player.checkAndSetPrison();
                playersTurns(choosePlayerDependingOnTurn());
            }
        }
    }

    private void playerActions(Player player) {
        checkAndSetPlayerTurnIndicator(player);
        useDice();
        player.movePlayer(sumDicesResult(), board);
        player.purchaseCard(board);
        payFee(player);
        player.checkAndDoActions(board);
        //CHEAT >>>>
        player.giveMeAllFields(board, redPlayer);
        // CHEAT ^^^^
        checkIfPlayerIsInGoesToPrisonPosition(player);

        checkIfPlayerIsOnTaxCard(player);
        checkEventCard(player);

        updatePlayerCashInLabels();
    }

    private int sumPlayerProperty(Player player) {

        int sumPlayerCash = player.getCash();

        for (Map.Entry<Integer, BoardField> entry : board.getFieldsArray().entrySet()) {
            if (entry.getValue().getCard() instanceof BuyableCard) {
                BuyableCard buyableCard = (BuyableCard) entry.getValue().getCard();

                if (buyableCard.getBelongsTo().equals(player.getPlayerColor()) && !buyableCard.isOnPledge()) {
                    sumPlayerCash += buyableCard.getFieldCost();

                    if (buyableCard instanceof CityCard) {
                        CityCard cityCard = (CityCard) buyableCard;
                        sumPlayerCash += cityCard.getNumberOfBuildings() * cityCard.getBuildCost();
                    }
                }
            }
        }
        return sumPlayerCash;
    }



    private void checkAndSetPlayerTurnIndicator(Player player){
        board.setPlayerRedCashRectangleStrokeColorBLACK();
        board.setPlayerBlueCashRectangleStrokeColorBLACK();
        board.setPlayerGreenCashRectangleStrokeColorBLACK();
        board.setPlayerYellowCashRectangleStrokeColorBLACK();

        if(player.getPlayerColor().equals("red"))
            board.setPlayerRedCashRectangleStrokeColorRED();
        else if(player.getPlayerColor().equals("blue"))
            board.setPlayerBlueCashRectangleStrokeColorRED();
        else if(player.getPlayerColor().equals("green"))
            board.setPlayerGreenCashRectangleStrokeColorRED();
        else if(player.getPlayerColor().equals("yellow"))
            board.setPlayerYellowCashRectangleStrokeColorRED();
    }

    private void checkIfPlayerIsInGoesToPrisonPosition(Player player){
        if(player.getPlayerPositionNumber() == 30 && !player.isInPrison()){
            player.setPlayerPositionNumber(10);
            player.movePlayer(board);
            board.putInfoToProcess("+ " + player.getPlayerColor() + " goes to prison for  " + player.getInPrisonTurnCounter() + " turns");
            player.setInPrison(true);
        }
    }

    private Player choosePlayerDependingOnTurn(){
        playersList.get(0).setPlayerTurn(false);
        playersList.get(1).setPlayerTurn(false);
        if(playersList.size() > 2)
        playersList.get(2).setPlayerTurn(false);
        if(playersList.size() > 3)
        playersList.get(3).setPlayerTurn(false);

        playerPicker++;
        if(playerPicker == numberOfPlayers)
            playerPicker = 0;

        playersList.get(playerPicker).setPlayerTurn(true);

        return playersList.get(playerPicker);

    }

    private void updatePlayerCashInLabels(){
        board.setPlayerRedLabel(redPlayer.getCash());
        board.setPlayerBlueLabel(bluePlayer.getCash());
        board.setPlayerGreenLabel(greenPlayer.getCash());
        board.setPlayerYellowLabel(yellowPlayer.getCash());
    }

    private void checkIfPlayerIsOnTaxCard(Player player){

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

    private void payFee(Player player){

        if(board.getFieldsArray().get(player.getPlayerPositionNumber()).getTypeOfField().equals("Card")) {

            BuyableCard temporaryCard = (BuyableCard) board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard();
            String enemyPlayerColor = temporaryCard.getBelongsTo();
            int sumOfFee = 0;

            if (!temporaryCard.isOnPledge()) {
                if (board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard() instanceof CityCard) {
                    CityCard cityCard = (CityCard) board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard();
                    if (!cityCard.getBelongsTo().equals(player.getPlayerColor())
                            && !cityCard.getBelongsTo().equals("nobody")) {
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
                    if (!triangleCard.getBelongsTo().equals(player.getPlayerColor())
                            && !triangleCard.getBelongsTo().equals("nobody")) {
                        int sumOfTriangleCards = 0;
                        for (int i = 12; i <= 26; i += 16) {
                            TriangleCard checkedTriangleCard = (TriangleCard) board.getFieldsArray().get(i).getCard();
                            if (checkedTriangleCard.getBelongsTo().equals(enemyPlayerColor))
                                sumOfTriangleCards++;
                        }

                        switch (sumOfTriangleCards) {
                            case 1:
                                sumOfFee = (firstDiceResult + secondDiceResult) * 5;
                                break;
                            case 2:
                                sumOfFee = (firstDiceResult + secondDiceResult) * 10;
                                break;
                        }

                    }

                } else if (board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard() instanceof CircleCard) {
                    CircleCard circleCard = (CircleCard) board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard();
                    if (!circleCard.getBelongsTo().equals(player.getPlayerColor())
                            && !circleCard.getBelongsTo().equals("nobody")) {
                        int sumOfCircleCards = 0;
                        for (int i = 5; i <= 35; i += 10) {
                            CircleCard checkedCircleCard = (CircleCard) board.getFieldsArray().get(i).getCard();
                            if (checkedCircleCard.getBelongsTo().equals(enemyPlayerColor))
                                sumOfCircleCards++;
                        }

                        switch (sumOfCircleCards) {
                            case 1:
                                sumOfFee = 25;
                                break;
                            case 2:
                                sumOfFee = 50;
                                break;
                            case 3:
                                sumOfFee = 100;
                                break;
                            case 4:
                                sumOfFee = 200;
                                break;
                        }

                    }
                }

                if(sumOfFee <= sumPlayerProperty(player)) {
                    player.substractCash(sumOfFee);
                }
                else {
                    sumOfFee = player.getCash();
                    deleteDefeatedPlayer(player);

                }


                    switch (enemyPlayerColor) {
                        case "red":
                            redPlayer.addCash(sumOfFee);
                            board.setPlayerRedLabel(redPlayer.getCash());
                            break;
                        case "blue":
                            bluePlayer.addCash(sumOfFee);
                            board.setPlayerRedLabel(bluePlayer.getCash());
                            break;
                        case "green":
                            greenPlayer.addCash(sumOfFee);
                            board.setPlayerRedLabel(greenPlayer.getCash());
                            break;
                        case "yellow":
                            greenPlayer.addCash(sumOfFee);
                            board.setPlayerRedLabel(greenPlayer.getCash());
                            break;
                    }



                if (sumOfFee != 0)
                    board.putInfoToProcess("+ #" + player.getPlayerColor() + " pays the player #" + enemyPlayerColor + " " + sumOfFee + "$");

                updatePlayerCashInLabels();
            }
        }
    }

    private void deleteDefeatedPlayer(Player player){
        player.setDefeated(true);
        player.setCash(0);
        player.getPawn().setVisible(false);
        player.getPawnAfterImage().setVisible(false);

        board.putInfoToProcess("+ #" + player.getPlayerColor() + " has been defeated");

        updatePlayerCashInLabels();
    }

    private void checkWinner(){
        int countDefeatedPlayers = 0;
        Player winner = null;
        for(Player x : playersList){
            if(x.isDefeated())
                countDefeatedPlayers++;
            else
                winner = x;
        }

        if(countDefeatedPlayers == playersList.size()-1) {
            board.getDiceRollBtn().setDisable(true);
            board.getEndTurnBtn().setDisable(true);
            for(int i = 0; i<10; i++)
                board.putInfoToProcess("+ #" + winner.getPlayerColor() + " IS A WINNER!!!");
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

    Player getGreen() {
        return greenPlayer;
    }

    Player getYellow() {
        return yellowPlayer;
    }

    Board getBoard() {
        return board;
    }
}
