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

    void createPlayers(){
        redPlayer = new Human(board.getFieldsArray().get(0).getRedPlayerStopX(), board.getFieldsArray().get(0).getRedPlayerStopY(), "red");
        board.getCashLabels().setPlayerRedLabel(redPlayer.getCash());
        playersList.add(redPlayer);

        if(!board.getMainMenu().getBluePlayerButton().getText().equals("AI"))
            bluePlayer = new Human(board.getFieldsArray().get(0).getBluePlayerStopX(), board.getFieldsArray().get(0).getBluePlayerStopY(), "blue");
        else
            bluePlayer = new AI(board.getFieldsArray().get(0).getBluePlayerStopX(), board.getFieldsArray().get(0).getBluePlayerStopY(), "blue");
        board.getCashLabels().setPlayerBlueLabel(bluePlayer.getCash());
        playersList.add(bluePlayer);

         if(!board.getMainMenu().getGreenPlayerButton().getText().equals("AI"))
             greenPlayer = new Human(board.getFieldsArray().get(0).getGreenPlayerStopX(), board.getFieldsArray().get(0).getGreenPlayerStopY(), "green");
         else if(!board.getMainMenu().getGreenPlayerButton().getText().equals("Human"))
             greenPlayer = new AI(board.getFieldsArray().get(0).getGreenPlayerStopX(), board.getFieldsArray().get(0).getGreenPlayerStopY(), "green");
         if(!board.getMainMenu().getGreenPlayerButton().getText().equals("noone")) {
             board.getCashLabels().setPlayerGreenLabel(greenPlayer.getCash());
             playersList.add(greenPlayer);
         }
         else {
            greenPlayer.getPawn().setVisible(false);
            greenPlayer.setCash(0);
         }


         if(!board.getMainMenu().getYellowPlayerButton().getText().equals("AI"))
             yellowPlayer = new Human(board.getFieldsArray().get(0).getYellowPlayerStopX(), board.getFieldsArray().get(0).getYellowPlayerStopY(), "yellow");
         else if(!board.getMainMenu().getYellowPlayerButton().getText().equals("Human"))
             yellowPlayer = new AI(board.getFieldsArray().get(0).getYellowPlayerStopX(), board.getFieldsArray().get(0).getYellowPlayerStopY(), "yellow");
         if(!board.getMainMenu().getYellowPlayerButton().getText().equals("noone")) {
             board.getCashLabels().setPlayerYellowLabel(yellowPlayer.getCash());
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

    private void playersTurns(Player player) {

        checkWinner();

        if(player.isDefeated()){
            playersTurns(choosePlayerDependingOnTurn());
        }else {


            if (!player.isInPrison()) {
                board.getDices().getDiceRollBtn().setVisible(true);

                redPlayer.getPawnAfterImage().setVisible(false);
                bluePlayer.getPawnAfterImage().setVisible(false);
                greenPlayer.getPawnAfterImage().setVisible(false);
                yellowPlayer.getPawnAfterImage().setVisible(false);


                if (player instanceof Human) {
                    board.getDices().getDiceRollBtn().setOnMouseClicked(e -> {

                        board.getDices().getDiceRollBtn().setVisible(false);
                        player.getPawnAfterImage().setVisible(true);


                        playerActions(player);


                        if (!board.getDices().getDiceRollBtn().isVisible())
                            board.getDices().getEndTurnBtn().setVisible(true);

                    });

                    board.getDices().getEndTurnBtn().setOnMouseClicked(x -> {
                        board.getDices().getEndTurnBtn().setVisible(false);
                        playersTurns(choosePlayerDependingOnTurn());
                    });
                } else if (player instanceof AI) {
                    player.getPawnAfterImage().setVisible(true);

                    playerActions(player);

                    playersTurns(choosePlayerDependingOnTurn());
                }
            } else {
                board.getTable().putInfoToProcess("+ " + player.getPlayerColor() + " is in prison for " + player.getInPrisonTurnCounter() + " turns");
                player.checkAndSetPrison();
                playersTurns(choosePlayerDependingOnTurn());
            }
        }
    }

    private void playerActions(Player player) {
        //CHEAT >>>>
         player.giveMeAllFields(board, bluePlayer);
        // CHEAT ^^^^

        checkAndSetPlayerTurnIndicator(player);
        player.checkAndDoActions(board);
        useDice();
        player.movePlayer(sumDicesResult(), board);
        player.purchaseCard(board);
        payFee(player);
        checkIfPlayerIsOnTaxCard(player);
        checkEventCard(player);
        checkIfPlayerIsInGoesToPrisonPosition(player);
        updatePlayerCashInLabels();
    }

    private void checkAndSetPlayerTurnIndicator(Player player){
        board.getCashLabels().setPlayersCashRectangleStroke(player.getPlayerColor());
    }

    private void checkIfPlayerIsInGoesToPrisonPosition(Player player){
        if(player.getPlayerPositionNumber() == 30 && !player.isInPrison()){
            player.setPlayerPositionNumber(10);
            player.movePlayer(board);
            board.getTable().putInfoToProcess("+ " + player.getPlayerColor() + " goes to prison for  " + player.getInPrisonTurnCounter() + " turns");
            player.setInPrison(true);
        }
    }

    private Player choosePlayerDependingOnTurn(){
        playerPicker++;
        if(playerPicker == numberOfPlayers)
            playerPicker = 0;

        return playersList.get(playerPicker);

    }

    private void updatePlayerCashInLabels(){
        board.getCashLabels().setPlayerRedLabel(redPlayer.getCash());
        board.getCashLabels().setPlayerBlueLabel(bluePlayer.getCash());
        board.getCashLabels().setPlayerGreenLabel(greenPlayer.getCash());
        board.getCashLabels().setPlayerYellowLabel(yellowPlayer.getCash());
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
                    if(player instanceof AI)
                        ((AI) player).pledgeOrSell(board, sumOfFee);
                    player.substractCash(sumOfFee);
                }
                else {
                    sumOfFee = player.getCash();
                    deleteDefeatedPlayer(player);

                }


                    switch (enemyPlayerColor) {
                        case "red":
                            redPlayer.addCash(sumOfFee);
                            board.getCashLabels().setPlayerRedLabel(redPlayer.getCash());
                            break;
                        case "blue":
                            bluePlayer.addCash(sumOfFee);
                            board.getCashLabels().setPlayerRedLabel(bluePlayer.getCash());
                            break;
                        case "green":
                            greenPlayer.addCash(sumOfFee);
                            board.getCashLabels().setPlayerRedLabel(greenPlayer.getCash());
                            break;
                        case "yellow":
                            greenPlayer.addCash(sumOfFee);
                            board.getCashLabels().setPlayerRedLabel(greenPlayer.getCash());
                            break;
                    }



                if (sumOfFee != 0)
                    board.getTable().putInfoToProcess("+ #" + player.getPlayerColor() + " pays the player #" + enemyPlayerColor + " " + sumOfFee + "$");

                updatePlayerCashInLabels();
            }
        }
    }

    private void checkIfPlayerIsOnTaxCard(Player player){

        if(player.getPlayerPositionNumber() == 4 || player.getPlayerPositionNumber() == 38) {
            TaxCard tempCard = (TaxCard) board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard();
            int cashToPay = tempCard.pickAndPayTax();
            if(sumPlayerProperty(player) < cashToPay)
                deleteDefeatedPlayer(player);
            else{
                if(player instanceof AI)
                    ((AI) player).pledgeOrSell(board, cashToPay);
                player.substractCash(cashToPay);
                board.getTable().putInfoToProcess("+ #" + player.getPlayerColor() + " pays tax of " + cashToPay + "$");
            }

        }

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

    private void deleteDefeatedPlayer(Player player){
        player.setDefeated(true);
        player.setCash(0);
        player.getPawn().setVisible(false);
        player.getPawnAfterImage().setVisible(false);

        board.getTable().putInfoToProcess("+ #" + player.getPlayerColor() + " has been defeated");

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
            board.getDices().getDiceRollBtn().setDisable(true);
            board.getDices().getEndTurnBtn().setDisable(true);
            for(int i = 0; i<10; i++)
                board.getTable().putInfoToProcess("+ #" + winner.getPlayerColor() + " IS A WINNER!!!");
        }
    }

    private void useDice(){

        int diceResult1 = diceRoll();
        int diceResult2 = diceRoll();

        switch (diceResult1){
            case 1: board.getDices().getFirstDiceShow().setImage(board.getDices().getDice1()); break;
            case 2: board.getDices().getFirstDiceShow().setImage(board.getDices().getDice2()); break;
            case 3: board.getDices().getFirstDiceShow().setImage(board.getDices().getDice3()); break;
            case 4: board.getDices().getFirstDiceShow().setImage(board.getDices().getDice4()); break;
            case 5: board.getDices().getFirstDiceShow().setImage(board.getDices().getDice5()); break;
            case 6: board.getDices().getFirstDiceShow().setImage(board.getDices().getDice6()); break;
        }

        switch (diceResult2){
            case 1: board.getDices().getSecondDiceShow().setImage(board.getDices().getDice1()); break;
            case 2: board.getDices().getSecondDiceShow().setImage(board.getDices().getDice2()); break;
            case 3: board.getDices().getSecondDiceShow().setImage(board.getDices().getDice3()); break;
            case 4: board.getDices().getSecondDiceShow().setImage(board.getDices().getDice4()); break;
            case 5: board.getDices().getSecondDiceShow().setImage(board.getDices().getDice5()); break;
            case 6: board.getDices().getSecondDiceShow().setImage(board.getDices().getDice6()); break;
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


    public Player getRed() {
        return redPlayer;
    }

    public Player getBlue() {
        return bluePlayer;
    }

    public Player getGreen() {
        return greenPlayer;
    }

    public Player getYellow() {
        return yellowPlayer;
    }

    public Board getBoard() {
        return board;
    }
}
