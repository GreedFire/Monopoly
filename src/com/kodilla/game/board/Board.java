package com.kodilla.game.board;

import com.kodilla.game.board.boardContent.*;
import com.kodilla.game.cards.buyableCards.CircleCard;
import com.kodilla.game.cards.buyableCards.CityCard;
import com.kodilla.game.cards.buyableCards.TriangleCard;
import com.kodilla.game.cards.unBuyableCards.EventCard;
import com.kodilla.game.cards.unBuyableCards.TaxCard;
import javafx.scene.layout.*;
import java.util.HashMap;

public class Board {
    private final HashMap<Integer, BoardField> fieldsArray = new HashMap<>();
    private final GridPane grid = new GridPane();

    private final MainMenu mainMenu = new MainMenu();
    private final Table table = new Table();
    private final FieldInfo fieldInfo = new FieldInfo();
    private final CashLabels cashLabels = new CashLabels();
    private final BuyCardLayout buyCardLayout = new BuyCardLayout();
    private final BelongsIndicators belongsIndicators = new BelongsIndicators();
    private final Dices dices = new Dices();

    public Board(){
        mainMenu.prepareMainMenu();

        prepareFieldsOnBoard();

        prepareRowsAndColumnsOfGrid();

        prepareGridForCardsInfoVisibility();

        dices.prepareDiceAndDiceButtons(grid);

        cashLabels.preparePlayersLabels(grid);

        table.prepareProcessText();

        table.prepareTableContent(grid);

        buyCardLayout.prepareBuyCardLayout(grid);

        belongsIndicators.prepareBelongsToIndicators(grid, fieldsArray);

        fieldInfo.showFieldInfo(grid, fieldsArray);
    }

    private void prepareRowsAndColumnsOfGrid(){
        ColumnConstraints col1 = new ColumnConstraints(130);
        ColumnConstraints col2 = new ColumnConstraints(71);
        RowConstraints row1 = new RowConstraints(104);
        RowConstraints row2 = new RowConstraints(71);

        grid.getColumnConstraints().addAll(col1, col2, col2, col2, col2, col2, col2, col2, col2, col2, col1);
        grid.getRowConstraints().addAll(row1, row2, row2, row2, row2, row2, row2, row2, row2, row2, row1);
    }

    public BelongsIndicators getBelongsIndicators() {
        return belongsIndicators;
    }

