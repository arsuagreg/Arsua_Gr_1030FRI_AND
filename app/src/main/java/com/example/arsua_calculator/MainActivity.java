package com.example.arsua_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txtDisplay;
    private StringBuilder input = new StringBuilder();
    private double operand1, operand2;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtDisplay = findViewById(R.id.txtDisplay);
        setupButtons();
    }
    private void setupButtons() {
        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide,
                R.id.btnDecimal, R.id.btnEquals, R.id.btnClear, R.id.btnBackspace
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(this::onButtonClick);
        }
    }
    private void onButtonClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        int id = button.getId();

        if (id == R.id.btnEquals) {
            calculate();
        } else if (id == R.id.btnAdd || id == R.id.btnSubtract ||
                id == R.id.btnMultiply || id == R.id.btnDivide) {
            if (input.length() > 0) {
                if (!operator.isEmpty()) {
                    calculate(); // Calculate any previous operation before adding a new operator
                }
                operand1 = Double.parseDouble(input.toString());
                operator = buttonText;
                input.setLength(0); // Clear the input for the next number
                updateDisplay(); // Update the display
            }
        } else if (id == R.id.btnDecimal) {
            if (input.length() == 0) {
                // Do nothing if input is empty
            } else if (!input.toString().contains(".")) {
                // Only allow one decimal point
                input.append(".");
            }
        } else if (id == R.id.btnClear) {
            input.setLength(0);
            operator = "";
            txtDisplay.setText("0");
        } else if (id == R.id.btnBackspace) {
            if (input.length() > 0) {
                input.setLength(input.length() - 1);
                if (input.length() == 0) {
                    txtDisplay.setText("0");
                } else {
                    updateDisplay();
                }
            }
        } else {
            // input constraints
            String currentInput = input.toString();
            if (currentInput.length() < 5) { // 2 digits + 1 decimal point + 2 decimal places = 5
                if (currentInput.contains(".")) {
                    // 2 decimal places
                    int decimalIndex = currentInput.indexOf(".");
                    if (currentInput.length() - decimalIndex <= 2) {
                        input.append(buttonText);
                    }
                } else {
                    input.append(buttonText);
                }
            }
            updateDisplay();
        }
    }
    private void updateDisplay() {
        String displayText = input.toString();
        if (!operator.isEmpty()) {
            if (input.length() > 0) {
                displayText = formatNumber(operand1) + " " + operator + " " + input.toString();
            } else {
                displayText = formatNumber(operand1) + " " + operator;
            }
        }
        txtDisplay.setText(displayText);
    }
    private void calculate() {
        if (input.length() > 0) {
            operand2 = Double.parseDouble(input.toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "x":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        txtDisplay.setText("Error");
                        return;
                    }
                    break;
            }

            // Display the result with correct formatting
            String resultText = formatNumber(operand1) + " " + operator + " " + formatNumber(operand2) + "\n= " + formatNumber(result);
            txtDisplay.setText(resultText);
            input.setLength(0);
            operator = "";
        }
    }
    private String formatNumber(double number) {
        if (number % 1 == 0) {
            return String.format("%d", (int) number); // No decimal places
        } else {
            return String.format("%.2f", number); // 2 decimal places
        }
    }
}
