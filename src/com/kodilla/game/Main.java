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
        * To fix:
        * maybe make Player turns bigger change with collections
        *
        * To do:
        * prison - Player boolean inPrison -> true -> to wielki if zapobiegający turze
        *   lose conditions, main menu, adding players, trade, animations and sounds, which turn indicator(triangle)
        *
        *  Bugs:
        *  //ToDo: hujhgu
        *  sometimes player cant pay fee and probably pays when on pledge - czasami dwa razy trzeba sprzedac piątke wiec pewnie liczba przekracza 5 budynkow i dlatego nie pobiera oplaty
        *
        *
        *
        */
        GameControl gameControl = new GameControl();
        ImageView imageView = new ImageView(imageback);

        //-------------------------------------------------------------------------------------
        Group root = new Group();

        GridPane grid = gameControl.getBoard().getGrid();
        grid.setGridLinesVisible(false);

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
