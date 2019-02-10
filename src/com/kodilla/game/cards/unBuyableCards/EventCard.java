package com.kodilla.game.cards.unBuyableCards;

import com.kodilla.game.board.Board;
import com.kodilla.game.cards.Card;
import com.kodilla.game.player.Player;

import java.util.Random;

public class EventCard extends Card {

    public EventCard(String typeOfCard) {
        super(typeOfCard);
    }

    public void pickEvent(Player player, Board board){

        int money = 0;
        int newPlayerPosition = 0;

        Random randGen = new Random();
        int picker = randGen.nextInt(6);


        switch(picker){
            case 0:
                money = 25;
                player.addCash(money);
                board.getTable().putInfoToProcess("+ #" + player.getPlayerColor() + " gets money worth " + money + "$");
                break;
            case 1:
                money = 50;
                player.addCash(money);
                board.getTable().putInfoToProcess("+ #" + player.getPlayerColor() + " gets money worth " + money + "$");
                break;
            case 2:
                money = 100;
                player.addCash(money);
                board.getTable().putInfoToProcess("+ #" + player.getPlayerColor() + " gets money worth " + money + "$");
                break;
            case 3:
                money = 150;
                player.addCash(money);
                board.getTable().putInfoToProcess("+ #" + player.getPlayerColor() + " gets money worth " + money + "$");
                break;
            case 4:
                money = 200;
                player.addCash(money);
                board.getTable().putInfoToProcess("+ #" + player.getPlayerColor() + " gets money worth " + money + "$");
                break;
            case 5:
                newPlayerPosition = randGen.nextInt(40);
                player.setPlayerPositionNumber(newPlayerPosition);
                player.movePlayer(board);
                break;

        }


    }
}
