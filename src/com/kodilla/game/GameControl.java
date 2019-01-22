package com.kodilla.game;

import com.kodilla.game.player.AI;
import com.kodilla.game.player.Human;
import com.kodilla.game.board.Board;
import com.kodilla.game.board.BoardField;
import com.kodilla.game.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameControl {

    private Map<Integer, BoardField> board = new HashMap<>(Board.getBoard());
    private Player redPlayer;
    private Player bluePlayer;

    boolean gameEnd = false;

    public GameControl(){
       redPlayer = new Human(board.get(0).getRedPlayerStopX(), board.get(0).getRedPlayerStopY(), "red");
       bluePlayer = new AI(board.get(0).getBluePlayerStopX(), board.get(0).getBluePlayerStopY(), "blue");
    }

    public void gameFlow(){

        while(!gameEnd){

            playersTurns();

            gameEnd = true;

        }
    }

    private void playersTurns(){

        boolean redPlayerTurn = false;
        boolean bluePlayerTurn = false;

        while(!redPlayerTurn){
            redPlayer.movePlayer(diceRoll());
            redPlayerTurn = true;
        }

        while(!bluePlayerTurn){
            bluePlayer.movePlayer(diceRoll());
            bluePlayerTurn = true;
        }

    }

    private int diceRoll(){

        Random rand = new Random();
        int dice1 = rand.nextInt(6)+1;
        int dice2 = rand.nextInt(6)+1;

        return dice1 + dice2;
    }

    public Player getRed() {
        return redPlayer;
    }

    public Player getBlue() {
        return bluePlayer;
    }
}
