//Name: Simran Nijjar, Tomi Lui
//Student Number: 301397295, 301426310
//Date: October 11, 2022

/*
    I watched Dr. Brian Fraser's videos listed under Assignment 3 on the course page
    to get an understanding on how to do the assignment and followed code showed in the videos.
 */

package ca.cmpt276.assignment3.model;

import java.util.HashSet;

/*
    GameDetails class is the Singleton class that stores the options data for playing the game
 */
public class GameDetails {
    private int rows;
    private int cols;
    private int flowers;
    private int num_of_flowers_found;
    HashSet<Cell> set;

    private class Cell {
        public int row;
        public int col;
        public Cell(int row,int col) {
            this.row = row;
            this.col = col;
        }
    }

    /*
        Constructor for creating a new game with pre-set settings
     */
    private GameDetails(){
        rows = 4;
        cols = 6;
        flowers = 6;
        num_of_flowers_found = 0;

        generate_flower_coordinates(flowers);
    }

    public void restart_game() {
        num_of_flowers_found = 0;
        generate_flower_coordinates(flowers);
    }

    private static GameDetails instance;
    public static GameDetails getInstance(){
        if (instance == null){
            instance = new GameDetails();
        }
        return instance;
    }

    /*
        Setters and getters for the game options
     */
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

    public int get_num_of_flowers_found() {
        return num_of_flowers_found;
    }

    private int get_random_int(int min, int max) {
        return (int)(Math.random()*(max-min+1)+min);
    }

    /*
        This function generates and stores the cell of the flowers in a hashset
     */
    private void generate_flower_coordinates(int number_of_flowers) {
        set = new HashSet<Cell>(); // stores {row, col} = location of the flower

        int i = 0;
        while (i < number_of_flowers) {
            Cell flower_cell = new Cell(get_random_int(0, rows - 1), get_random_int(0, cols - 1));
            if (!has_flower(flower_cell.row, flower_cell.col)) {
                set.add(flower_cell);
                i++;
            }
        }
    }

    /*
        Returns True if the input cell contains a flower
     */
    public boolean has_flower(int row_num, int col_num) {
        for ( Cell c : set) {
            if (row_num == c.row && col_num == c.col) {
                return true;
            }
        }
        return false;
    }

    public String show_flowers_coordinates() {
        StringBuilder s = new StringBuilder() ;
        for (Cell c : set) {
            s.append("row: " + Integer.toString(c.row) + " " + "col: " + Integer.toString(c.col));
            s.append("\n");
        }
        return s.toString();
    }

    /*
            If the cell does not contain a flower, return the how many flowers exist in the same row
        and col
            if the cell contain a flower, return -1
     */
    public int get_hint(int row_num, int col_num) {
        if (has_flower(row_num, col_num)) {
            // return -1 if coordinate has a flower
            num_of_flowers_found ++;
            return -1;
        }

        int flowers_in_cols = 0;
        int flowers_in_rows = 0;
        for (Cell cell : set) {

            if (cell.row == row_num) {
                flowers_in_rows ++;
            }

            if (cell.col == col_num) {
                flowers_in_cols ++;
            }
        }
        return flowers_in_cols + flowers_in_rows;
    }

    public boolean game_complete() {
        return num_of_flowers_found == flowers;
    }

    /*
       This method will set the rows and cols based on what the user
       clicked in the Game Settings screen.
     */

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
