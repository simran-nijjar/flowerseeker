//Name: Simran Nijjar and Tomi Lui
//Student Numbers: 301397295 and
//Date: October 14, 2022

/*
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

public class Help extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        course_link();
        flower_link();
        gamebackground_link();
        skybackground_link();
    }

    public static Intent make_intent(Context context){
        return new Intent(context, Help.class);
    }

    private void course_link(){
        TextView homepage_link = findViewById(R.id.course_link);
        homepage_link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void flower_link(){
        TextView flower_source_link = findViewById(R.id.blueflower_link);
        flower_source_link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void gamebackground_link(){
        TextView gamebackground_source_link = findViewById(R.id.gamebackground_link);
        gamebackground_source_link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void skybackground_link(){
        TextView skybackground_source_link = findViewById(R.id.skybackground_link);
        skybackground_source_link.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
