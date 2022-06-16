package com.example.week19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity  {

    char operator;
    double fnum = 0;
    boolean sequence = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText input = findViewById(R.id.editText);
        Button addButton = findViewById(R.id.addButton);
        Button subtractButton = findViewById(R.id.subtractButton);
        Button multiplyButton = findViewById(R.id.multiplyButton);
        Button divideButton = findViewById(R.id.divideButton);
        Button equalsButton = findViewById(R.id.equalsButton);
        Button clearButton = findViewById(R.id.clearButton);


    }

    public void addClick(View v)
    {
        EditText input = findViewById(R.id.editText);
        try {
//Depending on what operator the user has pressed it will change the variable 'operator'
            if (operator == '+')
            {//To perform multiple operations at the same time it needs to perform the previous calculation first and then move on to the next
                fnum += Double.parseDouble(String.valueOf(input.getText()));
                operator = '+';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else if (operator == '-')
            {
                fnum -= Double.parseDouble(String.valueOf(input.getText()));
                operator = '+';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else if (operator == '/')
            {
                fnum /= Double.parseDouble(String.valueOf(input.getText()));
                operator = '+';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else if (operator == '*')
            {
                fnum *= Double.parseDouble(String.valueOf(input.getText()));
                operator = '+';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else
            {
                fnum = Double.parseDouble(String.valueOf(input.getText()));
                operator = '+';
                input.setText("");
            }

        }
        catch (NumberFormatException e)
        {
            input.setText("Error");
            operator = ' ';
        }
    }
    public void subtractClick(View v)
    {
        EditText input = findViewById(R.id.editText);
        try {
            if (operator == '-')
            {
                fnum -= Double.parseDouble(String.valueOf(input.getText()));
                operator = '-';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else if (operator == '+')
            {
                fnum += Double.parseDouble(String.valueOf(input.getText()));
                operator = '-';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else if (operator == '/')
            {
                fnum /= Double.parseDouble(String.valueOf(input.getText()));
                operator = '-';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else if (operator == '*')
            {
                fnum *= Double.parseDouble(String.valueOf(input.getText()));
                operator = '-';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else
            {
                fnum = Double.parseDouble(String.valueOf(input.getText()));
                operator = '-';
            }
        }
        catch (NumberFormatException e)
        {
            input.setText("Error");
            operator = ' ';
        }
    }
    public void divideClick(View v)
    {
        EditText input = findViewById(R.id.editText);
        try {
            if (operator == '/')
            {
                fnum /= Double.parseDouble(String.valueOf(input.getText()));
                operator = '/';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else if (operator == '-')
            {
                fnum -= Double.parseDouble(String.valueOf(input.getText()));
                operator = '/';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else if (operator == '+')
            {
                fnum += Double.parseDouble(String.valueOf(input.getText()));
                operator = '/';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else if (operator == '*')
            {
                fnum *= Double.parseDouble(String.valueOf(input.getText()));
                operator = '/';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else
            {
                fnum = Double.parseDouble(String.valueOf(input.getText()));
                operator = '/';
                input.setText("");
            }
        }
        catch (NumberFormatException e)
        {
            input.setText("Error");
            operator = ' ';
        }
    }
    public void multiplyClick(View v)
    {
        EditText input = findViewById(R.id.editText);
        try {
            if (operator == '*')
            {
                fnum *= Double.parseDouble(String.valueOf(input.getText()));
                operator = '*';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else if (operator == '-')
            {
                fnum -= Double.parseDouble(String.valueOf(input.getText()));
                operator = '*';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else if (operator == '/')
            {
                fnum /= Double.parseDouble(String.valueOf(input.getText()));
                operator = '*';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else if (operator == '+')
            {
                fnum += Double.parseDouble(String.valueOf(input.getText()));
                operator = '*';
                input.setText(String.valueOf(fnum));
                sequence = true;
            }
            else
            {
                fnum = Double.parseDouble(String.valueOf(input.getText()));
                operator = '*';
                input.setText("");
            }
        }
        catch (NumberFormatException e)
        {
            input.setText("Error");
            operator = ' ';
        }
    }
    public void equalsClick(View v)
    {

        EditText input = findViewById(R.id.editText);
        try {//Once the user has finished entering the operation the calculations will be performed and displayed
            double num2 = Double.parseDouble(String.valueOf(input.getText()));
            String test = String.valueOf(input.getText());
            if (operator == '+')
            {
                operator = ' ';
                addition(fnum, num2);

            }
            else if (operator == '-')
            {
                operator = ' ';
                subtraction (fnum, num2);
            }
            else if (operator == '*')
            {
                operator = ' ';
                multiplication (fnum, num2);
            }
            else if (operator == '/')
            {
                operator = ' ';
                division (fnum, num2);
            }
            else
            {
                input.setText("0");
            }
        }
        catch (NumberFormatException e)
        {
            input.setText("Error");
            operator = ' ';
        }


    }

    public void zeroClick(View v) {//If the user presses a button with a number on it, that number is displayed
        EditText input = findViewById(R.id.editText);
        if(input.getText().toString().equals("0"))
        {//If its the first number entered it removes the 0
            input.setText("");
        }
        if(sequence)
        {//If its a sequence it clears the textbox
            input.setText("");
        }//If it is a regular input it appends the number
            input.append("0");
            sequence = false;
    }
    public void oneClick(View v) {
        EditText input = findViewById(R.id.editText);
        if(input.getText().toString().equals("0"))
        {
            input.setText("");
        }
        if(sequence)
        {
            input.setText("");
        }
            input.append("1");
            sequence = false;
    }
    public void twoClick(View v) {
        EditText input = findViewById(R.id.editText);
        if(input.getText().toString().equals("0"))
        {
            input.setText("");
        }
        if(sequence)
        {
            input.setText("");
        }
        input.append("2");
        sequence = false;
    }
    public void threeClick(View v) {
        EditText input = findViewById(R.id.editText);
        if(input.getText().toString().equals("0"))
        {
            input.setText("");
        }
        if(sequence)
        {
            input.setText("");
        }
        input.append("3");
        sequence = false;
    }
    public void fourClick(View v) {
        EditText input = findViewById(R.id.editText);
        if(input.getText().toString().equals("0"))
        {
            input.setText("");
        }
        if(sequence)
        {
            input.setText("");
        }
        input.append("4");
        sequence = false;
    }
    public void fiveClick(View v) {
        EditText input = findViewById(R.id.editText);
        if(input.getText().toString().equals("0"))
        {
            input.setText("");
        }
        if(sequence)
        {
            input.setText("");
        }
        input.append("5");
        sequence = false;
    }
    public void sixClick(View v) {
        EditText input = findViewById(R.id.editText);
        if(input.getText().toString().equals("0"))
        {
            input.setText("");
        }
        if(sequence)
        {
            input.setText("");
        }
        input.append("6");
        sequence = false;
    }
    public void sevenClick(View v) {
        EditText input = findViewById(R.id.editText);
        if(input.getText().toString().equals("0"))
        {
            input.setText("");
        }
        if(sequence)
        {
            input.setText("");
        }
        input.append("7");
        sequence = false;
    }
    public void eightClick(View v) {
        EditText input = findViewById(R.id.editText);
        if(input.getText().toString().equals("0"))
        {
            input.setText("");
        }
        if(sequence)
        {
            input.setText("");
        }
        input.append("8");
        sequence = false;
    }
    public void nineClick(View v) {
        EditText input = findViewById(R.id.editText);
        if(input.getText().toString().equals("0"))
        {
            input.setText("");
        }
        if(sequence)
        {
            input.setText("");
        }
        input.append("9");
        sequence = false;
    }
    public void clearClick(View v) {
        fnum = 0;
        operator = ' ';
        EditText input = findViewById(R.id.editText);
        input.setText("0");
    }

    private double addition ( double num1, double num2){//Performs the calculation and displays it
        EditText input = findViewById(R.id.editText);
        double total = num1 + num2;
        input.setText(String.valueOf(total));
        fnum = total;
        operator = ' ';
        return total;
    }
    private double subtraction ( double num1, double num2){
        EditText input = findViewById(R.id.editText);
        double total = num1 - num2;
        input.setText(String.valueOf(total));
        fnum = total;
        return total;
    }
    private double division ( double num1, double num2){
        EditText input = findViewById(R.id.editText);
        double total = num1 / num2;
        input.setText(String.valueOf(total));
        fnum = total;
        return total;
    }
    private double multiplication ( double num1, double num2){
        EditText input = findViewById(R.id.editText);
        double total = num1 * num2;
        input.setText(String.valueOf(total));
        fnum = total;
        return total;
    }


}

