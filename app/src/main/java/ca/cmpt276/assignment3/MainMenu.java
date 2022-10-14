//Name: Simran Nijjar
//Student Number: 301397295
//Date: October 7, 2022

package ca.cmpt276.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        play_game_button();
        options_button();
    }

    private void play_game_button(){
        Button play_game = findViewById(R.id.play_game_button);
        play_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, GameScreen.class);
                startActivity(intent);
            }
        });
    }

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

}
