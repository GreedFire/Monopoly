package com.kodilla.game.board.boardContent;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CashLabels {

    private final Text playerRedLabel = new Text("Red: 0$");
    private final Text playerBlueLabel  = new Text("Blue: 0$");
    private final Text playerGreenLabel  = new Text("Green: 0$");
    private final Text playerYellowLabel  = new Text("Yellow: 0$");
    private final Rectangle playerRedCashRectangle = new Rectangle(100,25, Color.WHITE);
    private final Rectangle playerBlueCashRectangle = new Rectangle(100,25, Color.WHITE);
    private final Rectangle playerGreenCashRectangle = new Rectangle(100,25, Color.WHITE);
    private final Rectangle playerYellowCashRectangle = new Rectangle(100,25, Color.WHITE);

    public void preparePlayersLabels(GridPane grid){

        playerRedCashRectangle.setStroke(Color.BLACK);
        playerBlueCashRectangle.setStroke(Color.BLACK);
        playerGreenCashRectangle.setStroke(Color.BLACK);
        playerYellowCashRectangle.setStroke(Color.BLACK);

        Rectangle redPlayerColor = new Rectangle(10,10, Color.RED);
        redPlayerColor.setStroke(Color.BLACK);
        Rectangle bluePlayerColor = new Rectangle(10,10, Color.BLUE);
        bluePlayerColor.setStroke(Color.BLACK);
        Rectangle greenPlayerColor = new Rectangle(10,10, Color.GREEN);
        greenPlayerColor.setStroke(Color.BLACK);
        Rectangle yellowPlayerColor = new Rectangle(10,10, Color.YELLOW);
        yellowPlayerColor.setStroke(Color.BLACK);

        HBox hBox1 = new HBox(redPlayerColor, playerRedLabel);
        HBox hBox2 = new HBox(bluePlayerColor, playerBlueLabel);
        HBox hBox3 = new HBox(greenPlayerColor, playerGreenLabel);
        HBox hBox4 = new HBox(yellowPlayerColor, playerYellowLabel);

        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);
        hBox4.setAlignment(Pos.CENTER);

        HBox.setMargin(redPlayerColor, new Insets(1,5,1,1));
        HBox.setMargin(bluePlayerColor, new Insets(1,5,1,1));
        HBox.setMargin(greenPlayerColor, new Insets(1,5,1,1));
        HBox.setMargin(yellowPlayerColor, new Insets(1,5,1,1));

        StackPane Label1 = new StackPane(playerRedCashRectangle, hBox1);
        StackPane Label2 = new StackPane(playerBlueCashRectangle, hBox2);
        StackPane Label3 = new StackPane(playerGreenCashRectangle, hBox3);
        StackPane Label4 = new StackPane(playerYellowCashRectangle, hBox4);

        VBox playersInfoLayout = new VBox(Label1, Label2, Label3, Label4);

        VBox.setMargin(Label1, new Insets(1,1,1,30));
        VBox.setMargin(Label2, new Insets(1,1,1,30));
        VBox.setMargin(Label3, new Insets(1,1,1,30));
        VBox.setMargin(Label4, new Insets(1,1,1,30));
        grid.add(playersInfoLayout, 1,8);
    }

    public void setPlayerRedLabel(int cash) {
        playerRedLabel.setText("Red: " + cash + "$");
    }

    public void setPlayerBlueLabel(int cash) {
        playerBlueLabel.setText("Blue: " + cash + "$");
    }

    public void setPlayerGreenLabel(int cash) {
        playerGreenLabel.setText("Green: " + cash + "$");
    }

    public void setPlayerYellowLabel(int cash) {
        playerYellowLabel.setText("Yellow: " + cash + "$");
    }

    public void setPlayersCashRectangleStroke(String playerColor){
        playerRedCashRectangle.setStroke(Color.BLACK);
        playerBlueCashRectangle.setStroke(Color.BLACK);
        playerGreenCashRectangle.setStroke(Color.BLACK);
        playerYellowCashRectangle.setStroke(Color.BLACK);

        switch(playerColor){
            case "red": playerRedCashRectangle.setStroke(Color.RED); break;
            case "blue": playerBlueCashRectangle.setStroke(Color.RED); break;
            case "green": playerGreenCashRectangle.setStroke(Color.RED); break;
            case "yellow": playerYellowCashRectangle.setStroke(Color.RED); break;
        }
    }

}
