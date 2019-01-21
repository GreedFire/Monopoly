package com.kodilla.game.Player;

import com.kodilla.game.board.Board;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private int fieldPositionNumber = 0;
    private int playerPositionX;
    private int playerPositionY;
    private int cash = 1000;
    private String playerColor;
    private Circle pawn;

    public Player(int playerPositionX, int playerPositionY, String playerColor) {
        this.playerPositionX = playerPositionX;
        this.playerPositionY = playerPositionY;
        this.playerColor = playerColor;

        pawn = new Circle(getPlayerPositionX(), getPlayerPositionY(), 10);
        pawn.setStroke(Color.BLACK);
        if(playerColor.equals("red"))
            pawn.setFill(Color.RED);
        else if(playerColor.equals("blue"))
            pawn.setFill(Color.BLUE);
    }

    public void movePlayer(int diceRoll){
        fieldPositionNumber = diceRoll;
        // Getting X and Y where player can stop his pawn from board
        playerPositionX = Board.getBoard().get(fieldPositionNumber).getRedPlayerStopX();
        playerPositionY = Board.getBoard().get(fieldPositionNumber).getRedPlayerStopY();

        // Moving pawn of player
        pawn.setCenterX(getPlayerPositionX());
        pawn.setCenterY(getPlayerPositionY());
    }

    private int getPlayerPositionX() {
        return playerPositionX;
    }

    private int getPlayerPositionY() {
        return playerPositionY;
    }

    public Circle getPawn() {
        return pawn;
    }
}
