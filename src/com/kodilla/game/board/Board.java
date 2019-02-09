package com.kodilla.game.board;

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
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private HashMap<Integer, BoardField> fieldsArray = new HashMap<>();
    private GridPane grid = new GridPane();
    private GridPane menuGrid = new GridPane();
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
    private ImageView bannerView = new ImageView();
    private ImageView authorView = new ImageView();

    private Text playerRedLabel = new Text("Red: 0$");
    private Text playerBlueLabel  = new Text("Blue: 0$");
    private Text playerGreenLabel  = new Text("Green: 0$");
    private Text playerYellowLabel  = new Text("Yellow: 0$");
    private Rectangle playerRedCashRectangle = new Rectangle(100,25, Color.WHITE);
    private Rectangle playerBlueCashRectangle = new Rectangle(100,25, Color.WHITE);
    private Rectangle playerGreenCashRectangle = new Rectangle(100,25, Color.WHITE);
    private Rectangle playerYellowCashRectangle = new Rectangle(100,25, Color.WHITE);

    private Text[] gameplayInfo = new Text[10];

    private Rectangle actionButton1 = new Rectangle(200,50, Color.WHITE);
    private Rectangle actionButton2 = new Rectangle(200,50, Color.WHITE);
    private Rectangle actionButton3 = new Rectangle(200,50, Color.WHITE);
    private Rectangle actionButton4 = new Rectangle(200,50, Color.WHITE);

    private StackPane buyCardContentLayout;
    private Button buyCardYesButton = new Button("yes");
    private Button buyCardNoButton = new Button("no");

    private int playerPickerTextOne = 0;
    private int playerPickerTextTwo = 0;
    private Button redPlayerButton = new Button("Human");
    private Button bluePlayerButton = new Button("AI");
    private Button greenPlayerButton = new Button("noone");
    private Button yellowPlayerButton = new Button("noone");
    private Button startButton = new Button("START");



    public Board(){

        prepareMainMenu();

        prepareFieldsOnBoard();

        prepareRowsAndColumnsOfGrid();

        prepareGridForCardsInfoVisibility(); //ShowFieldInfo is for now in Main class

        prepareDiceAndDiceButtons();

        preparePlayersLabels();

        prepareProcessText();

        prepareTableContent();

        prepareBuyCardLayout();

        prepareBelongsToIndicators();

    }

    public Button getBluePlayerButton() {
        return bluePlayerButton;
    }

    public Button getGreenPlayerButton() {
        return greenPlayerButton;
    }

    public Button getYellowPlayerButton() {
        return yellowPlayerButton;
    }

    public Button getStartButton() {
        return startButton;
    }

    public GridPane getMenuGrid() {
        return menuGrid;
    }

    private void setPlayerPickerTextOne() {
        playerPickerTextOne++;
        if(playerPickerTextOne == 2)
            playerPickerTextOne = 0;
    }

    private void setPlayerPickerTextTwo() {
        playerPickerTextTwo++;
        if(playerPickerTextTwo == 3)
            playerPickerTextTwo = 0;
    }

    private void prepareMainMenu(){
        // Making background
        Rectangle menuBackground = new Rectangle(0,0,893,842);
        menuBackground.setFill(Color.WHITE);
        menuBackground.setStroke(Color.BLACK);
        menuBackground.setStrokeWidth(5);

        // Monopoly BANNER
        Image banner = new Image("file:resources/banner.png");
        bannerView.setImage(banner);
        bannerView.setDisable(true);

        // Author
        Image authorImage = new Image("file:resources/author.png");
        authorView.setImage(authorImage);
        authorView.setDisable(true);

        // Making PLAY button
        Rectangle playButton = new Rectangle(200,100, Color.WHITE);
        playButton.setStroke(Color.BLACK);
        playButton.setOnMouseEntered(e -> playButton.setFill(Color.YELLOW));
        playButton.setOnMouseExited(e -> playButton.setFill(Color.WHITE));
        Text playButtonText = new Text("PLAY");
        playButtonText.setFont(new Font(20));
        playButtonText.setDisable(true);
        StackPane playButtonLayout = new StackPane(playButton, playButtonText);

        // Vertical list of buttons
        VBox menuLayout = new VBox(bannerView, playButtonLayout, authorView);
        VBox.setMargin(bannerView, new Insets(0,0,100,150));
        VBox.setMargin(authorView, new Insets(250,0,0,600));

        //--------------------------------------------------------------------------------

        ArrayList<String> secondPlayerButtonStringList = new ArrayList<>();
        secondPlayerButtonStringList.add("Human");
        secondPlayerButtonStringList.add("AI");

        ArrayList<String> otherPlayersButtonStringList = new ArrayList<>();
        otherPlayersButtonStringList.add("Human");
        otherPlayersButtonStringList.add("AI");
        otherPlayersButtonStringList.add("noone");

        redPlayerButton.setTextFill(Color.RED);
        redPlayerButton.setPrefSize(100,50);
        redPlayerButton.setFont(new Font(15));
        redPlayerButton.setDisable(true);

        bluePlayerButton.setTextFill(Color.BLUE);
        bluePlayerButton.setPrefSize(100,50);
        bluePlayerButton.setFont(new Font(15));
        bluePlayerButton.setOnMouseClicked(e -> {
            bluePlayerButton.setText(secondPlayerButtonStringList.get(playerPickerTextOne));
            setPlayerPickerTextOne();
        });


        greenPlayerButton.setTextFill(Color.GREEN);
        greenPlayerButton.setPrefSize(100,50);
        greenPlayerButton.setFont(new Font(15));
        greenPlayerButton.setOnMouseClicked(e -> {
            greenPlayerButton.setText(otherPlayersButtonStringList.get(playerPickerTextTwo));
            setPlayerPickerTextTwo();
        });


        yellowPlayerButton.setTextFill(Color.YELLOW);
        yellowPlayerButton.setPrefSize(100,50);
        yellowPlayerButton.setFont(new Font(15));
        yellowPlayerButton.setOnMouseClicked(e -> {
            yellowPlayerButton.setText(otherPlayersButtonStringList.get(playerPickerTextTwo));
            setPlayerPickerTextTwo();
        });



        HBox playersPickerLayout = new HBox(redPlayerButton, bluePlayerButton, greenPlayerButton, yellowPlayerButton);
        HBox.setMargin(redPlayerButton, new Insets(0,10,0,0));
        HBox.setMargin(bluePlayerButton, new Insets(0,10,0,0));
        HBox.setMargin(greenPlayerButton, new Insets(0,10,0,0));
        HBox.setMargin(yellowPlayerButton, new Insets(0,0,0,0));

        startButton.setPrefSize(200,100);
        startButton.setFont(new Font(30));
        VBox vbox = new VBox(playersPickerLayout, startButton);
        vbox.setVisible(false);
        VBox.setMargin(startButton, new Insets(10,0,0,350));
        VBox.setMargin(playersPickerLayout, new Insets(0,0,0,250));


        menuGrid.getChildren().addAll(menuBackground, menuLayout, vbox);

        GridPane.setMargin(menuLayout, new Insets(100,0,0,0));
        GridPane.setMargin(vbox, new Insets(300,0,0,0));

        playButtonLayout.setOnMouseClicked(e -> {
            playButtonLayout.setVisible(false);

            vbox.setVisible(true);
        });

    }

    public void setThingsOnStartButtonClicked(){
        menuGrid.setVisible(false);
        menuGrid.setDisable(true);
        bannerView.setVisible(false);
        authorView.setVisible(false);
    }

    public void setPlayerRedCashRectangleStrokeColorRED() {
        this.playerRedCashRectangle.setStroke(Color.RED);
    }

    public void setPlayerBlueCashRectangleStrokeColorRED() {
        this.playerBlueCashRectangle.setStroke(Color.RED);
    }

    public void setPlayerGreenCashRectangleStrokeColorRED() {
        this.playerGreenCashRectangle.setStroke(Color.RED);
    }

    public void setPlayerYellowCashRectangleStrokeColorRED() {
        this.playerYellowCashRectangle.setStroke(Color.RED);
    }

    public void setPlayerRedCashRectangleStrokeColorBLACK() {
        this.playerRedCashRectangle.setStroke(Color.BLACK);
    }

    public void setPlayerBlueCashRectangleStrokeColorBLACK() {
        this.playerBlueCashRectangle.setStroke(Color.BLACK);
    }

    public void setPlayerGreenCashRectangleStrokeColorBLACK() {
        this.playerGreenCashRectangle.setStroke(Color.BLACK);
    }

    public void setPlayerYellowCashRectangleStrokeColorBLACK() {
        this.playerYellowCashRectangle.setStroke(Color.BLACK);
    }


    private void prepareBuyCardLayout(){


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

    private void prepareTableContent(){
        //==============================================================================================================
            // CREATING MENU BUTTONS + TABLE SHAPE:
        //==============================================================================================================
        // Creating buttons shape
        Rectangle menuButton = new Rectangle(165,50, Color.WHEAT);
            menuButton.setStroke(Color.BLACK);
        Rectangle menuButton2 = new Rectangle(165,50, Color.WHITE);
            menuButton2.setStroke(Color.BLACK);
        Rectangle menuButton3 = new Rectangle(165,50, Color.WHITE);
            menuButton3.setStroke(Color.BLACK);

        // Creating buttons text
        Text firstOptionText = new Text("Process");
        Text secondOptionText = new Text("Trade");
        Text thirdOptionText = new Text("Actions");

        //// Creating table shape
        Rectangle rectangleTable = new Rectangle(497, 304, Color.WHITE);
            rectangleTable.setStroke(Color.BLACK);

        // Putting buttons and texts as one object
        StackPane firstOptionLayout = new StackPane(menuButton, firstOptionText);
        StackPane secondOptionLayout = new StackPane(menuButton2, secondOptionText);
        StackPane thirdOptionLayout = new StackPane(menuButton3, thirdOptionText);

        // Setting ^StackPane "buttons" horizontally
        HBox tableMenuLayout = new HBox(firstOptionLayout, secondOptionLayout, thirdOptionLayout);
        //==============================================================================================================
            // CREATING ACTION BUTTONS:
        //==============================================================================================================
        // Creating rectangles shapes
        actionButton1.setStroke(Color.BLACK);
        actionButton2.setStroke(Color.BLACK);
        actionButton3.setStroke(Color.BLACK);
        actionButton4.setStroke(Color.BLACK);

        // Creating text for action buttons
        Text actionText1 = new Text("give away on pledge");
            actionText1.setDisable(true);
        Text actionText2 = new Text("purchase from pledge");
            actionText2.setDisable(true);
        Text actionText3 = new Text("buy a building");
            actionText3.setDisable(true);
        Text actionText4 = new Text("sell a building");
            actionText4.setDisable(true);

        // Putting buttons and text as one object
        StackPane actionButtonLayout1 = new StackPane(actionButton1, actionText1);
        StackPane actionButtonLayout2 = new StackPane(actionButton2, actionText2);
        StackPane actionButtonLayout3 = new StackPane(actionButton3, actionText3);
        StackPane actionButtonLayout4 = new StackPane(actionButton4, actionText4);

        // Setting size of that objects ^
        actionButtonLayout1.setMaxSize(200,50);
        actionButtonLayout2.setMaxSize(200,50);
        actionButtonLayout3.setMaxSize(200,50);
        actionButtonLayout4.setMaxSize(200,50);

        // Trade buttons at work
        actionButton1.setOnMouseClicked(e -> {
            actionButton1.setFill(Color.YELLOW);
            actionButton2.setFill(Color.WHITE);
            actionButton3.setFill(Color.WHITE);
            actionButton4.setFill(Color.WHITE);
        });

        actionButton2.setOnMouseClicked(e -> {
            actionButton1.setFill(Color.WHITE);
            actionButton2.setFill(Color.YELLOW);
            actionButton3.setFill(Color.WHITE);
            actionButton4.setFill(Color.WHITE);
        });

        actionButton3.setOnMouseClicked(e -> {
            actionButton1.setFill(Color.WHITE);
            actionButton2.setFill(Color.WHITE);
            actionButton3.setFill(Color.YELLOW);
            actionButton4.setFill(Color.WHITE);
        });

        actionButton4.setOnMouseClicked(e -> {
            actionButton1.setFill(Color.WHITE);
            actionButton2.setFill(Color.WHITE);
            actionButton3.setFill(Color.WHITE);
            actionButton4.setFill(Color.YELLOW);
        });

        //==============================================================================================================
            // CREATING BOOKMARKS:
        //==============================================================================================================

        VBox processContentLayout = new VBox(gameplayInfo[0],gameplayInfo[1], gameplayInfo[2], gameplayInfo[3], gameplayInfo[4], gameplayInfo[5], gameplayInfo[6],
                gameplayInfo[7], gameplayInfo[8], gameplayInfo[9]); //PROCESS CONTENT
        GridPane tradeContentLayout = new GridPane(); //TRADE CONTENT
        VBox actionsContentLayout = new VBox(actionButtonLayout1, actionButtonLayout2, actionButtonLayout3, actionButtonLayout4); //ACTIONS CONTENT

        // Changing margins of action buttons
        VBox.setMargin(actionButtonLayout1, new Insets(30,0,1,148));
        VBox.setMargin(actionButtonLayout2, new Insets(10,0,1,148));
        VBox.setMargin(actionButtonLayout3, new Insets(10,0,1,148));
        VBox.setMargin(actionButtonLayout4, new Insets(10,0,1,148));

        // Putting content from bookmarks into one StackPane to set all content in the same place - just correct visibility
        StackPane tableLayout = new StackPane(rectangleTable, processContentLayout, actionsContentLayout, tradeContentLayout);

        // Modyfing appereance of text
        StackPane.setMargin(processContentLayout, new Insets(1,1,1,10)); // left192

        //==============================================================================================================
        // // Menu buttons at work:
        //==============================================================================================================
        firstOptionLayout.setOnMouseClicked(e -> {
            menuButton.setFill(Color.WHEAT);
            menuButton2.setFill(Color.WHITE);
            menuButton3.setFill(Color.WHITE);
            actionButton1.setFill(Color.WHITE);
            actionButton2.setFill(Color.WHITE);
            actionButton3.setFill(Color.WHITE);
            actionButton4.setFill(Color.WHITE);

            actionsContentLayout.setVisible(false);
            tradeContentLayout.setVisible(false);
            processContentLayout.setVisible(true);
        });

        secondOptionLayout.setOnMouseClicked(e -> {
            menuButton.setFill(Color.WHITE);
            menuButton2.setFill(Color.WHEAT);
            menuButton3.setFill(Color.WHITE);
            actionButton1.setFill(Color.WHITE);
            actionButton2.setFill(Color.WHITE);
            actionButton3.setFill(Color.WHITE);
            actionButton4.setFill(Color.WHITE);

            processContentLayout.setVisible(false);
            actionsContentLayout.setVisible(false);
            tradeContentLayout.setVisible(true);
        });

        thirdOptionLayout.setOnMouseClicked(e -> {
            menuButton.setFill(Color.WHITE);
            menuButton2.setFill(Color.WHITE);
            menuButton3.setFill(Color.WHEAT);

            processContentLayout.setVisible(false);
            tradeContentLayout.setVisible(false);
            actionsContentLayout.setVisible(true);

            actionButtonLayout1.setVisible(true);
            actionButtonLayout2.setVisible(true);
            actionButtonLayout3.setVisible(true);
            actionButtonLayout4.setVisible(true);
        });

        // Setting visibility to false when starting game
        actionButtonLayout1.setVisible(false);
        actionButtonLayout2.setVisible(false);
        actionButtonLayout3.setVisible(false);
        actionButtonLayout4.setVisible(false);

        // Table and menu buttons are independently added
        VBox tableAndMenu = new VBox(tableMenuLayout, tableLayout);

        // Adding entire table to grid to show on Board
        grid.add(tableAndMenu,2,2,6,7);
    }

    private void moveProcessTexts() {

        for (int i = 0, j = 1; i < 10 && j < 10; i++, j++) {
            gameplayInfo[i].setText(gameplayInfo[j].getText());
        }
    }

    public void putInfoToProcess(String text){
        moveProcessTexts();
        gameplayInfo[9].setText(text);
    }

    private void prepareProcessText(){
        for(int i = 0; i<10; i++){
            gameplayInfo[i] = new Text("+***********************");
            gameplayInfo[i].setFont(new Font(20));
        }
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
                        fourBuildingsFee.setVisible(true);
                        fiveBuildingsFee.setVisible(true);
                        costOfBuilding.setVisible(true);
                        colorOfCard.setVisible(true);
                        twoBuildingsFee.setVisible(true);
                        threeBuildingsFee.setVisible(true);

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

    private void preparePlayersLabels(){

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
        GridPane.setMargin(hbox, new Insets(10,1,70,7));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,0,0,30));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(3).getCard();
        hbox = new HBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 7,10);
        GridPane.setMargin(hbox, new Insets(10,1,70,7));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,0,0,30));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(5).getCard();
        hbox = new HBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 5,10);
        GridPane.setMargin(hbox, new Insets(10,1,70,7));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,0,0,30));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(6).getCard();
        hbox = new HBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 4,10);
        GridPane.setMargin(hbox, new Insets(10,1,70,7));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,0,0,30));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(8).getCard();
        hbox = new HBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 2,10);
        GridPane.setMargin(hbox, new Insets(10,1,70,7));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,0,0,30));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(9).getCard();
        hbox = new HBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(hbox, 1,10);
        GridPane.setMargin(hbox, new Insets(10,1,70,7));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,0,0,30));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(11).getCard();
        VBox vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,9);
        GridPane.setMargin(vbox, new Insets(10,0,0,106));
        VBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(12).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,8);
        GridPane.setMargin(vbox, new Insets(10,0,0,106));
        VBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(13).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,7);
        GridPane.setMargin(vbox, new Insets(10,0,0,106));
        VBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(14).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,6);
        GridPane.setMargin(vbox, new Insets(10,0,0,106));
        VBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(15).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,5);
        GridPane.setMargin(vbox, new Insets(10,0,0,106));
        VBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(16).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,4);
        GridPane.setMargin(vbox, new Insets(10,0,0,106));
        VBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(18).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,2);
        GridPane.setMargin(vbox, new Insets(10,0,0,106));
        VBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(19).getCard();
        vbox = new VBox(temporaryBuyableCard.getBelongsIndicator(), temporaryBuyableCard.getPledgeAndBuildingsIndicator());
        grid.add(vbox, 0,1);
        GridPane.setMargin(vbox, new Insets(10,0,0,106));
        VBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(21).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 1,0);
        GridPane.setMargin(hbox, new Insets(80,0,0,5));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,30,0,0));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(23).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 3,0);
        GridPane.setMargin(hbox, new Insets(80,0,0,5));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,30,0,0));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(24).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 4,0);
        GridPane.setMargin(hbox, new Insets(80,0,0,5));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,30,0,0));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(25).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 5,0);
        GridPane.setMargin(hbox, new Insets(80,0,0,5));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,30,0,0));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(26).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 6,0);
        GridPane.setMargin(hbox, new Insets(80,0,0,5));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,30,0,0));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(27).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 7,0);
        GridPane.setMargin(hbox, new Insets(80,0,0,5));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,30,0,0));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(28).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 8,0);
        GridPane.setMargin(hbox, new Insets(80,0,0,5));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,30,0,0));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(29).getCard();
        hbox = new HBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(hbox, 9,0);
        GridPane.setMargin(hbox, new Insets(80,0,0,5));
        HBox.setMargin(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), new Insets(0,30,0,0));
        hbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);



        temporaryBuyableCard = (BuyableCard) fieldsArray.get(31).getCard();
        vbox = new VBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(vbox, 10,1);
        GridPane.setMargin(vbox, new Insets(5,0,0,10));
        VBox.setMargin(temporaryBuyableCard.getBelongsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(32).getCard();
        vbox = new VBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(vbox, 10,2);
        GridPane.setMargin(vbox, new Insets(5,0,0,10));
        VBox.setMargin(temporaryBuyableCard.getBelongsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(34).getCard();
        vbox = new VBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(vbox, 10,4);
        GridPane.setMargin(vbox, new Insets(5,0,0,10));
        VBox.setMargin(temporaryBuyableCard.getBelongsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(35).getCard();
        vbox = new VBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(vbox, 10,5);
        GridPane.setMargin(vbox, new Insets(5,0,0,10));
        VBox.setMargin(temporaryBuyableCard.getBelongsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(37).getCard();
        vbox = new VBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(vbox, 10,7);
        GridPane.setMargin(vbox, new Insets(5,0,0,10));
        VBox.setMargin(temporaryBuyableCard.getBelongsIndicator(), new Insets(30,0,0,0));
        vbox.setDisable(true);
        temporaryBuyableCard.getBelongsIndicator().setVisible(false);
        temporaryBuyableCard.getPledgeAndBuildingsIndicator().setVisible(false);

        temporaryBuyableCard = (BuyableCard) fieldsArray.get(39).getCard();
        vbox = new VBox(temporaryBuyableCard.getPledgeAndBuildingsIndicator(), temporaryBuyableCard.getBelongsIndicator());
        grid.add(vbox, 10,9);
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

    public StackPane getBuyCardContentLayout() {
        return buyCardContentLayout;
    }

    public Button getBuyCardYesButton() {
        return buyCardYesButton;
    }

    public Button getBuyCardNoButton() {
        return buyCardNoButton;
    }

    public Rectangle getActionButton1() {
        return actionButton1;
    }

    public Rectangle getActionButton2() {
        return actionButton2;
    }

    public Rectangle getActionButton3() {
        return actionButton3;
    }

    public Rectangle getActionButton4() {
        return actionButton4;
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
}
