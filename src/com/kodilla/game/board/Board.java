package com.kodilla.game.board;

import com.kodilla.game.cards.CityCard;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static HashMap<Integer, BoardField> fieldsArray = new HashMap<>();
    static{
        fieldsArray.put(0, new BoardField("Nothing", 881, 795, 881, 825));

        fieldsArray.put(1, new BoardField("Card", 755, 795, 755, 825, new CityCard("City Card", "field #1", 1, 2, 60,2, 10, 30, 90, 160, 250, 50)));
        fieldsArray.put(2, new BoardField("Nothing", 683, 795, 683 ,825));
        fieldsArray.put(3, new BoardField("Card", 612, 795, 612, 825, new CityCard("City Card", "field #3", 1, 2, 60, 4, 20, 60, 180, 320, 450, 50)));
        fieldsArray.put(4, new BoardField("Nothing", 541,795,541,825));
        fieldsArray.put(5, new BoardField("Nothing", 469, 795, 469, 825));
        fieldsArray.put(6, new BoardField("Card", 398, 795, 398,825, new CityCard("City Card", "field #6", 2, 3, 100,6,30,90,270,400,550,50)));
        fieldsArray.put(7, new BoardField("Nothing", 327,795,327,825));
        fieldsArray.put(8, new BoardField("Card", 255,795,255,825, new CityCard("City Card", "field #8", 2, 3, 100, 6,30, 90, 270, 400, 550, 50)));
        fieldsArray.put(9, new BoardField("Card", 184, 795, 184 ,825, new CityCard("City Card", "field #9", 2, 3, 120, 8, 40, 100, 300, 450, 600, 50)));

        fieldsArray.put(10, new BoardField("Nothing", 113,795,113,825));

        fieldsArray.put(11, new BoardField("Card", 77,729,47,729, new CityCard("City Card", "field 11", 3, 3,140,10,50,150,450,625,750,100)));
        fieldsArray.put(12, new BoardField("Nothing", 77,657,47,657));
        fieldsArray.put(13, new BoardField("Card", 77,585,47,585, new CityCard("City Card", "field #13", 3, 3, 140, 10,50,150,450,625,750,100)));
        fieldsArray.put(14, new BoardField("Card",77,513,47,813, new CityCard("City Card", "field #14", 3,3,160,12,60,180,500,700,900,100)));
        fieldsArray.put(15, new BoardField("Nothing",77,441,47,441));
        fieldsArray.put(16, new BoardField("Card", 77,369,47,369, new CityCard("City Card", "field #16", 4, 3, 180,14,70,200,550,750,950,100)));
        fieldsArray.put(17, new BoardField("Nothing", 77,297,47,297));
        fieldsArray.put(18, new BoardField("Card", 77,225,47,225, new CityCard("City Card", "field #18", 4, 3, 180,14,70,200,550,750,950,100)));
        fieldsArray.put(19, new BoardField("Card", 77,153,47,153, new CityCard("City Card", "field #19", 4, 3, 200,16,80,220,600,800,1000,100)));

        fieldsArray.put(20, new BoardField("Nothing", 77,81,47,81));

        fieldsArray.put(21, new BoardField("Card", 143,51,143,21, new CityCard("City Card,", "field #21", 5, 3,220,18,90,250,700,875,1050,150)));
        fieldsArray.put(22, new BoardField("Nothing", 215,51,215,21));
        fieldsArray.put(23, new BoardField("Card", 287,51,287,21, new CityCard("City Card", "field #23", 5, 3, 220,18,90,250,700,875,1050,150)));
        fieldsArray.put(24, new BoardField("Card", 359,51,359,21, new CityCard("City Card", "field #24", 5, 3,240,20,100,300,750,925,1100,150)));
        fieldsArray.put(25, new BoardField("Nothing", 431,51,431,21));
        fieldsArray.put(26, new BoardField("Card", 503,51,503,21, new CityCard("City Card", "field #26", 6, 3,260,22,110,330,800,975,1150,150)));
        fieldsArray.put(27, new BoardField("Card", 575,51,575,21, new CityCard("City Card", "field #27", 6,3,260,22,110,330,800,975,1150, 150)));
        fieldsArray.put(28, new BoardField("Nothing", 647,51,647,21));
        fieldsArray.put(29, new BoardField("Card", 719,51,719,21, new CityCard("City Card", "field #28", 6,3,280,24,120,360,850,1025,1200,150)));

        fieldsArray.put(30, new BoardField("Nothing", 791,51,791,21));

        fieldsArray.put(31, new BoardField("Card", 821,117,851,117, new CityCard("City Card", "field #31", 7,3,300,26,130,390,900,1100,1275,200)));
        fieldsArray.put(32, new BoardField("Card", 821,189,851,189, new CityCard("City Card", "field #32", 7, 3, 300,26,130,390,900,1100,1275,200)));
        fieldsArray.put(33, new BoardField("Nothing", 821,261,851,261));
        fieldsArray.put(34, new BoardField("Card", 821,333,851,333, new CityCard("City Card", "field #34", 7,3,320,28,150,450,1000,1200,1400,200)));
        fieldsArray.put(35, new BoardField("Nothing", 821,405,851,405));
        fieldsArray.put(36, new BoardField("Nothing", 821,477,851,477));
        fieldsArray.put(37, new BoardField("Card", 821,549,851,549, new CityCard("City Card", "field #37", 8, 2,350,35,175,500,1100,1300,1500,200)));
        fieldsArray.put(38, new BoardField("Nothing", 821,621,851,621));
        fieldsArray.put(39, new BoardField("Card", 821,693,851,693, new CityCard("City Card", "field #39", 8,2,400,50,200,600,1400,1700,2000,200)));
    }

    public static Map<Integer, BoardField> getBoard(){
        Map<Integer, BoardField> copy = new HashMap<>(fieldsArray);
        return copy;
    }

}
