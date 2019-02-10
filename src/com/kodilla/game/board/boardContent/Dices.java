package com.kodilla.game.board.boardContent;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Dices {
    private Button diceRollBtn = new Button("Roll");
    private Button endTurnBtn = new Button("Turn end");
    private Image dice1 = new Image("file:resources/dice1.png");
    private Image dice2 = new Image("file:resources/dice2.png");
    private Image dice3 = new Image("file:resources/dice3.png");
    private Image dice4 = new Image("file:resources/dice4.png");
    private Image dice5 = new Image("file:resources/dice5.png");
    private Image dice6 = new Image("file:resources/dice6.png");
    private ImageView firstDiceShow = new ImageView(dice1);
    private ImageView secondDiceShow = new ImageView(dice2);

    public void prepareDiceAndDiceButtons(GridPane grid){
        endTurnBtn.setVisible(false);
        StackPane turnsButtonsLayout = new StackPane(endTurnBtn, diceRollBtn);
        VBox diceLayout = new VBox();
        diceLayout.getChildren().addAll(turnsButtonsLayout, firstDiceShow, secondDiceShow);
        grid.add(diceLayout, 9,8);
    }

    public Button getDiceRollBtn() {
        return diceRollBtn;
    }

    public Image getDice1() {
        return dice1;
    }

    public Image getDice2() {
        return dice2;
    }

    public Image getDice3() {
        return dice3;
    }

    public Image getDice4() {
        return dice4;
    }

    public Image getDice5() {
        return dice5;
    }

    public Image getDice6() {
        return dice6;
    }

    public ImageView getFirstDiceShow() {
        return firstDiceShow;
    }

    public ImageView getSecondDiceShow() {
        return secondDiceShow;
    }

    public Button getEndTurnBtn() {
        return endTurnBtn;
    }
}
