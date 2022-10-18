//Name: Simran Nijjar, Tomi Lui
//Student Number: 301397295, 301426310
//Date: October 8, 2022

/*
    I watched Dr. Brian Fraser's videos listed under Assignment 3 on the course page to learn how
    to populate buttons, display image or number when button is clicked, and to lock button sizer
    I looked at https://stackoverflow.com/a/6276592 to learn how to add images to an alert dialog
    The following lines of code reference this website:
    LayoutInflater inflater = LayoutInflater.from(GameScreen.this);
    final View congrats_dialog = inflater.inflate(R.layout.congratulations_dialog, null);
 */

package ca.cmpt276.assignment3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ca.cmpt276.assignment3.model.GameDetails;

/*
    GameScreen class displays the game to the player and allows them to play
 */

public class GameScreen extends AppCompatActivity {
    private GameDetails game_details = GameDetails.getInstance();
    Button buttons[][];
    private final String TAG = "GAME_SCREEN_TAG";
    int scans_used_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
            Get the saved board size and saved flowers from when the application
            was last run using methods that use SharedPreferences to create the new game using same option
         */
        String saved_board_size = GameSettings.get_board_size(this);
        int saved_flowers = GameSettings.get_saved_flowers(this);
        game_details.set_board_size(saved_board_size);
        game_details.set_flowers(saved_flowers);
        game_details.restart_game();
        buttons = new Button[game_details.get_rows()][game_details.get_cols()];
        scans_used_count = 0;
        setContentView(R.layout.game_screen);
        populate_buttons();
        // remove this later
//        String s = game_details.show_flowers_coordinates();
//        Log.d(TAG, "\nFlower coordinates: ");
//        Log.d(TAG, s);


    }

    /*
        This method returns the Game Screen intent to the Main Menu
     */
    public static Intent make_intent(Context context){
        return new Intent(context, GameScreen.class);
    }

    /*
        This method will populate the buttons the user plays with based on the board size set in options
        When a button is clicked, button_clicked() is clicked which will either display a flower or hint
     */
    private void populate_buttons(){
        TableLayout table = findViewById(R.id.table_for_flower_buttons);
        for (int row = 0; row < game_details.get_rows(); row++){
            //Add row into table for every row from game details
            TableRow table_row = new TableRow(this);
            //Match width and height of table to parent and scale weight
            table_row.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(table_row);
            for (int col = 0; col < game_details.get_cols(); col++){
                final int FINAL_COL = col;
                final int FINAL_ROW = row;
                //Add button into each column of each row
                Button btn = new Button(this);
                //Match width and height of buttons to parent and scale weight
                btn.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                //Set the padding so that the text is not cut off
                btn.setPadding(0,0,0,0);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //When button is clicked check if button contains a flower or not
                        button_clicked(FINAL_COL,FINAL_ROW);
                        btn.setEnabled(false);
                    }
                });
                table_row.addView(btn);
                buttons[row][col] = btn;
            }
        }
    }

    /*
        This method will display if the use guessed correctly with locked button sizes so image
        does not distort the button. Otherwise, display number of flowers in row and col.
     */
    private void button_clicked(int col, int row){
        Button btn = buttons[row][col];
        //Lock button sizes
        lock_button_sizes();
        //Scale image to button
        int new_width = btn.getWidth();
        int new_height = btn.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blueflower);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, new_width, new_height, true);
        Resources resource = getResources();

        int hint = game_details.get_hint(row, col);
        // if user guessed correctly, display flower
        if (hint == -1) {
            btn.setBackground(new BitmapDrawable(resource, scaledBitmap));
            // update ui
            TextView flowers_found_tx = findViewById(R.id.tx_found_flowers_count);
            flowers_found_tx.setText(Integer.toString(game_details.get_num_of_flowers_found()));

            decrement_hints(row, col);
        }
        else {
            //Change text on button
            btn.setText(Integer.toString(hint));
        }

        boolean contains = game_details.has_flower(row, col);
        Log.d(TAG, "row: " + row + " col: " + col + "\nCONTAINS: " + contains);

        // update scans used
        TextView scans_used = findViewById(R.id.tx_scans_count);
        scans_used_count ++;
        scans_used.setText(Integer.toString(scans_used_count));

        // check if all flowers have been found
        if (game_details.game_complete()) {
            finish_game_popup();
//            Toast.makeText(this, "GAME COMPLETE!!!", Toast.LENGTH_SHORT).show();
        }

    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void decrement_hints(int row, int col) {
        for (int i = 0; i < game_details.get_rows(); i++) {
            for (int j = 0; j < game_details.get_cols(); j++) {
                if (i == row || j == col) {
                    Button curButton = buttons[i][j];
                    String btnText = (String) curButton.getText();
//                    Toast.makeText(this, btnText, Toast.LENGTH_SHORT).show();

                    if (isNumeric(btnText)) {
                        curButton.setText(Integer.toString(Integer.parseInt(btnText) -1));
                    }
                }
            }

        }
    }

    void finish_game_popup() {
        ImageView congrats_image = new ImageView(this);
        congrats_image.setImageResource(R.drawable.blueflower);
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };
        LayoutInflater inflater = LayoutInflater.from(GameScreen.this);
        final View congrats_dialog = inflater.inflate(R.layout.congratulations_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(congrats_dialog);

        builder.setPositiveButton("return", dialogClickListener).show();

//                .setNegativeButton("No", dialogClickListener).show();
    }

    /*
        This method locks the button sizes so that when user clicks a button, the button does not change size
     */
    private void lock_button_sizes(){
        for (int row = 0; row < game_details.get_rows(); row++){
            for (int col = 0; col < game_details.get_cols(); col++){
                Button btn = buttons[row][col];
                //Set the width and height of button to not change when user clicks button
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
