package com.kodilla.game.board.boardContent;

import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.buyableCards.CircleCard;
import com.kodilla.game.cards.buyableCards.CityCard;
import com.kodilla.game.cards.buyableCards.TriangleCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.HashMap;
import java.util.Map;

public class FieldInfo {
    public void showFieldInfo(GridPane grid, HashMap<Integer, BoardField> fieldsArray){

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
}
