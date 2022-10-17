//Name: Simran Nijjar, Tomi Lui
//Student Number: 301397295, 301426310
//Date: October 6, 2022

/*
    I watched Dr. Brian Fraser's videos listed under Assignment 3 on the course page
    to get an understanding on how to do the assignment and followed code showed in the videos.
    I looked at https://www.youtube.com/watch?v=goVoYf2qie0
    "How to create rotate/spin animation in android | Android tutorial for beginners" by CoderScion Media
    To learn about how to an image spin on its own
     I read about alpha and how to make an image fade in on https://developer.android.com/develop/ui/views/animations/reveal-or-hide-view
 */

package ca.cmpt276.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/*
    MainActivity class displays the welcome screen to the user which displays
    name of application, name of authors, images of flowers that are animated,
    and a skip button that skips to the main menu
 */
public class MainActivity extends AppCompatActivity {
    Animation spinAnimation;
    Animation fadeIn;
    private ImageView image_view1;
    private ImageView image_view2;
    private ImageView image_view3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image_view1 = findViewById(R.id.blue_flower1);
        image_view2 = findViewById(R.id.blue_flower2);
        image_view3 = findViewById(R.id.blue_flower3);
        spin_animation();
        fade_in();
        main_menu_button();
    }

    /*
        This method displays the skip button which allows user to go the main menu
     */
    private void main_menu_button() {
        Button skip_button = findViewById(R.id.skip_button);
        skip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }

    /*
        This creates the spin animation which gets the animation details from spinright.xml
     */
    private void spin_animation() {
        spinAnimation = AnimationUtils.loadAnimation(this,R.anim.spinright);
        image_view1.startAnimation(spinAnimation);
    }

    /*
        This creates the fade in animation which gets the animation details from fadein.xml
     */
    private void fade_in() {
        fadeIn = AnimationUtils.loadAnimation(this,R.anim.fadein);
        image_view2.startAnimation(fadeIn);
        image_view3.startAnimation(fadeIn);
    }

}