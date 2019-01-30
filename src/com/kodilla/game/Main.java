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
        *  pole wiezienia
        *
        *  POPRAW TURY GRACZY I WYDZIEL NA AI I KOMPA
        *  - zmienna boolean isYourTurn if true -> do
        *  - if Player instanceof human -> buttons else if AI -> random
        *  - być może graczy w jeden klawisz i sprawdzanie kogo kolej booleanem wyzej, ale co dalej z AI?
        *
        *
        * stałe-red,blue?, mniej ogolnych zmiennch
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
