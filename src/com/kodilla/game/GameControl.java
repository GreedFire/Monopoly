package com.kodilla.game;

import com.kodilla.game.player.AI;
import com.kodilla.game.player.Human;
import com.kodilla.game.board.Board;
import com.kodilla.game.player.Player;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
            redPlayer.movePlayer(sumDicesRoll(), board);
                board.putInfoToProcess("+ #red moved to field #" + redPlayer.getFieldPositionNumber());
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


              useDice();
              bluePlayer.movePlayer(sumDicesRoll(), board);
                board.putInfoToProcess("+ #blue moved to field #" + bluePlayer.getFieldPositionNumber());
              board.setPlayerBlueLabel(bluePlayer.getCash());
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
