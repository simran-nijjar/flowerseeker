//Name: Simran Nijjar, Tomi Lui
//Student Number: 301397295, 301426310
//Date: October 7, 2022

/*
I watched Dr. Brian Fraser's videos listed under Assignment 3 on the course page
to get an understanding on how to do the assignment and followed code showed in the videos.
 */

package ca.cmpt276.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/*
    MainMenu class displays three buttons which allows the user to:
    play the game, go to game settings, and go to help for game information
 */
public class MainMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        play_game_button();
        options_button();
        help_button();
    }

    /*
        This method displays a button that when clicked will go to the game screen
     */
    private void play_game_button(){
        Button play_game = findViewById(R.id.play_game_button);
        play_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GameScreen.make_intent(MainMenu.this);
                startActivity(intent);
            }
        });
    }

    /*
        This method displays a button that when clicked will go to game settings
     */
    private void options_button(){
        Button option_button = findViewById(R.id.options_button);
        option_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GameSettings.make_intent(MainMenu.this);
                startActivity(intent);
            }
        });
    }

    /*
        This method displays a button that when clicked will go to the help screen
     */
    private void help_button(){
        Button help = findViewById(R.id.help_button);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Help.make_intent(MainMenu.this);
                startActivity(intent);
            }
        });
    }

}
