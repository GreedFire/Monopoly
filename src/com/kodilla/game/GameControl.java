package com.kodilla.game;

import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.BuyAbleCard;
import com.kodilla.game.cards.buyableCards.CircleCard;
import com.kodilla.game.cards.buyableCards.CityCard;
import com.kodilla.game.cards.buyableCards.TriangleCard;
import com.kodilla.game.cards.unBuyableCards.EventCard;
import com.kodilla.game.cards.unBuyableCards.TaxCard;
import com.kodilla.game.player.AI;
import com.kodilla.game.player.Human;
import com.kodilla.game.board.Board;
import com.kodilla.game.player.Player;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

class GameControl {

    private final Board board = new Board();
    private Player redPlayer;
    private Player bluePlayer;
    private Player greenPlayer;
    private Player yellowPlayer;
    private int firstDiceResult;
    private int secondDiceResult;
    private final ArrayList<Player> playersList = new ArrayList<>();
    private int numberOfPlayers;
    private int playerPicker = 0;
    private String enemyColorForTrade;

    public void setEnemyColorForTrade(String enemyColorForTrade) {
        this.enemyColorForTrade = enemyColorForTrade;
    }

    void createPlayers(){
        redPlayer = new Human(board.getFieldsArray().get(0).getRedPlayerStopX(), board.getFieldsArray().get(0).getRedPlayerStopY(), Player.getRED());
        board.getCashLabels().setPlayerRedLabel(redPlayer.getCash());
        playersList.add(redPlayer);

        if(!board.getMainMenu().getBluePlayerButton().getText().equals("AI"))
            bluePlayer = new Human(board.getFieldsArray().get(0).getBluePlayerStopX(), board.getFieldsArray().get(0).getBluePlayerStopY(), Player.getBLUE());
        else
            bluePlayer = new AI(board.getFieldsArray().get(0).getBluePlayerStopX(), board.getFieldsArray().get(0).getBluePlayerStopY(), Player.getBLUE());
        board.getCashLabels().setPlayerBlueLabel(bluePlayer.getCash());
        playersList.add(bluePlayer);

         if(!board.getMainMenu().getGreenPlayerButton().getText().equals("AI"))
             greenPlayer = new Human(board.getFieldsArray().get(0).getGreenPlayerStopX(), board.getFieldsArray().get(0).getGreenPlayerStopY(), Player.getGREEN());
         else if(!board.getMainMenu().getGreenPlayerButton().getText().equals("Human"))
             greenPlayer = new AI(board.getFieldsArray().get(0).getGreenPlayerStopX(), board.getFieldsArray().get(0).getGreenPlayerStopY(), Player.getGREEN());
         if(!board.getMainMenu().getGreenPlayerButton().getText().equals("Empty")) {
             board.getCashLabels().setPlayerGreenLabel(greenPlayer.getCash());
             playersList.add(greenPlayer);
         }
         else {
            greenPlayer.getPawn().setVisible(false);
            greenPlayer.setCash(0);
         }


         if(!board.getMainMenu().getYellowPlayerButton().getText().equals("AI"))
             yellowPlayer = new Human(board.getFieldsArray().get(0).getYellowPlayerStopX(), board.getFieldsArray().get(0).getYellowPlayerStopY(), Player.getYELLOW());
         else if(!board.getMainMenu().getYellowPlayerButton().getText().equals("Human"))
             yellowPlayer = new AI(board.getFieldsArray().get(0).getYellowPlayerStopX(), board.getFieldsArray().get(0).getYellowPlayerStopY(), Player.getYELLOW());
         if(!board.getMainMenu().getYellowPlayerButton().getText().equals("Empty")) {
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
            if (player.isInPrison()) {
                redPlayer.getPawnAfterImage().setVisible(false);
                bluePlayer.getPawnAfterImage().setVisible(false);
                greenPlayer.getPawnAfterImage().setVisible(false);
                yellowPlayer.getPawnAfterImage().setVisible(false);

                if (player instanceof Human) {

                    board.getDices().getDiceRollBtn().setVisible(true);
                    board.getDices().getDiceRollBtn().setOnMouseClicked(e -> {
                        player.getPawnAfterImage().setVisible(true);
                        board.getDices().getDiceRollBtn().setVisible(false);
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
                    board.getDices().getEndTurnBtn().setVisible(true);

                    board.getDices().getEndTurnBtn().setOnMouseClicked(x -> {
                        board.getDices().getEndTurnBtn().setVisible(false);
                        playersTurns(choosePlayerDependingOnTurn());
                    });

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
         //player.giveMeAllFields(board, redPlayer);
        //CHEAT >>>>

        checkAndSetPlayerTurnIndicator(player);
        if(player instanceof Human)
            board.getTable().getMenuButton3().setOnMouseClicked(e -> player.checkAndDoActions(board));
        else if(player instanceof AI)
            player.checkAndDoActions(board);
        board.getTable().getMenuButton2().setOnMouseClicked(e -> trade(board, player));
        useDice();
        player.movePlayer(sumDicesResult(), board);
        player.purchaseCard(board);
        checkEventCard(player);
        checkIfPlayerIsInGoesToPrisonPosition(player);
        payFee(player);
        checkIfPlayerIsOnTaxCard(player);
        updatePlayerCashInLabels();
    }

    private void trade(Board board, Player player) {
        ArrayList<BuyAbleCard> playerCards = new ArrayList<>();
        ArrayList<BuyAbleCard> enemyCards = new ArrayList<>();

            for (Map.Entry<Integer, BoardField> entry : board.getFieldsArray().entrySet()) {
                if (entry.getValue().getCard() instanceof BuyAbleCard) {
                    entry.getValue().getRectangle().setOnMouseClicked(x -> {

                        BuyAbleCard buyableCard = (BuyAbleCard) entry.getValue().getCard();

                        if (buyableCard.getBelongsTo().equals(player.getPlayerColor()) && board.getTable().getMenuButton2().getFill().equals(Color.WHEAT) && player instanceof Human) {
                            board.getTable().getPlayerTradeCard().setText(buyableCard.getFieldName());
                            if (playerCards.size() == 0)
                                playerCards.add(buyableCard);
                        }
                        if (!buyableCard.getBelongsTo().equals(player.getPlayerColor()) && !buyableCard.getBelongsTo().equals("nobody") && board.getTable().getMenuButton2().getFill().equals(Color.WHEAT) && player instanceof Human) {
                            setEnemyColorForTrade(buyableCard.getBelongsTo());
                            board.getTable().getEnemyTradeCard().setText(buyableCard.getFieldName());
                            if (enemyCards.size() == 0)
                                enemyCards.add(buyableCard);
                        }
                    });
                }
              //  if(board.getTable().getMenuButton2().getFill().equals(Color.WHITE))
               //     break;
            }

            board.getTable().getTrade().setOnMouseClicked(e -> {
                if(player instanceof Human) {
                    Player enemy = null;
                    switch (enemyColorForTrade) {
                        case "red":
                            enemy = redPlayer;
                            break;
                        case "blue":
                            enemy = bluePlayer;
                            break;
                        case "green":
                            enemy = greenPlayer;
                            break;
                        case "yellow":
                            enemy = yellowPlayer;
                            break;
                    }
                    // CANT BE LIKE THAT
                    if (player.getCash() > board.getTable().getPlayerTradeCash() && Objects.requireNonNull(enemy).getCash() > board.getTable().getEnemyTradeCash()) {

                        int sumPlayerTradeCost = playerCards.get(0).getFieldCost() + board.getTable().getPlayerTradeCash();
                        int sumEnemyTradeCost = enemyCards.get(0).getFieldCost() + board.getTable().getEnemyTradeCash();

                        // FOR AI
                        if(enemy instanceof AI) {
                            // ENEMY CONDITIONS TO AGREED
                            if (sumPlayerTradeCost >= sumEnemyTradeCost) {
                                //TRADED
                                doTrade(board, player, playerCards, enemyCards);
                            }
                            else board.getTable().getTradeInfo().setText("Player didn't agreed");
                        } else if(enemy instanceof Human){

                            Color enemyColor = null;
                            switch(enemy.getPlayerColor()){
                                case "red": enemyColor = Color.RED; break;
                                case "blue": enemyColor = Color.BLUE; break;
                                case "green": enemyColor = Color.GREEN; break;
                                case "yellow": enemyColor = Color.YELLOW; break;
                            }

                            board.getTable().getYes().setTextFill(enemyColor);
                            board.getTable().getNo().setTextFill(enemyColor);
                            board.getTable().getYes().setVisible(true);
                            board.getTable().getNo().setVisible(true);

                            board.getTable().getNo().setOnMouseClicked(f -> board.getTable().getTradeInfo().setText("Player didn't agreed"));
                            board.getTable().getYes().setOnMouseClicked(f -> doTrade(board, player, playerCards, enemyCards));
                        }

                    }
                }
                else board.getTable().getTradeInfo().setText("Something is wrong");
            });


            board.getTable().getReset().setOnMouseClicked(e -> resetTrade(board, playerCards, enemyCards));


    }

    private void doTrade(Board board, Player player, ArrayList<BuyAbleCard> playerCards, ArrayList<BuyAbleCard> enemyCards) {
        if (playerCards.size() != 0) {
            for (BuyAbleCard card : playerCards) {
                card.setBelongsTo(enemyColorForTrade);
                card.setBelongsIndicatorColor();
            }
        }
        if (enemyCards.size() != 0) {
            for (BuyAbleCard card : enemyCards) {
                card.setBelongsTo(player.getPlayerColor());
                card.setBelongsIndicatorColor();
            }
        }

        player.addCash(board.getTable().getEnemyTradeCash());
        player.subtractCash(board.getTable().getPlayerTradeCash());

        switch (enemyColorForTrade) {
            case "red":
                redPlayer.addCash(board.getTable().getPlayerTradeCash());
                redPlayer.subtractCash(board.getTable().getEnemyTradeCash());
                break;
            case "blue":
                bluePlayer.addCash(board.getTable().getPlayerTradeCash());
                bluePlayer.subtractCash(board.getTable().getEnemyTradeCash());
                break;
            case "green":
                greenPlayer.addCash(board.getTable().getPlayerTradeCash());
                greenPlayer.subtractCash(board.getTable().getEnemyTradeCash());
                break;
            case "yellow":
                yellowPlayer.addCash(board.getTable().getPlayerTradeCash());
                yellowPlayer.subtractCash(board.getTable().getEnemyTradeCash());
                break;
        }


        updatePlayerCashInLabels();

        resetTrade(board, playerCards, enemyCards);
        board.getTable().getTradeInfo().setText("Player agreed");
    }

    private void resetTrade(Board board, ArrayList<BuyAbleCard> playerCards, ArrayList<BuyAbleCard> enemyCards) {
        enemyColorForTrade = null;
        board.getTable().getPlayerTradeCard().setText("");
        board.getTable().getEnemyTradeCard().setText("");
        board.getTable().resetCash();
        board.getTable().updateTradeCashTexts();
        playerCards.clear();
        enemyCards.clear();
        board.getTable().getTradeInfo().setText("");
        board.getTable().getYes().setVisible(false);
        board.getTable().getNo().setVisible(false);
        board.getTable().getYes().setTextFill(Color.BLACK);
        board.getTable().getNo().setTextFill(Color.BLACK);
    }


    private void checkAndSetPlayerTurnIndicator(Player player){
        board.getCashLabels().setPlayersCashRectangleStroke(player.getPlayerColor());
    }

    private void checkIfPlayerIsInGoesToPrisonPosition(Player player){
        if(player.getPlayerPositionNumber() == 30 && player.isInPrison()){
            player.setPlayerPositionNumber(10);
            player.movePlayer(board);
            board.getTable().putInfoToProcess("+ " + player.getPlayerColor() + " goes to prison for  " + player.getInPrisonTurnCounter() + " turns");
            player.setInPrison(true);
        }
    }

    private Player choosePlayerDependingOnTurn(){
        redPlayer.setYourTurn(false);
        bluePlayer.setYourTurn(false);
        greenPlayer.setYourTurn(false);
        yellowPlayer.setYourTurn(false);

        playerPicker++;
        if(playerPicker == numberOfPlayers)
            playerPicker = 0;

        playersList.get(playerPicker).setYourTurn(true);
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

            BuyAbleCard temporaryCard = (BuyAbleCard) board.getFieldsArray().get(player.getPlayerPositionNumber()).getCard();
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
                    player.subtractCash(sumOfFee);
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
                            yellowPlayer.addCash(sumOfFee);
                            board.getCashLabels().setPlayerRedLabel(yellowPlayer.getCash());
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
                player.subtractCash(cashToPay);
                board.getTable().putInfoToProcess("+ #" + player.getPlayerColor() + " pays tax of " + cashToPay + "$");
            }

        }

    }

    private int sumPlayerProperty(Player player) {

        int sumPlayerCash = player.getCash();

        for (Map.Entry<Integer, BoardField> entry : board.getFieldsArray().entrySet()) {
            if (entry.getValue().getCard() instanceof BuyAbleCard) {
                BuyAbleCard buyableCard = (BuyAbleCard) entry.getValue().getCard();

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
                board.getTable().putInfoToProcess("+ #" + (winner != null ? winner.getPlayerColor() : null) + " IS A WINNER!!!");
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
