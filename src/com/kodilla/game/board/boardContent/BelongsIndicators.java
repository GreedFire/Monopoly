package com.kodilla.game.board.boardContent;

import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.BuyAbleCard;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class BelongsIndicators {

    private final Image pledgeImage = new Image("file:resources/pledge.png");
    private final Image zeroBuildingsImage = new Image("file:resources/0buildings.png");
    private final Image oneBuildingImage = new Image("file:resources/1buildings.png");
    private final Image twoBuildingsImage = new Image("file:resources/2buildings.png");
    private final Image threeBuildingsImage = new Image("file:resources/3buildings.png");
    private final Image fourBuildingsImage = new Image("file:resources/4buildings.png");
    private final Image fiveBuildingsImage = new Image("file:resources/5buildings.png");

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

        BuyAbleCard temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(1).getCard();
        HBox hbox = new HBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 9,10);
        setBelongsIndicatorsProperly4(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(3).getCard();
        hbox = new HBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 7,10);
        setBelongsIndicatorsProperly4(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(5).getCard();
        hbox = new HBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 5,10);
        setBelongsIndicatorsProperly4(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(6).getCard();
        hbox = new HBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 4,10);
        setBelongsIndicatorsProperly4(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(8).getCard();
        hbox = new HBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 2,10);
        setBelongsIndicatorsProperly4(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(9).getCard();
        hbox = new HBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 1,10);
        setBelongsIndicatorsProperly4(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(11).getCard();
        VBox vbox = new VBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,9);
        setBelongsIndicatorsProperly3(temporaryBuyAbleCard, vbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(12).getCard();
        vbox = new VBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,8);
        setBelongsIndicatorsProperly3(temporaryBuyAbleCard, vbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(13).getCard();
        vbox = new VBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,7);
        setBelongsIndicatorsProperly3(temporaryBuyAbleCard, vbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(14).getCard();
        vbox = new VBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,6);
        setBelongsIndicatorsProperly3(temporaryBuyAbleCard, vbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(15).getCard();
        vbox = new VBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,5);
        setBelongsIndicatorsProperly3(temporaryBuyAbleCard, vbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(16).getCard();
        vbox = new VBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,4);
        setBelongsIndicatorsProperly3(temporaryBuyAbleCard, vbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(18).getCard();
        vbox = new VBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,2);
        setBelongsIndicatorsProperly3(temporaryBuyAbleCard, vbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(19).getCard();
        vbox = new VBox(temporaryBuyAbleCard.getBelongsIndicator(), temporaryBuyAbleCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,1);
        setBelongsIndicatorsProperly3(temporaryBuyAbleCard, vbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(21).getCard();
        hbox = new HBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(hbox, 1,0);
        setBelongsIndicatorsProperly2(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(23).getCard();
        hbox = new HBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(hbox, 3,0);
        setBelongsIndicatorsProperly2(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(24).getCard();
        hbox = new HBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(hbox, 4,0);
        setBelongsIndicatorsProperly2(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(25).getCard();
        hbox = new HBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(hbox, 5,0);
        setBelongsIndicatorsProperly2(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(26).getCard();
        hbox = new HBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(hbox, 6,0);
        setBelongsIndicatorsProperly2(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(27).getCard();
        hbox = new HBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(hbox, 7,0);
        setBelongsIndicatorsProperly2(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(28).getCard();
        hbox = new HBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(hbox, 8,0);
        setBelongsIndicatorsProperly2(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(29).getCard();
        hbox = new HBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(hbox, 9,0);
        setBelongsIndicatorsProperly2(temporaryBuyAbleCard, hbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(31).getCard();
        vbox = new VBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(vbox, 10,1);
        setBelongsIndicatorsProperly(temporaryBuyAbleCard, vbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(32).getCard();
        vbox = new VBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(vbox, 10,2);
        setBelongsIndicatorsProperly(temporaryBuyAbleCard, vbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(34).getCard();
        vbox = new VBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(vbox, 10,4);
        setBelongsIndicatorsProperly(temporaryBuyAbleCard, vbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(35).getCard();
        vbox = new VBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(vbox, 10,5);
        setBelongsIndicatorsProperly(temporaryBuyAbleCard, vbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(37).getCard();
        vbox = new VBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(vbox, 10,7);
        setBelongsIndicatorsProperly(temporaryBuyAbleCard, vbox);

        temporaryBuyAbleCard = (BuyAbleCard) fieldsArray.get(39).getCard();
        vbox = new VBox(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), temporaryBuyAbleCard.getBelongsIndicator());
        grid.add(vbox, 10,9);
        setBelongsIndicatorsProperly(temporaryBuyAbleCard, vbox);

    }

    private void setBelongsIndicatorsProperly4(BuyAbleCard temporaryBuyAbleCard, HBox hbox) {
        GridPane.setMargin(hbox, new Insets(10,1,70,7));
        HBox.setMargin(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), new Insets(0,0,0,30));
        hbox.setDisable(true);
        temporaryBuyAbleCard.getBelongsIndicator().setVisible(false);
        temporaryBuyAbleCard.getPledgeAndBuildingsIndicator().setVisible(false);
    }

    private void setBelongsIndicatorsProperly3(BuyAbleCard temporaryBuyAbleCard, VBox vbox) {
        GridPane.setMargin(vbox, new Insets(10,0,0,106));
        VBox.setMargin(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyAbleCard.getBelongsIndicator().setVisible(false);
        temporaryBuyAbleCard.getPledgeAndBuildingsIndicator().setVisible(false);
    }

    private void setBelongsIndicatorsProperly2(BuyAbleCard temporaryBuyAbleCard, HBox hbox) {
        GridPane.setMargin(hbox, new Insets(80,0,0,5));
        HBox.setMargin(temporaryBuyAbleCard.getPledgeAndBuildingsIndicator(), new Insets(0,30,0,0));
        hbox.setDisable(true);
        temporaryBuyAbleCard.getBelongsIndicator().setVisible(false);
        temporaryBuyAbleCard.getPledgeAndBuildingsIndicator().setVisible(false);
    }

    private void setBelongsIndicatorsProperly(BuyAbleCard temporaryBuyAbleCard, VBox vbox) {
        GridPane.setMargin(vbox, new Insets(5,0,0,10));
        VBox.setMargin(temporaryBuyAbleCard.getBelongsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyAbleCard.getBelongsIndicator().setVisible(false);
        temporaryBuyAbleCard.getPledgeAndBuildingsIndicator().setVisible(false);
    }
}
