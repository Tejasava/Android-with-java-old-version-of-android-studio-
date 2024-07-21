package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuizApp {

    // MainActivity.java
    public static class MainActivity extends AppCompatActivity {

        public static int score = 0; // Static score to be accessed across activities

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Button b1 = findViewById(R.id.button);
            RadioButton r1 = findViewById(R.id.radiovideo1); // "Who is the current Prime Minister of India in 2024?"
            RadioButton r2 = findViewById(R.id.radiovideo2); // "Rahul Gandhi"
            RadioButton r3 = findViewById(R.id.radiovideo3); // "Arvind Kejriwal"
            RadioButton r4 = findViewById(R.id.radiovideo4); // "Mamata Banerjee"

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (r1.isChecked()) {
                        ++score; // Correct answer is A) Narendra Modi
                    } else {
                        --score;
                    }
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    // SecondActivity.java
    public static class SecondActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_second);

            Button b1 = findViewById(R.id.button);
            RadioButton r5 = findViewById(R.id.radiovideo5); // "Which party won the most seats in the 2024 Lok Sabha elections?"
            RadioButton r6 = findViewById(R.id.radiovideo6); // "Indian National Congress (INC)"
            RadioButton r7 = findViewById(R.id.radiovideo7); // "Aam Aadmi Party (AAP)"
            RadioButton r8 = findViewById(R.id.radiovideo8); // "Trinamool Congress (TMC)"

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (r5.isChecked()) {
                        ++MainActivity.score; // Correct answer is A) Bharatiya Janata Party (BJP)
                    } else {
                        --MainActivity.score;
                    }
                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    // ThirdActivity.java
    public static class ThirdActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_third);

            Button b1 = findViewById(R.id.button);
            RadioButton r9 = findViewById(R.id.radiovideo9); // "Who is the current Finance Minister of India in 2024?"
            RadioButton r10 = findViewById(R.id.radiovideo10); // "P. Chidambaram"
            RadioButton r11 = findViewById(R.id.radiovideo11); // "Arun Jaitley"
            RadioButton r12 = findViewById(R.id.radiovideo12); // "Sushil Modi"

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (r9.isChecked()) {
                        ++MainActivity.score; // Correct answer is A) Nirmala Sitharaman
                    } else {
                        --MainActivity.score;
                    }
                    Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    // FourthActivity.java
    public static class FourthActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fourth);

            TextView t1 = findViewById(R.id.textView3);
            Button b1 = findViewById(R.id.button4);

            t1.setText("Your score is " + MainActivity.score);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.score = 0; // Reset score
                    Intent intent = new Intent(FourthActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}
