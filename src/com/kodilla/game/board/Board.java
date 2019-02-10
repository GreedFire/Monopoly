package com.kodilla.game.board;

import com.kodilla.game.board.boardContent.BuyCardLayout;
import com.kodilla.game.board.boardContent.CashLabels;
import com.kodilla.game.board.boardContent.MainMenu;
import com.kodilla.game.board.boardContent.Table;
import com.kodilla.game.cards.BuyableCard;
import com.kodilla.game.cards.buyableCards.CircleCard;
import com.kodilla.game.cards.buyableCards.CityCard;
import com.kodilla.game.cards.buyableCards.TriangleCard;
import com.kodilla.game.cards.unBuyableCards.EventCard;
import com.kodilla.game.cards.unBuyableCards.TaxCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private HashMap<Integer, BoardField> fieldsArray = new HashMap<>();
    private GridPane grid = new GridPane();
    private MainMenu mainMenu = new MainMenu();
    private Table table = new Table();
    private CashLabels cashLabels = new CashLabels();
    private BuyCardLayout buyCardLayout = new BuyCardLayout();

    private Button diceRollBtn = new Button("Roll");
    private Button endTurnBtn = new Button("Turn end");

    private Image dice1 = new Image("file:resources/dice1.png");
    private Image dice2 = new Image("file:resources/dice2.png");
    private Image dice3 = new Image("file:resources/dice3.png");
    private Image dice4 = new Image("file:resources/dice4.png");
    private Image dice5 = new Image("file:resources/dice5.png");
    private Image dice6 = new Image("file:resources/dice6.png");
    private Image pledgeImage = new Image("file:resources/pledge.png");
    private Image zeroBuildingsImage = new Image("file:resources/0buildings.png");
    private Image oneBuildingImage = new Image("file:resources/1buildings.png");
    private Image twoBuildingsImage = new Image("file:resources/2buildings.png");
    private Image threeBuildingsImage = new Image("file:resources/3buildings.png");
    private Image fourBuildingsImage = new Image("file:resources/4buildings.png");
    private Image fiveBuildingsImage = new Image("file:resources/5buildings.png");
    private ImageView firstDiceShow = new ImageView(dice1);
    private ImageView secondDiceShow = new ImageView(dice2);



    public Board(){

        mainMenu.prepareMainMenu();

        prepareFieldsOnBoard();

        prepareRowsAndColumnsOfGrid();

        prepareGridForCardsInfoVisibility();

        prepareDiceAndDiceButtons();

        cashLabels.preparePlayersLabels(grid);

        table.prepareProcessText();

        table.prepareTableContent(grid);

        buyCardLayout.prepareBuyCardLayout(grid);

        prepareBelongsToIndicators();

        showFieldInfo();
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public Table getTable() {
        return table;
    }

    public CashLabels getCashLabels() {
        return cashLabels;
    }

    public void showFieldInfo(){

        // Creating nodes for positioning card layouts
        StackPane entireCardLayout = new StackPane();
        StackPane fieldColorAndInfoLayout = new StackPane();
        VBox infoInsideColor = new VBox();
        VBox cardInfoLayout = new VBox();
        cardInfoLayout.setAlignment(Pos.CENTER);
        infoInsideColor.setAlignment(Pos.CENTER);

        // Creating text variables
        Text fieldName = new Text();
        Text fieldCost = new Text();
        Text zeroBuildingsFee = new Text();
        Text oneBuildingFee = new Text();
        Text twoBuildingsFee = new Text();
        Text threeBuildingsFee = new Text();
        Text fourBuildingsFee = new Text();
        Text fiveBuildingsFee = new Text();
        Text costOfBuilding = new Text();

        // Creating card color figure
        Rectangle colorOfCard = new Rectangle(150,40, Color.WHITE);
        colorOfCard.setArcHeight(30);
        colorOfCard.setArcWidth(30);

        // Creating card figure
        Rectangle cardBody = new Rectangle(160,200, Color.WHITE);
        cardBody.setStroke(Color.BLACK);
        cardBody.setStrokeWidth(1.5);
        cardBody.setArcHeight(30);
        cardBody.setArcWidth(30);

        //Creating triangle and circle for other cards
        Circle circle = new Circle(100, Color.WHITE);
        circle.setStroke(Color.BLACK);
        Polygon triangle = new Polygon(15.0, 0.0,0.0, 30.0,30.0, 30.0);
        triangle.setFill(Color.WHITE);
        triangle.setStroke(Color.BLACK);

        // Positioning nodes
        entireCardLayout.getChildren().addAll(cardBody, cardInfoLayout);
        fieldColorAndInfoLayout.getChildren().addAll(colorOfCard, infoInsideColor);
        infoInsideColor.getChildren().addAll(fieldName, fieldCost);
        cardInfoLayout.getChildren().addAll(triangle, circle, fieldColorAndInfoLayout, zeroBuildingsFee, oneBuildingFee, twoBuildingsFee, threeBuildingsFee, fourBuildingsFee, fiveBuildingsFee, costOfBuilding);

        GridPane.setMargin(entireCardLayout, new Insets(1,1,1,40));
        VBox.setMargin(fieldColorAndInfoLayout, new Insets(1,1,10,1));

        // Adding card layout to grid
        grid.add(entireCardLayout,7,2);

        // Loop for access entire HashMap to get to hidden rectangles to show card info
        // and get access to cards
        for(Map.Entry<Integer, BoardField> entry : fieldsArray.entrySet()) {
            entry.getValue().getRectangle().setOnMouseEntered(e -> {

                   // "Checking what type of card there is"
                    if(entry.getValue().getCard() instanceof CityCard){
                        CityCard tempCard = (CityCard) entry.getValue().getCard();

                        triangle.setVisible(false);
                        triangle.getPoints().setAll(0.0,0.0,0.0,0.0,0.0,0.0);

                        circle.setVisible(false);
                        circle.setRadius(0);
                        twoBuildingsFee.setVisible(true);
                        threeBuildingsFee.setVisible(true);
                        fourBuildingsFee.setVisible(true);
                        fiveBuildingsFee.setVisible(true);
                        costOfBuilding.setVisible(true);
                        colorOfCard.setVisible(true);


                        // Picking color for card
                        String colorOfCardPicker = tempCard.getCardColor();
                        switch(colorOfCardPicker){
                            case "gray": colorOfCard.setFill(Color.GRAY); break;
                            case "teal": colorOfCard.setFill(Color.CYAN); break;
                            case "purple": colorOfCard.setFill(Color.MEDIUMPURPLE); break;
                            case "orange": colorOfCard.setFill(Color.ORANGE); break;
                            case "red": colorOfCard.setFill(Color.RED); break;
                            case "yellow": colorOfCard.setFill(Color.YELLOW); break;
                            case "green": colorOfCard.setFill(Color.LIGHTGREEN); break;
                            case "blue": colorOfCard.setFill(Color.BLUE); break;
                        }
                        fieldName.setText(tempCard.getFieldName());
                        fieldCost.setText(tempCard.getFieldCost() + "$");
                        zeroBuildingsFee.setText("fee: " + tempCard.getZeroBuildingsFee() + "$");
                        oneBuildingFee.setText("1 building: " + tempCard.getOneBuildingsFee() + "$");
                        twoBuildingsFee.setText("2 buildings: " + tempCard.getTwoBuildingsFee() + "$");
                        threeBuildingsFee.setText("3 buildings: " + tempCard.getThreeBuildingsFee() + "$");
                        fourBuildingsFee.setText("4 buildings: " + tempCard.getFourBuildingsFee() + "$");
                        fiveBuildingsFee.setText("5 buildings: " + tempCard.getFiveBuildingsFee() + "$");
                        costOfBuilding.setText("Build price: " + tempCard.getBuildCost() + "$");

                        entireCardLayout.setVisible(true);
                    }
                   else if(entry.getValue().getCard() instanceof CircleCard){
                        CircleCard tempCard = (CircleCard) entry.getValue().getCard();

                        triangle.setVisible(false);
                        triangle.getPoints().setAll(15.0, 0.0,0.0, 30.0,30.0, 30.0);
                        circle.setVisible(true);
                        circle.setRadius(15);
                        twoBuildingsFee.setVisible(true);
                        threeBuildingsFee.setVisible(true);
                        fourBuildingsFee.setVisible(false);
                        fiveBuildingsFee.setVisible(false);
                        costOfBuilding.setVisible(false);
                        colorOfCard.setVisible(false);

                        fieldName.setText(tempCard.getFieldName());
                        fieldCost.setText(tempCard.getFieldCost() + "$");
                        zeroBuildingsFee.setText("1x O: " + tempCard.getOneCircleFee() + "$");
                        oneBuildingFee.setText("2x O: " + tempCard.getTwoCirclesFee() + "$");
                        twoBuildingsFee.setText("3x O: " + tempCard.getThreeCirclesFee() + "$");
                        threeBuildingsFee.setText("4x O: " + tempCard.getFourCirclesFee() + "$");

                        entireCardLayout.setVisible(true);
                   }
                    else if(entry.getValue().getCard() instanceof TriangleCard){
                        TriangleCard tempCard = (TriangleCard) entry.getValue().getCard();

                        triangle.setVisible(true);
                        triangle.getPoints().setAll(15.0, 0.0,0.0, 30.0,30.0, 30.0);
                        circle.setVisible(false);
                        circle.setRadius(0);
                        twoBuildingsFee.setVisible(false);
                        threeBuildingsFee.setVisible(false);
                        fourBuildingsFee.setVisible(false);
                        fiveBuildingsFee.setVisible(false);
                        costOfBuilding.setVisible(false);
                        colorOfCard.setVisible(false);

                        fieldName.setText(tempCard.getFieldName());
                        fieldCost.setText(tempCard.getFieldCost() + "$");
                        zeroBuildingsFee.setText("1x △: " + tempCard.getOneTriangleFee() + "*dice $");
                        oneBuildingFee.setText("2x △: " + tempCard.getTwoTrianglesFee() + "*dice $");

                        entireCardLayout.setVisible(true);

                    }

            });

            entireCardLayout.setVisible(false);
            entry.getValue().getRectangle().setOnMouseExited(e -> entireCardLayout.setVisible(false));
        }
    }

    private void prepareDiceAndDiceButtons(){
        endTurnBtn.setVisible(false);
        StackPane turnsButtonsLayout = new StackPane(endTurnBtn, diceRollBtn);
        VBox diceLayout = new VBox();
        diceLayout.getChildren().addAll(turnsButtonsLayout, firstDiceShow, secondDiceShow);
        grid.add(diceLayout, 9,8);
    }

    private void prepareRowsAndColumnsOfGrid(){
        ColumnConstraints col1 = new ColumnConstraints(130);
        ColumnConstraints col2 = new ColumnConstraints(71);
        RowConstraints row1 = new RowConstraints(104);
        RowConstraints row2 = new RowConstraints(71);

        grid.getColumnConstraints().addAll(col1, col2, col2, col2, col2, col2, col2, col2, col2, col2, col1);
        grid.getRowConstraints().addAll(row1, row2, row2, row2, row2, row2, row2, row2, row2, row2, row1);
    }

    private void prepareGridForCardsInfoVisibility(){
        grid.add(fieldsArray.get(0).getRectangle(),10,10);
        grid.add(fieldsArray.get(1).getRectangle(),9,10);
        grid.add(fieldsArray.get(2).getRectangle(),8,10);
        grid.add(fieldsArray.get(3).getRectangle(),7,10);
        grid.add(fieldsArray.get(4).getRectangle(),6,10);
        grid.add(fieldsArray.get(5).getRectangle(),5,10);
        grid.add(fieldsArray.get(6).getRectangle(),4,10);
        grid.add(fieldsArray.get(7).getRectangle(),3,10);
        grid.add(fieldsArray.get(8).getRectangle(),2,10);
        grid.add(fieldsArray.get(9).getRectangle(),1,10);
        grid.add(fieldsArray.get(10).getRectangle(),0,10);
        grid.add(fieldsArray.get(11).getRectangle(),0,9);
        grid.add(fieldsArray.get(12).getRectangle(),0,8);
        grid.add(fieldsArray.get(13).getRectangle(),0,7);
        grid.add(fieldsArray.get(14).getRectangle(),0,6);
        grid.add(fieldsArray.get(15).getRectangle(),0,5);
        grid.add(fieldsArray.get(16).getRectangle(),0,4);
        grid.add(fieldsArray.get(17).getRectangle(),0,3);
        grid.add(fieldsArray.get(18).getRectangle(),0,2);
        grid.add(fieldsArray.get(19).getRectangle(),0,1);
        grid.add(fieldsArray.get(20).getRectangle(),0,0);
        grid.add(fieldsArray.get(21).getRectangle(),1,0);
        grid.add(fieldsArray.get(22).getRectangle(),2,0);
        grid.add(fieldsArray.get(23).getRectangle(),3,0);
        grid.add(fieldsArray.get(24).getRectangle(),4,0);
        grid.add(fieldsArray.get(25).getRectangle(),5,0);
        grid.add(fieldsArray.get(26).getRectangle(),6,0);
        grid.add(fieldsArray.get(27).getRectangle(),7,0);
        grid.add(fieldsArray.get(28).getRectangle(),8,0);
        grid.add(fieldsArray.get(29).getRectangle(),9,0);
        grid.add(fieldsArray.get(30).getRectangle(),10,0);
        grid.add(fieldsArray.get(31).getRectangle(),10,1);
        grid.add(fieldsArray.get(32).getRectangle(),10,2);
        grid.add(fieldsArray.get(33).getRectangle(),10,3);
        grid.add(fieldsArray.get(34).getRectangle(),10,4);
        grid.add(fieldsArray.get(35).getRectangle(),10,5);
        grid.add(fieldsArray.get(36).getRectangle(),10,6);
        grid.add(fieldsArray.get(37).getRectangle(),10,7);
        grid.add(fieldsArray.get(38).getRectangle(),10,8);
        grid.add(fieldsArray.get(39).getRectangle(),10,9);
    }

    private void prepareBelongsToIndicators(){

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

    private void prepareFieldsOnBoard(){

        fieldsArray.put(0, new BoardField("Nothing", 881, 795, 881, 825,841,795,841,825,128, 102));

        fieldsArray.put(1, new BoardField("Card", 755, 795, 755, 825, 715,795,715,825, 72, 102, new CityCard("City Card", "field #1", 1, 60,2, 10, 30, 90, 160, 250, 50, "gray")));
        fieldsArray.put(2, new BoardField("Event Card", 683, 795, 683 ,825,643,795,643,825, 72, 102, new EventCard("Event Card")));
        fieldsArray.put(3, new BoardField("Card", 612, 795, 612, 825,572,795,572,825,72, 102, new CityCard("City Card", "field #3", 1, 60, 4, 20, 60, 180, 320, 450, 50, "gray")));
        fieldsArray.put(4, new BoardField("Tax Card", 541,795,541,825,501,795,501,825,72,102, new TaxCard("Tax Card")));
        fieldsArray.put(5, new BoardField("Card", 469, 795, 469, 825,429,795,429,825,72,102, new CircleCard("Circle Card", "field #5", 200)));
        fieldsArray.put(6, new BoardField("Card", 398, 795, 398,825,358,795,358,825,72,102 ,new CityCard("City Card", "field #6", 2, 100,6,30,90,270,400,550,50, "teal")));
        fieldsArray.put(7, new BoardField("Event Card", 327,795,327,825,387,795,387,825,72, 102, new EventCard("Event Card")));
        fieldsArray.put(8, new BoardField("Card", 255,795,255,825,215,795,215,825,72,102 ,new CityCard("City Card", "field #8", 2, 100, 6,30, 90, 270, 400, 550, 50, "teal")));
        fieldsArray.put(9, new BoardField("Card", 184, 795, 184 ,825,144,795,144,825,72,102 ,new CityCard("City Card", "field #9", 2, 120, 8, 40, 100, 300, 450, 600, 50, "teal")));

        fieldsArray.put(10, new BoardField("Nothing", 113,795,113,825,73,795,73,825,128,102));

        fieldsArray.put(11, new BoardField("Card", 77,729,47,729,77,689,47,689, 128,72 ,new CityCard("City Card", "field #11", 3,140,10,50,150,450,625,750,100, "purple")));
        fieldsArray.put(12, new BoardField("Card", 77,657,47,657,77,647,47,647,128,72,new TriangleCard("Triangle Card", "field #12", 150)));
        fieldsArray.put(13, new BoardField("Card", 77,585,47,585,77,545,47,545,128,72, new CityCard("City Card", "field #13", 3, 140, 10,50,150,450,625,750,100, "purple")));
        fieldsArray.put(14, new BoardField("Card",77,513,47,513,77,473,47,473,128,72 ,new CityCard("City Card", "field #14", 3,160,12,60,180,500,700,900,100, "purple")));
        fieldsArray.put(15, new BoardField("Card",77,441,47,441,77,401,47,401,128,72, new CircleCard("Circle Card", "field #15", 200)));
        fieldsArray.put(16, new BoardField("Card", 77,369,47,369,77,329,47,329,128,72 ,new CityCard("City Card", "field #16", 4, 180,14,70,200,550,750,950,100, "orange")));
        fieldsArray.put(17, new BoardField("Event Card", 77,297,47,297,77,257,47,257,128,72, new EventCard("Event Card")));
        fieldsArray.put(18, new BoardField("Card", 77,225,47,225,77,185,47,185,128,72 ,new CityCard("City Card", "field #18", 4,  180,14,70,200,550,750,950,100, "orange")));
        fieldsArray.put(19, new BoardField("Card", 77,153,47,153,77,113,47,113,128,72 ,new CityCard("City Card", "field #19", 4, 200,16,80,220,600,800,1000,100, "orange")));

        fieldsArray.put(20, new BoardField("Nothing", 77,81,47,81,77,41,47,41,128,102));

        fieldsArray.put(21, new BoardField("Card", 143,51,143,21,183,51,183,21,72,102, new CityCard("City Card,", "field #21", 5,220,18,90,250,700,875,1050,150, "red")));
        fieldsArray.put(22, new BoardField("Event Card", 215,51,215,21,175,51,175,21,72,102, new EventCard("Event Card")));
        fieldsArray.put(23, new BoardField("Card", 287,51,287,21,327,51,327,21,72,102 ,new CityCard("City Card", "field #23", 5, 220,18,90,250,700,875,1050,150, "red")));
        fieldsArray.put(24, new BoardField("Card", 359,51,359,21,399,51,399,21, 72,102,new CityCard("City Card", "field #24", 5,240,20,100,300,750,925,1100,150, "red")));
        fieldsArray.put(25, new BoardField("Card", 431,51,431,21,471,51,471,21,72,102, new CircleCard("Circle Card", "field #25", 200)));
        fieldsArray.put(26, new BoardField("Card", 503,51,503,21,543,51,543,21, 72,102,new CityCard("City Card", "field #26", 6,260,22,110,330,800,975,1150,150, "yellow")));
        fieldsArray.put(27, new BoardField("Card", 575,51,575,21,615,51,615,21,72,102, new CityCard("City Card", "field #27", 6,260,22,110,330,800,975,1150, 150, "yellow")));
        fieldsArray.put(28, new BoardField("Card", 647,51,647,21,687,51,687,21,72,102, new TriangleCard("Triangle Card", "field #28", 150)));
        fieldsArray.put(29, new BoardField("Card", 719,51,719,21,759,51,759,21, 72,102,new CityCard("City Card", "field #29", 6,280,24,120,360,850,1025,1200,150, "yellow")));

        fieldsArray.put(30, new BoardField("Prison", 791,51,791,21,791,51,791,21,128,102));

        fieldsArray.put(31, new BoardField("Card", 821,117,851,117,821,157,851,157,128,72, new CityCard("City Card", "field #31", 7,300,26,130,390,900,1100,1275,200, "green")));
        fieldsArray.put(32, new BoardField("Card", 821,189,851,189,821,229,851,229,128,72 , new CityCard("City Card", "field #32", 7, 300,26,130,390,900,1100,1275,200, "green")));
        fieldsArray.put(33, new BoardField("Event Card", 821,261,851,261,821,221,851,221,128,72, new EventCard("Event Card")));
        fieldsArray.put(34, new BoardField("Card", 821,333,851,333,821,373,851,373, 128,72, new CityCard("City Card", "field #34", 7,320,28,150,450,1000,1200,1400,200, "green" )));
        fieldsArray.put(35, new BoardField("Card", 821,405,851,405,821,445,851,445,128,72, new CircleCard("Circle Card", "field #35", 200)));
        fieldsArray.put(36, new BoardField("Event Card", 821,477,851,477,821,517,851,517,128,72, new EventCard("Event Card")));
        fieldsArray.put(37, new BoardField("Card", 821,549,851,549,821,589,851,589, 128,72, new CityCard("City Card", "field #37", 8,350,35,175,500,1100,1300,1500,200, "blue")));
        fieldsArray.put(38, new BoardField("Tax Card", 821,621,851,621,821,661,851,661,128,72, new TaxCard("Tax Card")));
        fieldsArray.put(39, new BoardField("Card", 821,693,851,693,821,723,851,723, 128,72, new CityCard("City Card", "field #39", 8,400,50,200,600,1400,1700,2000,200, "blue")));

    }

    public HashMap<Integer, BoardField> getFieldsArray() {
        return fieldsArray;
    }

    public GridPane getGrid() {
        return grid;
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

    public BuyCardLayout getBuyCardLayout() {
        return buyCardLayout;
    }
}
