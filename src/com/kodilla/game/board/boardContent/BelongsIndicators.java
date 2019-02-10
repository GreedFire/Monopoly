package com.kodilla.game.board.boardContent;

import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.BuyableCard;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class BelongsIndicators {

    private Image pledgeImage = new Image("file:resources/pledge.png");
    private Image zeroBuildingsImage = new Image("file:resources/0buildings.png");
    private Image oneBuildingImage = new Image("file:resources/1buildings.png");
    private Image twoBuildingsImage = new Image("file:resources/2buildings.png");
    private Image threeBuildingsImage = new Image("file:resources/3buildings.png");
    private Image fourBuildingsImage = new Image("file:resources/4buildings.png");
    private Image fiveBuildingsImage = new Image("file:resources/5buildings.png");

    public Image getPledgeImage() {
        return pledgeImage;
    }

    public Image getZeroBuildingsImage() {
        return zeroBuildingsImage;
    }

    public Image getOneBuildingImage() {
        return oneBuildingImage;
    }

    public Image getTwoBuildingsImage() {
        return twoBuildingsImage;
    }

    public Image getThreeBuildingsImage() {
        return threeBuildingsImage;
    }

    public Image getFourBuildingsImage() {
        return fourBuildingsImage;
    }

    public Image getFiveBuildingsImage() {
        return fiveBuildingsImage;
    }

    public void prepareBelongsToIndicators(GridPane grid, HashMap<Integer, BoardField> fieldsArray){

        BuyableCard temporaryBuyableCard = (BuyableCard) fieldsArray.get(1).getCard();
        HBox hbox = new HBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 9,10);
        setBelongsIndicatorsProperly4(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(3).getCard();
        hbox = new HBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 7,10);
        setBelongsIndicatorsProperly4(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(5).getCard();
        hbox = new HBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 5,10);
        setBelongsIndicatorsProperly4(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(6).getCard();
        hbox = new HBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 4,10);
        setBelongsIndicatorsProperly4(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(8).getCard();
        hbox = new HBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 2,10);
        setBelongsIndicatorsProperly4(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(9).getCard();
        hbox = new HBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 1,10);
        setBelongsIndicatorsProperly4(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(11).getCard();
        VBox vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,9);
        setBelongsIndicatorsProperly3(temporaryBuyableCard, vbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(12).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,8);
        setBelongsIndicatorsProperly3(temporaryBuyableCard, vbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(13).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,7);
        setBelongsIndicatorsProperly3(temporaryBuyableCard, vbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(14).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,6);
        setBelongsIndicatorsProperly3(temporaryBuyableCard, vbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(15).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,5);
        setBelongsIndicatorsProperly3(temporaryBuyableCard, vbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(16).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,4);
        setBelongsIndicatorsProperly3(temporaryBuyableCard, vbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(18).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,2);
        setBelongsIndicatorsProperly3(temporaryBuyableCard, vbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(19).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,1);
        setBelongsIndicatorsProperly3(temporaryBuyableCard, vbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(21).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 1,0);
        setBelongsIndicatorsProperly2(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(23).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 3,0);
        setBelongsIndicatorsProperly2(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(24).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 4,0);
        setBelongsIndicatorsProperly2(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(25).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 5,0);
        setBelongsIndicatorsProperly2(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(26).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 6,0);
        setBelongsIndicatorsProperly2(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(27).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 7,0);
        setBelongsIndicatorsProperly2(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(28).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 8,0);
        setBelongsIndicatorsProperly2(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(29).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 9,0);
        setBelongsIndicatorsProperly2(temporaryBuyableCard, hbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(31).getCard();
        vbox = new VBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(vbox, 10,1);
        setBelongsIndicatorsProperly(temporaryBuyableCard, vbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(32).getCard();
        vbox = new VBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(vbox, 10,2);
        setBelongsIndicatorsProperly(temporaryBuyableCard, vbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(34).getCard();
        vbox = new VBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(vbox, 10,4);
        setBelongsIndicatorsProperly(temporaryBuyableCard, vbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(35).getCard();
        vbox = new VBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(vbox, 10,5);
        setBelongsIndicatorsProperly(temporaryBuyableCard, vbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(37).getCard();
        vbox = new VBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(vbox, 10,7);
        setBelongsIndicatorsProperly(temporaryBuyableCard, vbox);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(39).getCard();
        vbox = new VBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(vbox, 10,9);
        setBelongsIndicatorsProperly(temporaryBuyableCard, vbox);

    }

    private void setBelongsIndicatorsProperly4(BuyableCard temporaryBuyableCard, HBox hbox) {
        GridPane.setMargin(hbox, new Insets(10,1,70,7));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,0,0,30));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);
    }

    private void setBelongsIndicatorsProperly3(BuyableCard temporaryBuyableCard, VBox vbox) {
        GridPane.setMargin(vbox, new Insets(10,0,0,106));
        VBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);
    }

    private void setBelongsIndicatorsProperly2(BuyableCard temporaryBuyableCard, HBox hbox) {
        GridPane.setMargin(hbox, new Insets(80,0,0,5));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,30,0,0));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);
    }

    private void setBelongsIndicatorsProperly(BuyableCard temporaryBuyableCard, VBox vbox) {
        GridPane.setMargin(vbox, new Insets(5,0,0,10));
        VBox.setMargin(temporaryBuyableCard.getBelongsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);
    }
}