    public Dices getDices() {
        return dices;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public Table getTable() {
        return table;
    }

    public CashLabels getCashLabels() {
        return cashLabels;
    }

    public HashMap<Integer, BoardField> getFieldsArray() {
        return fieldsArray;
    }

    public GridPane getGrid() {
        return grid;
    }

    public BuyCardLayout getBuyCardLayout() {
        return buyCardLayout;
    }

    private void prepareFieldsOnBoard(){

        fieldsArray.put(0, new BoardField("Nothing", 881, 795, 881, 825,841,795,841,825,128, 102));

        fieldsArray.put(1, new BoardField("Card", 755, 795, 755, 825, 715,795,715,825, 72, 102, new CityCard("City Card", "field #1", 1, 60,2, 10, 30, 90, 160, 250, 50, "gray")));
        fieldsArray.put(2, new BoardField("Event Card", 683, 795, 683 ,825,643,795,643,825, 72, 102, new EventCard("Event Card")));
        fieldsArray.put(3, new BoardField("Card", 612, 795, 612, 825,572,795,572,825,72, 102, new CityCard("City Card", "field #3", 1, 60, 4, 20, 60, 180, 320, 450, 50, "gray")));
        fieldsArray.put(4, new BoardField("Tax Card", 541,795,541,825,501,795,501,825,72,102, new TaxCard("Tax Card")));
        fieldsArray.put(5, new BoardField("Card", 469, 795, 469, 825,429,795,429,825,72,102, new CircleCard("Circle Card", "field #5", 200)));
        fieldsArray.put(6, new BoardField("Card", 398, 795, 398,825,358,795,358,825,72,102 ,new CityCard("City Card", "field #6", 2, 100,6,30,90,270,400,550,50, "teal")));
        fieldsArray.put(7, new BoardField("Event Card", 327,795,327,825,387,795,387,825,72, 102, new EventCard("Event Card")));
        fieldsArray.put(8, new BoardField("Card", 255,795,255,825,215,795,215,825,72,102 ,new CityCard("City Card", "field #8", 2, 100, 6,30, 90, 270, 400, 550, 50, "teal")));
        fieldsArray.put(9, new BoardField("Card", 184, 795, 184 ,825,144,795,144,825,72,102 ,new CityCard("City Card", "field #9", 2, 120, 8, 40, 100, 300, 450, 600, 50, "teal")));

        fieldsArray.put(10, new BoardField("Nothing", 113,795,113,825,73,795,73,825,128,102));

        fieldsArray.put(11, new BoardField("Card", 77,729,47,729,77,689,47,689, 128,72 ,new CityCard("City Card", "field #11", 3,140,10,50,150,450,625,750,100, "purple")));
        fieldsArray.put(12, new BoardField("Card", 77,657,47,657,77,647,47,647,128,72,new TriangleCard("Triangle Card", "field #12", 150)));
        fieldsArray.put(13, new BoardField("Card", 77,585,47,585,77,545,47,545,128,72, new CityCard("City Card", "field #13", 3, 140, 10,50,150,450,625,750,100, "purple")));
        fieldsArray.put(14, new BoardField("Card",77,513,47,513,77,473,47,473,128,72 ,new CityCard("City Card", "field #14", 3,160,12,60,180,500,700,900,100, "purple")));
        fieldsArray.put(15, new BoardField("Card",77,441,47,441,77,401,47,401,128,72, new CircleCard("Circle Card", "field #15", 200)));
        fieldsArray.put(16, new BoardField("Card", 77,369,47,369,77,329,47,329,128,72 ,new CityCard("City Card", "field #16", 4, 180,14,70,200,550,750,950,100, "orange")));
        fieldsArray.put(17, new BoardField("Event Card", 77,297,47,297,77,257,47,257,128,72, new EventCard("Event Card")));
        fieldsArray.put(18, new BoardField("Card", 77,225,47,225,77,185,47,185,128,72 ,new CityCard("City Card", "field #18", 4,  180,14,70,200,550,750,950,100, "orange")));
        fieldsArray.put(19, new BoardField("Card", 77,153,47,153,77,113,47,113,128,72 ,new CityCard("City Card", "field #19", 4, 200,16,80,220,600,800,1000,100, "orange")));

        fieldsArray.put(20, new BoardField("Nothing", 77,81,47,81,77,41,47,41,128,102));

        fieldsArray.put(21, new BoardField("Card", 143,51,143,21,183,51,183,21,72,102, new CityCard("City Card,", "field #21", 5,220,18,90,250,700,875,1050,150, "red")));
        fieldsArray.put(22, new BoardField("Event Card", 215,51,215,21,175,51,175,21,72,102, new EventCard("Event Card")));
        fieldsArray.put(23, new BoardField("Card", 287,51,287,21,327,51,327,21,72,102 ,new CityCard("City Card", "field #23", 5, 220,18,90,250,700,875,1050,150, "red")));
        fieldsArray.put(24, new BoardField("Card", 359,51,359,21,399,51,399,21, 72,102,new CityCard("City Card", "field #24", 5,240,20,100,300,750,925,1100,150, "red")));
        fieldsArray.put(25, new BoardField("Card", 431,51,431,21,471,51,471,21,72,102, new CircleCard("Circle Card", "field #25", 200)));
        fieldsArray.put(26, new BoardField("Card", 503,51,503,21,543,51,543,21, 72,102,new CityCard("City Card", "field #26", 6,260,22,110,330,800,975,1150,150, "yellow")));
        fieldsArray.put(27, new BoardField("Card", 575,51,575,21,615,51,615,21,72,102, new CityCard("City Card", "field #27", 6,260,22,110,330,800,975,1150, 150, "yellow")));
        fieldsArray.put(28, new BoardField("Card", 647,51,647,21,687,51,687,21,72,102, new TriangleCard("Triangle Card", "field #28", 150)));
        fieldsArray.put(29, new BoardField("Card", 719,51,719,21,759,51,759,21, 72,102,new CityCard("City Card", "field #29", 6,280,24,120,360,850,1025,1200,150, "yellow")));

        fieldsArray.put(30, new BoardField("Prison", 791,51,791,21,791,51,791,21,128,102));

        fieldsArray.put(31, new BoardField("Card", 821,117,851,117,821,157,851,157,128,72, new CityCard("City Card", "field #31", 7,300,26,130,390,900,1100,1275,200, "green")));
        fieldsArray.put(32, new BoardField("Card", 821,189,851,189,821,229,851,229,128,72 , new CityCard("City Card", "field #32", 7, 300,26,130,390,900,1100,1275,200, "green")));
        fieldsArray.put(33, new BoardField("Event Card", 821,261,851,261,821,221,851,221,128,72, new EventCard("Event Card")));
        fieldsArray.put(34, new BoardField("Card", 821,333,851,333,821,373,851,373, 128,72, new CityCard("City Card", "field #34", 7,320,28,150,450,1000,1200,1400,200, "green" )));
        fieldsArray.put(35, new BoardField("Card", 821,405,851,405,821,445,851,445,128,72, new CircleCard("Circle Card", "field #35", 200)));
        fieldsArray.put(36, new BoardField("Event Card", 821,477,851,477,821,517,851,517,128,72, new EventCard("Event Card")));
        fieldsArray.put(37, new BoardField("Card", 821,549,851,549,821,589,851,589, 128,72, new CityCard("City Card", "field #37", 8,350,35,175,500,1100,1300,1500,200, "blue")));
        fieldsArray.put(38, new BoardField("Tax Card", 821,621,851,621,821,661,851,661,128,72, new TaxCard("Tax Card")));
        fieldsArray.put(39, new BoardField("Card", 821,693,851,693,821,723,851,723, 128,72, new CityCard("City Card", "field #39", 8,400,50,200,600,1400,1700,2000,200, "blue")));

    }

    private void prepareGridForCardsInfoVisibility(){
        grid.add(fieldsArray.get(0).getRectangle(),10,10);
        grid.add(fieldsArray.get(1).getRectangle(),9,10);
        grid.add(fieldsArray.get(2).getRectangle(),8,10);
        grid.add(fieldsArray.get(3).getRectangle(),7,10);
        grid.add(fieldsArray.get(4).getRectangle(),6,10);
        grid.add(fieldsArray.get(5).getRectangle(),5,10);
        grid.add(fieldsArray.get(6).getRectangle(),4,10);
        grid.add(fieldsArray.get(7).getRectangle(),3,10);
        grid.add(fieldsArray.get(8).getRectangle(),2,10);
        grid.add(fieldsArray.get(9).getRectangle(),1,10);
        grid.add(fieldsArray.get(10).getRectangle(),0,10);
        grid.add(fieldsArray.get(11).getRectangle(),0,9);
        grid.add(fieldsArray.get(12).getRectangle(),0,8);
        grid.add(fieldsArray.get(13).getRectangle(),0,7);
        grid.add(fieldsArray.get(14).getRectangle(),0,6);
        grid.add(fieldsArray.get(15).getRectangle(),0,5);
        grid.add(fieldsArray.get(16).getRectangle(),0,4);
        grid.add(fieldsArray.get(17).getRectangle(),0,3);
        grid.add(fieldsArray.get(18).getRectangle(),0,2);
        grid.add(fieldsArray.get(19).getRectangle(),0,1);
        grid.add(fieldsArray.get(20).getRectangle(),0,0);
        grid.add(fieldsArray.get(21).getRectangle(),1,0);
        grid.add(fieldsArray.get(22).getRectangle(),2,0);
        grid.add(fieldsArray.get(23).getRectangle(),3,0);
        grid.add(fieldsArray.get(24).getRectangle(),4,0);
        grid.add(fieldsArray.get(25).getRectangle(),5,0);
        grid.add(fieldsArray.get(26).getRectangle(),6,0);
        grid.add(fieldsArray.get(27).getRectangle(),7,0);
        grid.add(fieldsArray.get(28).getRectangle(),8,0);
        grid.add(fieldsArray.get(29).getRectangle(),9,0);
        grid.add(fieldsArray.get(30).getRectangle(),10,0);
        grid.add(fieldsArray.get(31).getRectangle(),10,1);
        grid.add(fieldsArray.get(32).getRectangle(),10,2);
        grid.add(fieldsArray.get(33).getRectangle(),10,3);
        grid.add(fieldsArray.get(34).getRectangle(),10,4);
        grid.add(fieldsArray.get(35).getRectangle(),10,5);
        grid.add(fieldsArray.get(36).getRectangle(),10,6);
        grid.add(fieldsArray.get(37).getRectangle(),10,7);
        grid.add(fieldsArray.get(38).getRectangle(),10,8);
        grid.add(fieldsArray.get(39).getRectangle(),10,9);
    }
}
