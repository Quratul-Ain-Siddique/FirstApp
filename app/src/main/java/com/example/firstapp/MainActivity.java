package com.example.firstapp;

import static java.lang.String.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    // views declarations
    private EditText number;
    private TextView tvSqrtResult;
    private TextView tvSquareResult;
    private EditText firstNumber;
    private EditText secondNumber;
    private Button calculateSquare;
    private Button calculateSquareRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initListeners();
    }

    /**
     * views initialization
     */
    private void initViews() {
        number = findViewById(R.id.number);
        tvSqrtResult = findViewById(R.id.tvSqrtResult);
        tvSquareResult = findViewById(R.id.tvSquareResult);
        firstNumber = findViewById(R.id.firstNumber);
        secondNumber = findViewById(R.id.secondNumber);
        calculateSquare = findViewById(R.id.btnCalculateSquare);
        calculateSquareRoot = findViewById(R.id.btnCalculateSquareRoot);
    }

    /**
     * listeners setup
     */
    private void initListeners() {
        calculateSquareRoot.setOnClickListener(v -> onSquareClick());
        calculateSquare.setOnClickListener(v -> onCalculateSquareClick());
    }

    /**
     * Called on square root button click
     */
    private void onSquareClick() {
        Toast.makeText(MainActivity.this, "Calculating Square Root ", Toast.LENGTH_SHORT).show();
        int a = Integer.parseInt(number.getText().toString());
        double squareRoot = Utils.calculateSquareRoot(a);
        tvSquareResult.setText(valueOf(squareRoot));
    }

    /**
     * Called on square button click
     */
    private void onCalculateSquareClick() {
        Log.i(TAG, "Calling load data");
        loadDataFromServer(new SuccessListener() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "onSuccess: ");
            }
        });
        Log.i(TAG, "Continuing normal execution");
        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "Calculating Square ", Toast.LENGTH_SHORT).show();
        int a = Integer.parseInt(firstNumber.getText().toString());
        int b = Integer.parseInt(secondNumber.getText().toString());
        int square = Utils.calculateSquare(a, b);
        tvSqrtResult.setText(square);

    }

    interface SuccessListener{
        void onSuccess();
    }
    void loadDataFromServer(SuccessListener successListener){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO
                try {
                    Thread.sleep(4000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                successListener.onSuccess();
            }
        });
        t.start();
    }
}