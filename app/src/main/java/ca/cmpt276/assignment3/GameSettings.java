//Name: Simran Nijjar
//Student Number: 301397295
//Date: October 7, 2022

package ca.cmpt276.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class GameSettings extends AppCompatActivity {
    public static int NUM_ROWS;
    public static int NUM_COLS;
    public static int NUM_FLOWERS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_settings);
        dropdown_board_size();
        dropdown_num_flowers();
    }



    private void dropdown_board_size(){
        Spinner board_size = findViewById(R.id.dropdown_board_size);
        String[] sizes = {"4 x 6", "5 x 10", "6 x 15"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                sizes);
        board_size.setAdapter(adapter);
        board_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        NUM_ROWS = 4;
                        NUM_COLS = 6;
                        break;

                    case 1:
                        NUM_ROWS = 5;
                        NUM_COLS = 10;
                        break;

                    case 2:
                        NUM_ROWS = 6;
                        NUM_COLS = 15;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                NUM_ROWS = 4;
                NUM_COLS = 6;
            }
        });
    }

    private void  dropdown_num_flowers(){
        Spinner num_flowers = findViewById(R.id.dropdown_num_flowers);
        String[] flowers = {"6", "10", "15", "20"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                flowers);
        num_flowers.setAdapter(adapter);
        num_flowers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        NUM_FLOWERS = 6;
                        break;

                    case 1:
                        NUM_FLOWERS = 10;
                        break;

                    case 2:
                        NUM_FLOWERS = 15;
                        break;

                    case 3:
                        NUM_FLOWERS = 20;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                NUM_FLOWERS = 6;
            }
        });
    }
}
