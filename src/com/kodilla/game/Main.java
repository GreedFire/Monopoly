package com.kodilla.game;

import com.kodilla.game.board.Board;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.*;

public class Main extends Application {

    private Image imageback = new Image("file:resources/board.png");
    private Image dice1 = new Image("file:resources/dice1.png");
    private Image dice2 = new Image("file:resources/dice2.png");
    private Image dice3 = new Image("file:resources/dice3.png");
    private Image dice4 = new Image("file:resources/dice4.png");
    private Image dice5 = new Image("file:resources/dice5.png");
    private Image dice6 = new Image("file:resources/dice6.png");

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /*
        * Popraw zeby board był raczej konstruktorem i w gamecontrol i gamecontrol wszystko wyswietlalo.
        * Napisz guzik losujący kostke,
        * Napisz wyswietlanie graczy i ich hajsu
        * Napisz kolorowy trojkącik nad graczem wyswietlajacy kogo jest kolej
        *
         */

        ImageView imageView = new ImageView(imageback);
        ImageView dice = new ImageView(dice1);

        //-------------------------------------------------------------------------------------
        Group root = new Group();
        GridPane grid = Board.getGrid();
        grid.setGridLinesVisible(true);

        grid.add(dice, 9,9);


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
