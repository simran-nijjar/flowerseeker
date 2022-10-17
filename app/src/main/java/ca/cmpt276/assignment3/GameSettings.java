//Name: Simran Nijjar, Tomi Lui
//Student Number: 301397295, 301426310
//Date: October 7, 2022

/*
I watched Dr. Brian Fraser's videos listed under Assignment 3 on the course page
to learn about SharedPreferences to create radio buttons and to use arrays in the
values folder to display and get the user options
 */

package ca.cmpt276.assignment3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import ca.cmpt276.assignment3.model.GameDetails;

/*
    GameSettings class displays the game options for board size and number of flowers to look for for the user to click
 */
public class GameSettings extends AppCompatActivity {
    private GameDetails game_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        game_details = GameDetails.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_settings);
        create_radio_board_size();
        create_radio_flowers();
    }

    /*
        This method returns the Game Settings intent to the Main Menu
     */
    public static Intent make_intent(Context context){
        return new Intent(context, GameSettings.class);
    }

    /*
        This method goes through the array of values in board_size.xml to display to user in radio buttons
        When user clicks a board size, it will call the save_board_size() function to save board size
     */
    private void create_radio_board_size(){
        RadioGroup group = findViewById(R.id.radio_group_board_size);
        String[] board_size_array = getResources().getStringArray(R.array.board_size);

        for (int i = 0; i < board_size_array.length; i++){
            final String board_size = board_size_array[i];

            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.display_string_option, board_size));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    save_board_size(board_size);
                }
            });

            group.addView(button);

            /*
                If the board size in the board_size.xml array is the one saved in the last application run
                Display the radio button with that size to be checked
             */
            if (board_size.equals(get_board_size(this))){
                button.setChecked(true);
            }
        }
    }

    /*
        This method looks at the string of the board size user clicked in Game Settings to set the rows and cols
     */
    private void save_board_size(String board_size) {
        SharedPreferences preferences = this.getSharedPreferences("Board Size Preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Board Size", board_size);
        editor.apply();

        if (board_size.equals("4 rows by 6 columns")){
            game_details.set_rows(4);
            game_details.set_cols(6);
        }
        if (board_size.equals("5 rows by 10 columns")){
            game_details.set_rows(5);
            game_details.set_cols(10);
        }
        if (board_size.equals("6 rows by 15 columns")) {
            game_details.set_rows(6);
            game_details.set_cols(15);
        }
    }

    /*
        This method gets the board size that was last chosen in the most recent application run
     */
    static public String get_board_size(Context context){
        SharedPreferences preferences = context.getSharedPreferences("Board Size Preferences", MODE_PRIVATE);
        String default_size = context.getResources().getString(R.string.default_board_size);
        return preferences.getString("Board Size", default_size);
    }

    /*
       This method goes through the array of values in flowers.xml to display to user in radio buttons
       When user clicks the number of flowers, it will call the save_flowers() function to number of flowers
    */
    @SuppressLint("SetTextI18n")
    private void create_radio_flowers(){
        RadioGroup group = findViewById(R.id.radio_group_flowers);
        int[] flowers_array = getResources().getIntArray(R.array.flowers);

        for (int i = 0; i < flowers_array.length; i++){
            final int flowers = flowers_array[i];

            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.display_flowers_string, flowers));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    save_flowers(flowers);
                }
            });

            group.addView(button);

              /*
                If the flower in the flowers.xml array is the one saved in the last application run
                Display the radio button with that number of flowers to be checked
             */
            if (flowers == get_saved_flowers(this)){
                button.setChecked(true);
            }
        }
    }

    /*
        This method looks at the number of flowers user has chosen in game settings and sets it
     */
    private void save_flowers(int flowers) {
        SharedPreferences preferences = this.getSharedPreferences("Flower Preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("Number of flowers", flowers);
        game_details.set_flowers(flowers);
        editor.apply();
    }

    /*
        This method gets the number of flowers that was last chosen in the most recent application run
     */
    static public int get_saved_flowers(Context context){
        SharedPreferences preferences = context.getSharedPreferences("Flower Preferences", MODE_PRIVATE);
        int default_flowers = context.getResources().getInteger(R.integer.default_num_flowers);
        return preferences.getInt("Number of flowers",default_flowers);
    }
}
