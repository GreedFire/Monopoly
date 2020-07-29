package com.kodilla.game.board.boardContent;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class MainMenu {

    private final ImageView bannerView = new ImageView();
    private final ImageView authorView = new ImageView();
    private final Button redPlayerButton = new Button("Human");
    private final Button bluePlayerButton = new Button("AI");
    private final Button greenPlayerButton = new Button("Empty");
    private final Button yellowPlayerButton = new Button("Empty");
    private final Button startButton = new Button("START");
    private int playerPickerTextOne = 0;
    private int playerPickerTextTwo = 0;
    private final GridPane menuGrid = new GridPane();

    private void setPlayerPickerTextOne() {
        playerPickerTextOne++;
        if(playerPickerTextOne == 2)
            playerPickerTextOne = 0;
    }

    private void setPlayerPickerTextTwo() {
        playerPickerTextTwo++;
        if(playerPickerTextTwo == 3)
            playerPickerTextTwo = 0;
    }

    public void setThingsOnStartButtonClicked(){
        menuGrid.setVisible(false);
        menuGrid.setDisable(true);
        bannerView.setVisible(false);
        authorView.setVisible(false);
    }

    public void prepareMainMenu(){
        // Making background
        Rectangle menuBackground = new Rectangle(0,0,893,842);
        menuBackground.setFill(Color.WHITE);
        menuBackground.setStroke(Color.BLACK);
        menuBackground.setStrokeWidth(5);

        // Monopoly BANNER
        Image banner = new Image("file:resources/banner.png");
        bannerView.setImage(banner);
        bannerView.setDisable(true);

        // Author
        Image authorImage = new Image("file:resources/author.png");
        authorView.setImage(authorImage);
        authorView.setDisable(true);

        // Making PLAY button
        Rectangle playButton = new Rectangle(200,100, Color.WHITE);
        playButton.setStroke(Color.BLACK);
        playButton.setOnMouseEntered(e -> playButton.setFill(Color.YELLOW));
        playButton.setOnMouseExited(e -> playButton.setFill(Color.WHITE));
        Text playButtonText = new Text("PLAY");
        playButtonText.setFont(new Font(20));
        playButtonText.setDisable(true);
        StackPane playButtonLayout = new StackPane(playButton, playButtonText);

        // Vertical list of buttons
        VBox menuLayout = new VBox(bannerView, playButtonLayout, authorView);
        VBox.setMargin(bannerView, new Insets(0,0,100,150));
        VBox.setMargin(authorView, new Insets(250,0,0,600));

        //--------------------------------------------------------------------------------

        ArrayList<String> secondPlayerButtonStringList = new ArrayList<>();
        secondPlayerButtonStringList.add("Human");
        secondPlayerButtonStringList.add("AI");

        ArrayList<String> otherPlayersButtonStringList = new ArrayList<>();
        otherPlayersButtonStringList.add("Human");
        otherPlayersButtonStringList.add("AI");
        otherPlayersButtonStringList.add("Empty");

        changePlayerButtonsLook(redPlayerButton, Color.RED);
        redPlayerButton.setDisable(true);

        changePlayerButtonsLook(bluePlayerButton, Color.BLUE);
        bluePlayerButton.setOnMouseClicked(e -> {
            bluePlayerButton.setText(secondPlayerButtonStringList.get(playerPickerTextOne));
            setPlayerPickerTextOne();
        });


        changePlayerButtonsLook(greenPlayerButton, Color.GREEN);
        greenPlayerButton.setOnMouseClicked(e -> {
            greenPlayerButton.setText(otherPlayersButtonStringList.get(playerPickerTextTwo));
            setPlayerPickerTextTwo();
        });


        changePlayerButtonsLook(yellowPlayerButton, Color.YELLOW);
        yellowPlayerButton.setOnMouseClicked(e -> {
            yellowPlayerButton.setText(otherPlayersButtonStringList.get(playerPickerTextTwo));
            setPlayerPickerTextTwo();
        });

        HBox playersPickerLayout = new HBox(redPlayerButton, bluePlayerButton, greenPlayerButton, yellowPlayerButton);
        HBox.setMargin(redPlayerButton, new Insets(0,10,0,0));
        HBox.setMargin(bluePlayerButton, new Insets(0,10,0,0));
        HBox.setMargin(greenPlayerButton, new Insets(0,10,0,0));
        HBox.setMargin(yellowPlayerButton, new Insets(0,0,0,0));

        startButton.setPrefSize(200,100);
        startButton.setFont(new Font(30));
        VBox vbox = new VBox(playersPickerLayout, startButton);
        vbox.setVisible(false);
        VBox.setMargin(startButton, new Insets(10,0,0,350));
        VBox.setMargin(playersPickerLayout, new Insets(0,0,0,250));


        menuGrid.getChildren().addAll(menuBackground, menuLayout, vbox);

        GridPane.setMargin(menuLayout, new Insets(100,0,0,0));
        GridPane.setMargin(vbox, new Insets(300,0,0,0));

        playButtonLayout.setOnMouseClicked(e -> {
            playButtonLayout.setVisible(false);

            vbox.setVisible(true);
        });

    }

    private void changePlayerButtonsLook(Button button, Color color){
        button.setTextFill(color);
        button.setPrefSize(100, 50);
        button.setFont(new Font(15));
    }

    public Button getBluePlayerButton() {
        return bluePlayerButton;
    }

    public Button getGreenPlayerButton() {
        return greenPlayerButton;
    }

    public Button getYellowPlayerButton() {
        return yellowPlayerButton;
    }

    public Button getStartButton() {
        return startButton;
    }

    public GridPane getMenuGrid() {
        return menuGrid;
    }
}
