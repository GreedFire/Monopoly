package com.kodilla.game;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.*;

public class Main extends Application {

    private final Image imageBack = new Image("file:resources/board.png");

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {


        GameControl gameControl = new GameControl();
        ImageView imageView = new ImageView(imageBack);

        //-------------------------------------------------------------------------------------
        Group root = new Group();

        GridPane grid = gameControl.getBoard().getGrid();
        GridPane menu = gameControl.getBoard().getMainMenu().getMenuGrid();
        grid.setGridLinesVisible(false);

        gameControl.getBoard().getMainMenu().getStartButton().setOnMouseClicked(e -> {

            gameControl.getBoard().getMainMenu().setThingsOnStartButtonClicked();
            gameControl.createPlayers();
            gameControl.gameFlow();

            root.getChildren().add(gameControl.getRed().getPawn());
            root.getChildren().add(gameControl.getRed().getPawnAfterImage());
            root.getChildren().add(gameControl.getBlue().getPawn());
            root.getChildren().add(gameControl.getBlue().getPawnAfterImage());
            root.getChildren().add(gameControl.getGreen().getPawn());
            root.getChildren().add(gameControl.getGreen().getPawnAfterImage());
            root.getChildren().add(gameControl.getYellow().getPawn());
            root.getChildren().add(gameControl.getYellow().getPawnAfterImage());
        });

        //-------------------------------------------------------------------------------------

        root.getChildren().add(imageView);
        root.getChildren().add(grid);

        root.getChildren().add(menu);

        Scene scene = new Scene(root, 898, 847);

        primaryStage.setTitle("Monopoly");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
