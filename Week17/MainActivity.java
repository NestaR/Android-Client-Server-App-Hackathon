package com.example.week17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void convert_onClick(View v)
    {
        EditText input = (EditText) findViewById(R.id.editText);
        RadioButton F1 = (RadioButton) findViewById(R.id.radioButton);
        RadioButton C1 = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton K1 = (RadioButton) findViewById(R.id.radioButton3);
        RadioButton F2 = (RadioButton) findViewById(R.id.radioButton4);
        RadioButton C2 = (RadioButton) findViewById(R.id.radioButton5);
        RadioButton K2 = (RadioButton) findViewById(R.id.radioButton6);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);


        double value = 0;
        if(F1.isChecked())//Checks which radio buttons are pressed and performs the calculations accordingly
        {
            value = Double.parseDouble(String.valueOf(input.getText()));
            if(C2.isChecked()) {
                value = ((value - 32) * 5 / (9));
            }
            else if(K2.isChecked())
            {
                value = (((value - 32) * 5 / (9)) + 273.15);
            }
            if(checkBox.isChecked())
            {
                int rvalue = (int) Math.rint(value);
                input.setText(String.valueOf(rvalue));
            }
            else
            {
                input.setText(String.valueOf(value));
            }
        }
        else if (C1.isChecked())
        {
            value = Double.parseDouble(String.valueOf(input.getText()));
            if(F2.isChecked()) {
                value = ((value * 9/5) + 32);
            }
            else if(K2.isChecked()) {
                value = ((value + 273.15));
            }
            if(checkBox.isChecked())
            {
                int rvalue = (int) Math.rint(value);
                input.setText(String.valueOf(rvalue));
            }
            else
            {
                input.setText(String.valueOf(value));
            }

        }
        else if (K1.isChecked())
        {
            value = Double.parseDouble(String.valueOf(input.getText()));
            if(F2.isChecked()) {
                value = ((value - 273.15) * 9/5 + 32);
            }
            else if(C2.isChecked()) {
                value = ((value - 273.15));
            }
            if(checkBox.isChecked())
            {
                int rvalue = (int) Math.rint(value);
                input.setText(String.valueOf(rvalue));
            }
            else
            {
                input.setText(String.valueOf(value));
            }

        }
    }
}
