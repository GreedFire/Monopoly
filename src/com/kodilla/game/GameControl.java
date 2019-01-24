package com.kodilla.game;

import com.kodilla.game.player.AI;
import com.kodilla.game.player.Human;
import com.kodilla.game.board.Board;
import com.kodilla.game.player.Player;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Random;

class GameControl {

    private Board board = new Board();
    private Player redPlayer;
    private Player bluePlayer;

    private int firstDiceResult;
    private int secondDiceResult;

    private Text playerRedLabel;
    private Text playerBlueLabel;



    boolean gameEnd = false;

    GameControl(){
       redPlayer = new Human(board.getFieldsArray().get(0).getRedPlayerStopX(), board.getFieldsArray().get(0).getRedPlayerStopY(), "red");
        playerRedLabel = new Text("Red: " + redPlayer.getCash() + "$");
       bluePlayer = new AI(board.getFieldsArray().get(0).getBluePlayerStopX(), board.getFieldsArray().get(0).getBluePlayerStopY(), "blue");
       playerBlueLabel = new Text("Blue: " + bluePlayer.getCash() + "$");
    }

    void gameFlow(){
            playersTurns();
    }

    void showInfo(){

        board.showFieldInfo();
    }

    public void showPlayersInfo(Player red, Player blue){

        VBox playersInfoLayout = new VBox(playerRedLabel, playerBlueLabel);

        board.getGrid().add(playersInfoLayout, 1,9);
    }

    private void playersTurns(){

        //RED TURN
        board.getDiceRollBtn().setOnMouseClicked(e -> {

            board.getDiceRollBtn().setVisible(false);

            useDice();
            redPlayer.movePlayer(sumDicesRoll(), board);
            playerRedLabel.setText("Red: " + redPlayer.getCash() + "$");

            if(!board.getDiceRollBtn().isVisible())
                board.getEndTurnBtn().setVisible(true);

            showPlayersInfo(redPlayer, bluePlayer);

        });

        //BLUE TURN
          board.getEndTurnBtn().setOnMouseClicked(e -> {

              board.getEndTurnBtn().setVisible(false);
              useDice();
              bluePlayer.movePlayer(sumDicesRoll(), board);
              playerBlueLabel.setText("Blue: " + bluePlayer.getCash() + "$");
              board.getDiceRollBtn().setVisible(true);

          });
    }

    private void useDice(){

                dicesRoll();

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
    }

    private void dicesRoll(){
        Random rand = new Random();
        firstDiceResult = rand.nextInt(6)+1;
        secondDiceResult = rand.nextInt(6)+1;
    }

    private int sumDicesRoll(){
        return firstDiceResult + secondDiceResult;
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
