package com.kodilla.game.board;

import com.kodilla.game.cards.CityCard;
import com.kodilla.game.player.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private HashMap<Integer, BoardField> fieldsArray = new HashMap<>();
    private GridPane grid = new GridPane();
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


    public Board(){
        fieldsArray.put(0, new BoardField("Nothing", 881, 795, 881, 825, 128, 102));

        fieldsArray.put(1, new BoardField("Card", 755, 795, 755, 825, 72, 102, new CityCard("City Card", "field #1", 1, 2, 60,2, 10, 30, 90, 160, 250, 50, "gray")));
        fieldsArray.put(2, new BoardField("Nothing", 683, 795, 683 ,825, 72, 102));
        fieldsArray.put(3, new BoardField("Card", 612, 795, 612, 825,72, 102, new CityCard("City Card", "field #3", 1, 2, 60, 4, 20, 60, 180, 320, 450, 50, "gray")));
        fieldsArray.put(4, new BoardField("Nothing", 541,795,541,825,72,102));
        fieldsArray.put(5, new BoardField("Nothing", 469, 795, 469, 825,72,102));
        fieldsArray.put(6, new BoardField("Card", 398, 795, 398,825,72,102 ,new CityCard("City Card", "field #6", 2, 3, 100,6,30,90,270,400,550,50, "teal")));
        fieldsArray.put(7, new BoardField("Nothing", 327,795,327,825,72, 102));
        fieldsArray.put(8, new BoardField("Card", 255,795,255,825,72,102 ,new CityCard("City Card", "field #8", 2, 3, 100, 6,30, 90, 270, 400, 550, 50, "teal")));
        fieldsArray.put(9, new BoardField("Card", 184, 795, 184 ,825,72,102 ,new CityCard("City Card", "field #9", 2, 3, 120, 8, 40, 100, 300, 450, 600, 50, "teal")));

        fieldsArray.put(10, new BoardField("Nothing", 113,795,113,825,128,102));

        fieldsArray.put(11, new BoardField("Card", 77,729,47,729, 128,72 ,new CityCard("City Card", "field 11", 3, 3,140,10,50,150,450,625,750,100, "purple")));
        fieldsArray.put(12, new BoardField("Nothing", 77,657,47,657,128,72));
        fieldsArray.put(13, new BoardField("Card", 77,585,47,585,128,72, new CityCard("City Card", "field #13", 3, 3, 140, 10,50,150,450,625,750,100, "purple")));
        fieldsArray.put(14, new BoardField("Card",77,513,47,813,128,72 ,new CityCard("City Card", "field #14", 3,3,160,12,60,180,500,700,900,100, "purple")));
        fieldsArray.put(15, new BoardField("Nothing",77,441,47,441,128,72));
        fieldsArray.put(16, new BoardField("Card", 77,369,47,369,128,72 ,new CityCard("City Card", "field #16", 4, 3, 180,14,70,200,550,750,950,100, "orange")));
        fieldsArray.put(17, new BoardField("Nothing", 77,297,47,297,128,72));
        fieldsArray.put(18, new BoardField("Card", 77,225,47,225,128,72 ,new CityCard("City Card", "field #18", 4, 3, 180,14,70,200,550,750,950,100, "orange")));
        fieldsArray.put(19, new BoardField("Card", 77,153,47,153,128,72 ,new CityCard("City Card", "field #19", 4, 3, 200,16,80,220,600,800,1000,100, "orange")));

        fieldsArray.put(20, new BoardField("Nothing", 77,81,47,81,128,102));

        fieldsArray.put(21, new BoardField("Card", 143,51,143,21,72,102, new CityCard("City Card,", "field #21", 5, 3,220,18,90,250,700,875,1050,150, "red")));
        fieldsArray.put(22, new BoardField("Nothing", 215,51,215,21,72,102));
        fieldsArray.put(23, new BoardField("Card", 287,51,287,21,72,102 ,new CityCard("City Card", "field #23", 5, 3, 220,18,90,250,700,875,1050,150, "red")));
        fieldsArray.put(24, new BoardField("Card", 359,51,359,21, 72,102,new CityCard("City Card", "field #24", 5, 3,240,20,100,300,750,925,1100,150, "red")));
        fieldsArray.put(25, new BoardField("Nothing", 431,51,431,21,72,102));
        fieldsArray.put(26, new BoardField("Card", 503,51,503,21, 72,102,new CityCard("City Card", "field #26", 6, 3,260,22,110,330,800,975,1150,150, "yellow")));
        fieldsArray.put(27, new BoardField("Card", 575,51,575,21,72,102, new CityCard("City Card", "field #27", 6,3,260,22,110,330,800,975,1150, 150, "yellow")));
        fieldsArray.put(28, new BoardField("Nothing", 647,51,647,21,72,102));
        fieldsArray.put(29, new BoardField("Card", 719,51,719,21, 72,102,new CityCard("City Card", "field #28", 6,3,280,24,120,360,850,1025,1200,150, "yellow")));

        fieldsArray.put(30, new BoardField("Nothing", 791,51,791,21,128,102));

        fieldsArray.put(31, new BoardField("Card", 821,117,851,117,128,72, new CityCard("City Card", "field #31", 7,3,300,26,130,390,900,1100,1275,200, "green")));
        fieldsArray.put(32, new BoardField("Card", 821,189,851,189,128,72 , new CityCard("City Card", "field #32", 7, 3, 300,26,130,390,900,1100,1275,200, "green")));
        fieldsArray.put(33, new BoardField("Nothing", 821,261,851,261,128,72));
        fieldsArray.put(34, new BoardField("Card", 821,333,851,333, 128,72, new CityCard("City Card", "field #34", 7,3,320,28,150,450,1000,1200,1400,200, "green" )));
        fieldsArray.put(35, new BoardField("Nothing", 821,405,851,405,128,72));
        fieldsArray.put(36, new BoardField("Nothing", 821,477,851,477,128,72));
        fieldsArray.put(37, new BoardField("Card", 821,549,851,549, 128,72, new CityCard("City Card", "field #37", 8, 2,350,35,175,500,1100,1300,1500,200, "blue")));
        fieldsArray.put(38, new BoardField("Nothing", 821,621,851,621,128,72));
        fieldsArray.put(39, new BoardField("Card", 821,693,851,693, 128,72, new CityCard("City Card", "field #39", 8,2,400,50,200,600,1400,1700,2000,200, "blue")));

        ColumnConstraints col1 = new ColumnConstraints(130);
        ColumnConstraints col2 = new ColumnConstraints(71);
        RowConstraints row1 = new RowConstraints(104);
        RowConstraints row2 = new RowConstraints(71);

        grid.getColumnConstraints().addAll(col1, col2, col2, col2, col2, col2, col2, col2, col2, col2, col1);
        grid.getRowConstraints().addAll(row1, row2, row2, row2, row2, row2, row2, row2, row2, row2, row1);

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



        VBox diceLayout = new VBox();
        diceLayout.getChildren().addAll(endTurnBtn ,diceRollBtn, firstDiceShow, secondDiceShow);
        grid.add(diceLayout, 9,8);
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
        Rectangle colorOfCard = new Rectangle(150,40, Color.PINK);
        colorOfCard.setArcHeight(30);
        colorOfCard.setArcWidth(30);

        // Creating card figure
        Rectangle cardBody = new Rectangle(160,200, Color.WHITE);
        cardBody.setStroke(Color.BLACK);
        cardBody.setStrokeWidth(1.5);
        cardBody.setArcHeight(30);
        cardBody.setArcWidth(30);

        // Positioning nodes
        entireCardLayout.getChildren().addAll(cardBody, cardInfoLayout);
        fieldColorAndInfoLayout.getChildren().addAll(colorOfCard, infoInsideColor);
        infoInsideColor.getChildren().addAll(fieldName, fieldCost);
        cardInfoLayout.getChildren().addAll(fieldColorAndInfoLayout, zeroBuildingsFee, oneBuildingFee, twoBuildingsFee, threeBuildingsFee, fourBuildingsFee, fiveBuildingsFee, costOfBuilding);

        // Adding card layout to grid
        grid.add(entireCardLayout,7,2);

        // Loop for access entire HashMap to get to hidden rectangles to show card info
        // and get access to cards
        for(Map.Entry<Integer, BoardField> entry : fieldsArray.entrySet()) {
            entry.getValue().getRectangle().setOnMouseEntered(e -> {

                   // "Checking what type of card there is"
                    if(entry.getValue().getCard() instanceof CityCard){
                        CityCard tempCard = (CityCard) entry.getValue().getCard();

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
                    }
                    else
                    {

                    }
                    entireCardLayout.setVisible(true);
            });

            // Hidding card info when exiting rectangle
            entry.getValue().getRectangle().setOnMouseExited(e -> entireCardLayout.setVisible(false));
        }
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
}
