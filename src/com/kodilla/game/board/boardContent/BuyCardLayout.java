package com.kodilla.game.board.boardContent;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BuyCardLayout {
    private StackPane buyCardContentLayout;
    private Button buyCardYesButton = new Button("yes");
    private Button buyCardNoButton = new Button("no");

    public void prepareBuyCardLayout(GridPane grid){
        Rectangle buyCardRectangle = new Rectangle(200,80, Color.WHITE);
        buyCardRectangle.setStroke(Color.BLACK);

        Text buyCardFieldName = new Text("Do you want to buy? ");

        HBox buyCardOptionsLayout = new HBox(buyCardYesButton, buyCardNoButton);
        VBox buyCardLayout = new VBox(buyCardFieldName, buyCardOptionsLayout);
        buyCardContentLayout = new StackPane(buyCardRectangle, buyCardLayout);

        StackPane.setMargin(buyCardLayout, new Insets(20,1,1,25));
        HBox.setMargin(buyCardYesButton, new Insets(1,1,1,30));
        HBox.setMargin(buyCardNoButton, new Insets(1,1,1,10));
        grid.add(buyCardContentLayout, 4,8);

        buyCardContentLayout.setVisible(false);
    }

    public StackPane getBuyCardContentLayout() {
        return buyCardContentLayout;
    }

    public Button getBuyCardYesButton() {
        return buyCardYesButton;
    }

    public Button getBuyCardNoButton() {
        return buyCardNoButton;
    }
}
