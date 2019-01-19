package com.kodilla.game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.*;

public class Main extends Application {

    private Image imageback = new Image("file:resources/board.png");

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ImageView imageView = new ImageView(imageback);

        Button button1 = new Button();
        button1.setText("Click me");
        button1.setOnAction(e -> System.out.println("ROFL"));



        Circle blueCircle = new Circle(821, 693, 10, Color.BLUE);
        blueCircle.setStroke(Color.BLACK);

        Player red = new Player(881,795, "Red");

        Circle redCircle = new Circle(red.getPlayerPositionX(), red.getPlayerPositionY(), 10, Color.RED);
        redCircle.setStroke(Color.BLACK);

        red.movePlayer();

        redCircle.setCenterX(red.getPlayerPositionX());
        redCircle.setCenterY(red.getPlayerPositionY());

        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(button1);
        root.getChildren().add(redCircle);
        root.getChildren().add(blueCircle);

        Scene scene = new Scene(root, 898, 847);

        primaryStage.setTitle("Monopoly");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
