//Name: Simran Nijjar
//Student Number: 301397295
//Date: October 7, 2022

package ca.cmpt276.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameSettings extends AppCompatActivity {
    public static int NUM_ROWS = 4;
    public static int NUM_COLS = 6;
    public static int NUM_FLOWERS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_settings);
        create_radio_board_size();
        create_radio_flowers();
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
                    Toast.makeText(GameSettings.this,"You clicked this " + board_size , Toast.LENGTH_SHORT).show();
                }
            });

            group.addView(button);
        }
    }

    private void create_radio_flowers(){
        RadioGroup group = findViewById(R.id.radio_group_flowers);
        String[] flowers_array = getResources().getStringArray(R.array.flowers);

        for (int i = 0; i < flowers_array.length; i++){
            final String flowers = flowers_array[i];

            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.display_string_option, flowers));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(GameSettings.this, "You clicked " + flowers, Toast.LENGTH_SHORT).show();
                }
            });

            group.addView(button);
        }
    }
}
