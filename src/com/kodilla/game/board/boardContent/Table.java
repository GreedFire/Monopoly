package com.kodilla.game.board.boardContent;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Table {

    private int playerTradeCash = 0;
    private int enemyTradeCash = 0;
    private Text playerTradeCashText = new Text("Cash: " + playerTradeCash);
    private Text enemyTradeCashText = new Text("Cash: " + enemyTradeCash);
    private Text playerTradeCard = new Text();
    private Text enemyTradeCard = new Text();
    private Button reset = new Button("Reset");
    private Button trade = new Button("Trade");
    private Rectangle menuButton2 = new Rectangle(165,50, Color.WHITE);
    private Rectangle actionButton1 = new Rectangle(200,50, Color.WHITE);
    private Rectangle actionButton2 = new Rectangle(200,50, Color.WHITE);
    private Rectangle actionButton3 = new Rectangle(200,50, Color.WHITE);
    private Rectangle actionButton4 = new Rectangle(200,50, Color.WHITE);
    private Text[] gameplayInfo = new Text[10];

    public int getPlayerTradeCash() {
        return playerTradeCash;
    }

    public int getEnemyTradeCash() {
        return enemyTradeCash;
    }

    public Button getReset() {
        return reset;
    }

    public Text getPlayerTradeCard() {
        return playerTradeCard;
    }

    public Text getEnemyTradeCard() {
        return enemyTradeCard;
    }

    public Rectangle getMenuButton2() {
        return menuButton2;
    }

    public Button getTrade() {
        return trade;
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

    private void moveProcessTexts() {

        for (int i = 0, j = 1; i < 10 && j < 10; i++, j++) {
            gameplayInfo[i].setText(gameplayInfo[j].getText());
        }
    }

    public void putInfoToProcess(String text){
        moveProcessTexts();
        gameplayInfo[9].setText(text);
    }

    public void prepareProcessText(){
        for(int i = 0; i<10; i++){
            gameplayInfo[i] = new Text("+***********************");
            gameplayInfo[i].setFont(new Font(20));
        }
    }

    public void prepareTableContent(GridPane grid){
        //==============================================================================================================
        // CREATING MENU BUTTONS + TABLE SHAPE:
        //==============================================================================================================
        // Creating buttons shape
        Rectangle menuButton = new Rectangle(165,50, Color.WHEAT);
        menuButton.setStroke(Color.BLACK);
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

        //=====================================================================================
        //=====================================================================================
        //=====================================================================================

        //TRADE
        GridPane tradeContentLayout = new GridPane(); //TRADE CONTENT
        tradeContentLayout.setVisible(false);

        playerTradeCashText.setFont(new Font(15));
        enemyTradeCashText.setFont(new Font(15));

        Button playerCashButton10 = new Button("+10");
        Button playerCashButton50 = new Button("+50");
        Button playerCashButton100 = new Button("+100");
        HBox playerCashButtonsHBox = new HBox(playerCashButton10, playerCashButton50, playerCashButton100, playerTradeCashText);
        playerCashButtonsHBox.setPrefWidth(210);

        Button enemyCashButton10 = new Button("+10");
        Button enemyCashButton50 = new Button("+50");
        Button enemyCashButton100 = new Button("+100");
        HBox enemyCashButtonsHBox = new HBox(enemyCashButton10, enemyCashButton50, enemyCashButton100, enemyTradeCashText);


        Button yes = new Button("yes");
        Button no = new Button("no");

        VBox playerTradeContent = new VBox(playerCashButtonsHBox, playerTradeCard);
        VBox enemyTradeContent = new VBox(enemyCashButtonsHBox, enemyTradeCard);

        Line line = new Line(50,0,50,200);
        HBox horizontalTable = new HBox(playerTradeContent, line, enemyTradeContent);
        HBox tradeAndReset = new HBox(trade, reset);
        HBox yesAndNo = new HBox(yes, no);
        VBox allTradeContent = new VBox(horizontalTable, tradeAndReset, yesAndNo);

        GridPane.setMargin(allTradeContent, new Insets(20,0,0,20));
        HBox.setMargin(line, new Insets(0,25,0,25));
        VBox.setMargin(tradeAndReset, new Insets(0,0,0,183));
        setMarginForCashButtons(trade, playerCashButton10, playerCashButton50);
        setMarginForCashButtons(playerCashButton100, enemyCashButton10, enemyCashButton50);
        HBox.setMargin(enemyCashButton100, new Insets(0,5,0,0));

        playerCashButton10.setOnMouseClicked(e -> {
            addCashToPlayerTradeCash(10);
            updateTradeCashTexts();
        });

        playerCashButton50.setOnMouseClicked(e -> {
            addCashToPlayerTradeCash(50);
            updateTradeCashTexts();
        });

        playerCashButton100.setOnMouseClicked(e -> {
            addCashToPlayerTradeCash(100);
            updateTradeCashTexts();
        });

        enemyCashButton10.setOnMouseClicked(e -> {
            addCashToEnemyTradeCash(10);
            updateTradeCashTexts();
        });

        enemyCashButton50.setOnMouseClicked(e -> {
            addCashToEnemyTradeCash(50);
            updateTradeCashTexts();
        });

        enemyCashButton100.setOnMouseClicked(e -> {
            addCashToEnemyTradeCash(100);
            updateTradeCashTexts();
        });

        tradeContentLayout.getChildren().addAll(allTradeContent);


        //=====================================================================================
        //=====================================================================================
        //=====================================================================================
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
            turnOffActionButtons(menuButton, menuButton2, menuButton3, actionsContentLayout, processContentLayout, tradeContentLayout);
            menuButton.setFill(Color.WHEAT);
        });

        secondOptionLayout.setOnMouseClicked(e -> {
            turnOffActionButtons(menuButton, menuButton2, menuButton3, actionsContentLayout, processContentLayout, tradeContentLayout);
            menuButton2.setFill(Color.WHEAT);
            processContentLayout.setVisible(false);
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
        grid.add(tableAndMenu,2,2,6,7);
    }

    private void setMarginForCashButtons(Button playerCashButton100, Button enemyCashButton10, Button enemyCashButton50) {
        HBox.setMargin(playerCashButton100, new Insets(0,5,0,0));
        HBox.setMargin(enemyCashButton10, new Insets(0,5,0,0));
        HBox.setMargin(enemyCashButton50, new Insets(0,5,0,0));
    }

    private void turnOffActionButtons(Rectangle button1, Rectangle button2, Rectangle button3, VBox vbox1, VBox vbox2, GridPane gridPane ){
        button1.setFill(Color.WHITE);
        button2.setFill(Color.WHITE);
        button3.setFill(Color.WHITE);
        actionButton1.setFill(Color.WHITE);
        actionButton2.setFill(Color.WHITE);
        actionButton3.setFill(Color.WHITE);
        actionButton4.setFill(Color.WHITE);

        vbox1.setVisible(false);
        gridPane.setVisible(false);
        vbox2.setVisible(true);

    }

    private void addCashToPlayerTradeCash(int add){
        playerTradeCash += add;
    }

    private void addCashToEnemyTradeCash(int add){
        enemyTradeCash += add;
    }

    public void resetCash(){
        playerTradeCash = 0;
        enemyTradeCash = 0;
    }

    public void updateTradeCashTexts(){
        playerTradeCashText.setText("Cash: " + playerTradeCash);
        enemyTradeCashText.setText("Cash: " + enemyTradeCash);
    }
}
