package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    // Calculator display
    private TextView display;

    // Calculator logic variables
    private String currentNumber = "";
    private String firstNumber = "";
    private String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Your XML layout

        // Initialize the display
        display = findViewById(R.id.display);

        // Set click listeners for all buttons
        setupNumberButtons();
        setupOperationButtons();
        setupEqualsButton();
        setupClearButton();
    }

    private void setupNumberButtons() {
        // Number buttons 0-9
        int[] numberButtonIds = {
                R.id.button1,    // 1
                R.id.button,      // 2 (note this is the ID from your XML)
                R.id.button2,    // 3
                R.id.button3,     // 4
                R.id.button4,     // 5
                R.id.button5,     // 6
                R.id.button6,     // 7
                R.id.button7,     // 8
                R.id.button8,     // 9
                R.id.button9      // 0
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(v -> numberClicked(v));
        }
    }

    private void setupOperationButtons() {
        // Operation buttons +, -, *, /
        int[] operationButtonIds = {
                R.id.button14,   // +
                R.id.button13,   // -
                R.id.button12,   // *
                R.id.button11    // /
        };

        for (int id : operationButtonIds) {
            findViewById(id).setOnClickListener(v -> operationClicked(v));
        }
    }

    private void setupEqualsButton() {
        // Equals button
        findViewById(R.id.button10).setOnClickListener(v -> equalsClicked(v));
    }

    private void setupClearButton() {
        // Clear button
        findViewById(R.id.buttonClear).setOnClickListener(v -> clearClicked(v));
    }

    // Called when a number button (0-9) is clicked
    private void numberClicked(View view) {
        MaterialButton button = (MaterialButton) view;
        String number = button.getText().toString();

        currentNumber += number;
        display.setText(currentNumber);
    }

    // Called when an operation button (+, -, *, /) is clicked
    private void operationClicked(View view) {
        MaterialButton button = (MaterialButton) view;

        // If we don't have a current number, don't do anything
        if (currentNumber.isEmpty()) return;

        firstNumber = currentNumber;
        currentNumber = "";
        operation = button.getText().toString();
    }

    // Called when the equals button (=) is clicked
    private void equalsClicked(View view) {
        // Don't calculate if we don't have two numbers
        if (firstNumber.isEmpty() || currentNumber.isEmpty()) {
            return;
        }

        double num1 = Double.parseDouble(firstNumber);
        double num2 = Double.parseDouble(currentNumber);
        double result = 0;

        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    display.setText("Error");
                    return;
                }
                break;
        }

        // Show the result
        display.setText(String.valueOf(result));

        // Reset for next calculation
        currentNumber = String.valueOf(result);
        firstNumber = "";
        operation = "";
    }

    // Called when the clear button (C) is clicked
    private void clearClicked(View view) {
        currentNumber = "";
        firstNumber = "";
        operation = "";
        display.setText("0");
    }
}