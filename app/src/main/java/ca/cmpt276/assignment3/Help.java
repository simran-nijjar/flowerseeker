//Name: Simran Nijjar, Tomi Lui
//Student Number: 301397295, 301426310
//Date: October 14, 2022

/*
    I watched Dr. Brian Fraser's videos listed under Assignment 3 on the course page
    to get an understanding on how to do the assignment and followed code showed in the videos.
    I followed this tutorial: https://learntodroid.com/how-to-create-a-hyperlink-using-android-textview/
    To learn how to add hyperlinks in TextView and how to access the links
 */

package ca.cmpt276.assignment3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/*
    Help class provides information about the game, provides a hyperlink to the course home page
    and displays the file name of the images used and a hyperlink to where the image sources.
 */
public class Help extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        display_course_link();
        display_flower_link();
        display_gamebackground_link();
        display_skybackground_link();
    }

    /*
        This method returns the Help intent to the Main Menu
     */
    public static Intent make_intent(Context context){
        return new Intent(context, Help.class);
    }

    /*
        Display the 276 course home-page hyperlink using value in strings.xml
     */
    private void display_course_link(){
        TextView homepage_link = findViewById(R.id.course_link);
        homepage_link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /*
      Display the name of the flower that was used in the buttons in GameScreen,
      the three animated flowers on the welcome screen, the flower on the main menu screen,
      and the two flowers shown in the dialog when user finishes a game
   */
    private void display_flower_link(){
        TextView flower_source_link = findViewById(R.id.blueflower_link);
        flower_source_link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /*
      Display the name of the game background that was used as the background on the
      welcome screen and the background of the game screen when the user plays
   */
    private void display_gamebackground_link(){
        TextView gamebackground_source_link = findViewById(R.id.gamebackground_link);
        gamebackground_source_link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /*
      Display the name of the sky background that was used as the background on the
      main menu screen, game settings screen, and the help screen
   */
    private void display_skybackground_link(){
        TextView skybackground_source_link = findViewById(R.id.skybackground_link);
        skybackground_source_link.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
