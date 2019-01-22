package com.kodilla.game;

import com.kodilla.game.board.Board;
import com.kodilla.game.board.BoardField;
import com.kodilla.game.cards.CityCard;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.*;

import java.util.HashMap;

public class Main extends Application {

    private Image imageback = new Image("file:resources/board.png");

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ImageView imageView = new ImageView(imageback);

        //-------------------------------------------------------------------------------------
        Group root = new Group();
        GridPane grid = Board.getGrid();
        grid.setGridLinesVisible(true);


/*
      StackPane cardInfo = new StackPane();
        Text fieldName = new Text();
        Text fieldCost = new Text();
        Text zeroBuildingsFee = new Text();

        cardInfo.getChildren().addAll(new Rectangle(100,100, Color.BLUE), fieldName, fieldCost, zeroBuildingsFee);
        grid.add(cardInfo,7,2);

        rec1.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Card card = Board.getBoard().get(1).getCard();
                CityCard cityCard = (CityCard) card;
                fieldName.setText(cityCard.getFieldName());
                fieldCost.setText(Integer.toString(cityCard.getFieldCost()));
                zeroBuildingsFee.setText(Integer.toString(cityCard.getZeroBuildingsFee()));
            }
        });

        */

        HashMap<Integer, BoardField> map = new HashMap<>(Board.getBoard());
        Board.showFieldInfo();


        GameControl gameControl = new GameControl();
        gameControl.gameFlow();

        //-------------------------------------------------------------------------------------

        root.getChildren().add(imageView);
        root.getChildren().add(grid);
        root.getChildren().add(gameControl.getRed().getPawn());
        root.getChildren().add(gameControl.getBlue().getPawn());

        Scene scene = new Scene(root, 898, 847);

        primaryStage.setTitle("Monopoly");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
