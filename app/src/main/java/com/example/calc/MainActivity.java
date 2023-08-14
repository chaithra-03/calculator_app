package com.example.calc;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private StringBuilder inputBuffer; // To store the user input
    private double currentValue = 0;
    private String currentOperator = "";
    private boolean operatorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        inputBuffer = new StringBuilder();
    }

    public void onDigitClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (operatorClicked) {
            inputBuffer.setLength(0); // Clear the input buffer
            operatorClicked = false;
        }

        if (inputBuffer.length() < 15) {
            inputBuffer.append(buttonText);
            resultTextView.setText(inputBuffer.toString());
        }
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        String operator = button.getText().toString();
        if (inputBuffer.length() > 0) {
            currentValue = Double.parseDouble(inputBuffer.toString());
            inputBuffer.setLength(0);
        }
        currentOperator = operator;
        operatorClicked = true;
    }

    public void onEqualClick(View view) {
        if (inputBuffer.length() > 0) {
            double secondValue = Double.parseDouble(inputBuffer.toString());
            double result = 0;
            switch (currentOperator) {
                case "+":
                    result = currentValue + secondValue;
                    break;
                case "-":
                    result = currentValue - secondValue;
                    break;
                case "*":
                    result = currentValue * secondValue;
                    break;
                case "/":
                    if (secondValue != 0) {
                        result = currentValue / secondValue;
                    } else {
                        resultTextView.setText("Error");
                        return;
                    }
                    break;
            }
            resultTextView.setText(String.valueOf(result));
            inputBuffer.setLength(0);
            inputBuffer.append(result);
            operatorClicked = false;
        }
    }

    public void onClearClick(View view) {
        inputBuffer.setLength(0);
        resultTextView.setText("0");
        currentValue = 0;
        currentOperator = "";
        operatorClicked = false;
    }
}
