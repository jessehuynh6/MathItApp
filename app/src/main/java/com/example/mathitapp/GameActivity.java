package com.example.mathitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    public int operand1;
    public int operand2;
    public int operation;
    public int answer;
    public int max;
    public int min;

    public final int add_operator = 0;
    public final int subtract_operator = 1;
    public final int multiply_operator = 2;
    public final int divide_operator = 3;

    public String[] operations = {"+", "-", "x", "/"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Display random math question in textview to user
        Intent intent = getIntent();
        TextView question = findViewById(R.id.questionText);
        question.setText(chooseQuestion());

    }

    private String chooseQuestion() {
        Random rand = new Random();
        operand1 = getOperand(100, 1);
        operand2 = getOperand(100, 1);
        //operation = rand.nextInt(operations.length);
        operation = 3;

        //Conditional Test #1: A subtract question cannot have a negative answer
        if (operation == subtract_operator) {
            while (operand1 < operand2) {
                operand1 = getOperand(100,1);
                operand2 = getOperand(100,1);
            }
        }
        //Conditional Test #2: A divide question must have a whole number answer
            else if (operation == divide_operator) {
                  while (!(operand1 % operand2 == 0) || !(operand1 > operand2)) {
                    operand1 = getOperand(100,1);
                    operand2 = getOperand(100,1);
                }
        }

        //Determine the answer for each question
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


}