package com.kodilla.game;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.*;

public class Main extends Application {

    private Image imageback = new Image("file:resources/board.png");


    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /*
        * Napisz żeby wyswietlalo ruch gracza w tabelce(napisy) (może uzyj tablicy i pętli)
        *
        * Napisz miejsce kolorowego kwadracika posiadania karty
        *
        * Napisz resztę kart
        * Napisz kupowanie karty
        * (chyba wystarczy podawać grid index i nie trzeba robić rectanglów)
        *
        */
        GameControl gameControl = new GameControl();
        ImageView imageView = new ImageView(imageback);

        //-------------------------------------------------------------------------------------
        Group root = new Group();

        GridPane grid = gameControl.getBoard().getGrid();
        grid.setGridLinesVisible(true);

        gameControl.showInfo();

        gameControl.gameFlow();

        //-------------------------------------------------------------------------------------

        root.getChildren().add(imageView);
        root.getChildren().add(grid);
        root.getChildren().add(gameControl.getRed().getPawn());
        root.getChildren().add(gameControl.getRed().getPawnAfterImage());
        root.getChildren().add(gameControl.getBlue().getPawn());
        root.getChildren().add(gameControl.getBlue().getPawnAfterImage());


        Scene scene = new Scene(root, 898, 847);

        primaryStage.setTitle("Monopoly");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
