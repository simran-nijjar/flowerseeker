//Name: Simran Nijjar
//Student Number: 301397295
//Date: October 7, 2022

package ca.cmpt276.assignment3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ca.cmpt276.assignment3.model.GameDetails;

public class GameSettings extends AppCompatActivity {
    private GameDetails game_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        game_details = GameDetails.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_settings);
        if (game_details.get_rows() == 4 && game_details.get_cols() == 6) {
            save_board_size("4 rows by 6 columns");
        }
        create_radio_board_size();
        create_radio_flowers();
    }

    public static Intent make_intent(Context context){
        return new Intent(context, GameSettings.class);
    }

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

            if (board_size.equals(get_board_size(this))){
                button.setChecked(true);
            }
        }
    }

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

    static public String get_board_size(Context context){
        SharedPreferences preferences = context.getSharedPreferences("Board Size Preferences", MODE_PRIVATE);
        String default_size = context.getResources().getString(R.string.default_board_size);
        return preferences.getString("Board Size", default_size);
    }

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

            if (flowers == get_saved_flowers(this)){
                button.setChecked(true);
            }
        }
    }

    private void save_flowers(int flowers) {
        SharedPreferences preferences = this.getSharedPreferences("Flower Preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("Number of flowers", flowers);
        game_details.set_flowers(flowers);
        editor.apply();
    }

    static public int get_saved_flowers(Context context){
        SharedPreferences preferences = context.getSharedPreferences("Flower Preferences", MODE_PRIVATE);
        int default_flowers = context.getResources().getInteger(R.integer.default_num_flowers);
        return preferences.getInt("Number of flowers",default_flowers);
    }
}
