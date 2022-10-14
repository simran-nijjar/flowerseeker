//Name: Simran Nijjar
//Student Number: 301397295
//Date: October 11, 2022

package ca.cmpt276.assignment3.model;

public class GameDetails {
    private int rows;
    private int cols;
    private int flowers;

    public GameDetails(){
        rows = 4;
        cols = 6;
        flowers = 6;
    }

    private static GameDetails instance;
    public static GameDetails getInstance(){
        if (instance == null){
            instance = new GameDetails();
        }
        return instance;
    }

    public void set_rows(int rows) {
        this.rows = rows;
    }

    public void set_cols(int cols){
        this.cols = cols;
    }

    public void set_flowers(int flowers){
        this.flowers = flowers;
    }

    public int get_rows(){
        return rows;
    }

    public int get_cols(){
        return cols;
    }

    public int get_flowers(){
        return flowers;
    }

    public void set_board_size(String board_size){
        if (board_size.equals("4 rows by 6 columns")){
            set_rows(4);
            set_cols(6);
        }
        if (board_size.equals("5 rows by 10 columns")){
            set_rows(5);
            set_cols(10);
        }
        if (board_size.equals("6 rows by 15 columns")) {
            set_rows(6);
            set_cols(15);
        }
    }
}
