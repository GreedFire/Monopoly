package com.kodilla.game;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.*;

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
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

        ColumnConstraints col1 = new ColumnConstraints(130);
        ColumnConstraints col2 = new ColumnConstraints(71);

        RowConstraints row1 = new RowConstraints(104);
        RowConstraints row2 = new RowConstraints(71);

        grid.getColumnConstraints().addAll(col1, col2, col2, col2, col2, col2, col2, col2, col2, col2, col1);
        grid.getRowConstraints().addAll(row1, row2, row2, row2, row2, row2, row2, row2, row2, row2, row1 );

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
