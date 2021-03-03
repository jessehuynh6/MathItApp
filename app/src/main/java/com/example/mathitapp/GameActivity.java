package com.example.mathitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GameActivity";

    public int operand1;
    public int operand2;
    public int operation;
    public int answer;
    //public int max;
    //public int min;

    public final int add_operator = 0;
    public final int subtract_operator = 1;
    public final int multiply_operator = 2;
    public final int divide_operator = 3;

    //Declare xml elements
    private Button mb1, mb2, mb3, mb4, mb5, mb6, mb7, mb8, mb9, mb0, mbEnter, mbClear;
    private TextView mInput;
    //private TextInputEditText nInput;
    private TextView mAnswer;
    private TextView mQuestion;

    public String[] operations = {"+", "-", "x", "/"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //initialise user input
        mInput = findViewById(R.id.tvInput);
        //nInput = findViewById(R.id.tiInput);

        //initialise buttons
        mb1 = findViewById(R.id.b1);
        mb2 = findViewById(R.id.b2);
        mb3 = findViewById(R.id.b3);
        mb4 = findViewById(R.id.b4);
        mb5 = findViewById(R.id.b5);
        mb6 = findViewById(R.id.b6);
        mb7 = findViewById(R.id.b7);
        mb8 = findViewById(R.id.b8);
        mb9 = findViewById(R.id.b9);
        mb0 = findViewById(R.id.b0);
        mbEnter = findViewById(R.id.bEnter);
        mbClear = findViewById(R.id.bClear);

        //initialise question
        mQuestion = findViewById(R.id.tvQuestion);

        //get intent
        Intent intent = getIntent();

        //set question
        mQuestion.setText(chooseQuestion());

        //set starting input textView
        mInput.setText("= ?");
        //nInput.setText("= ?");

        //handle numpad button clicks
        //handleNumpadClicks();
        mb1.setOnClickListener(this);
        mb2.setOnClickListener(this);
        mb3.setOnClickListener(this);
        mb4.setOnClickListener(this);
        mb5.setOnClickListener(this);
        mb6.setOnClickListener(this);
        mb7.setOnClickListener(this);
        mb8.setOnClickListener(this);
        mb9.setOnClickListener(this);
        mb0.setOnClickListener(this);
        mbEnter.setOnClickListener(this);
        mbClear.setOnClickListener(this);

    }

    private String chooseQuestion() {
        Random rand = new Random();
        operand1 = getOperand(100, 1);
        operand2 = getOperand(100, 1);
        operation = rand.nextInt(operations.length);
        //operation = 3;

        //2 conditions for subtract and divide equations
        if (operation == subtract_operator) {
            while (operand1 < operand2) {
                operand1 = getOperand(100,1);
                operand2 = getOperand(100,1); } }
        else if (operation == divide_operator) {
            while (!(operand1 % operand2 == 0) || !(operand1 > operand2)) {
                operand1 = getOperand(100,1);
                operand2 = getOperand(100,1); } }

        //Set answer
        switch (operation) {
            case add_operator:
                answer = operand1 + operand2;
                break;
            case subtract_operator:
                answer = operand1 - operand2;
                break;
            case multiply_operator:
                answer = operand1*operand2;
                break;
            case divide_operator:
                answer = operand1/operand2;
                break;
            default:
                break;
        }

        String questionTemp = operand1 + " " + operations[operation] + " " + operand2 + " ? ";

            return questionTemp;
    }

    private int getOperand(int max, int min) {
        Random rand = new Random();
        int randNumber = rand.nextInt((max - min) + 1) + min;
          return randNumber;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bEnter) {
            //Handle Enter button
            //to do : retrieve the answer from the TextView
            //to do : compare answer in the Textview with correct answer
        } else if (v.getId() == R.id.bClear) {
            //Handle Clear button
            mInput.setText("= ?");
        } else {
            //Handle Number button
            int enteredNum = Integer.parseInt(v.getTag().toString());
                //Allows answers with more than 1 digit
                if (mInput.getText().toString().endsWith("?")) {
                    mInput.setText("= " + enteredNum);
                } else mInput.append("" + enteredNum);
        }
    }
    }