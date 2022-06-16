package com.example.week16;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonRed = findViewById(R.id.buttonRed);
        Button buttonBlue = findViewById(R.id.buttonBlue);

        buttonRed.setOnClickListener(this);

        buttonBlue.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonRed:

                changeRed(v);
                //If the red button is pressed then applies the method to change the background colour to red
                break;
            case R.id.buttonBlue:
                changeBlue(v);
                //If the red button is pressed then applies the method to change the background colour to blue
                break;
        }

    }
    public void changeRed (View v){
        Button buttonBlue = (Button) findViewById(R.id.buttonBlue);
        buttonBlue.setText("Blue");
        this.getWindow().getDecorView().getRootView().setBackgroundColor(Color.RED);
        Button buttonRed = (Button) v;
        buttonRed.setText("It is now red");
        //Sets the background colour to red and changes the text to tell the user it has changed
    }

    public void changeBlue(View v){
        Button buttonRed = (Button) findViewById(R.id.buttonRed);
        buttonRed.setText("Red");
        this.getWindow().getDecorView().getRootView().setBackgroundColor(Color.BLUE);
        Button buttonBlue = (Button) v;
        buttonBlue.setText("It is now blue");
        //Sets the background colour to blue and changes the text to tell the user it has changed
    }
}
