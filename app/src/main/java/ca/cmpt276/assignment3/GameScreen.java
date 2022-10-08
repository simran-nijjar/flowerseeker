//Name: Simran Nijjar
//Student Number: 301397295
//Date: October 8, 2022

package ca.cmpt276.assignment3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameScreen extends AppCompatActivity {
    Button buttons[][] = new Button[GameSettings.NUM_ROWS][GameSettings.NUM_COLS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        populate_buttons();
    }

    private void populate_buttons(){
        TableLayout table = findViewById(R.id.table_for_flower_buttons);
        for (int row = 0; row < GameSettings.NUM_ROWS; row++){
            TableRow table_row = new TableRow(this);
            table_row.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(table_row);
            for (int col = 0; col < GameSettings.NUM_COLS; col++){
                final int FINAL_COL = col;
                final int FINAL_ROW = row;
                Button btn = new Button(this);
                btn.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                btn.setText("" + col + "," + row);
                //Prevents text from being cut out
                btn.setPadding(0,0,0,0);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        button_clicked(FINAL_COL,FINAL_ROW);
                    }
                });
                table_row.addView(btn);
                buttons[row][col] = btn;
            }
        }
    }

    private void button_clicked(int col, int row){
        Toast.makeText(this,"Button clicked: " + col + ", " + row,Toast.LENGTH_SHORT).show();
        Button btn = buttons[row][col];
        //Lock button sizes
        lock_button_sizes();
        //Scale image to button
        int new_width = btn.getWidth();
        int new_height = btn.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blueflower);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, new_width, new_height, true);
        Resources resource = getResources();
        btn.setBackground(new BitmapDrawable(resource, scaledBitmap));
        //Change text on button
        btn.setText("" + col);

    }

    private void lock_button_sizes(){
        for (int row = 0; row < GameSettings.NUM_ROWS; row++){
            for (int col = 0; col < GameSettings.NUM_COLS; col++){
                Button btn = buttons[row][col];
                int width = btn.getWidth();
                btn.setMinWidth(width);
                btn.setMaxWidth(width);
                int height = btn.getHeight();
                btn.setMinHeight(height);
                btn.setMaxHeight(height);
            }
        }
    }
}
