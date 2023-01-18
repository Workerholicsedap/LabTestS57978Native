//Name :Muhammad Afiq Bin Ausrizan
//No matric : S57978
package com.example.mindsharpenerquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    TextView equationText;
    EditText userInput;
    Button button;
    TextView displayResult;
    TextView displayStreak;

    RadioButton radioButton2,radioButton3,radioButton4;
    QuizLogic quizzer;

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equationText = findViewById(R.id.equationText);

        button = findViewById(R.id.button);

        userInput = findViewById(R.id.userInput);
        displayResult = findViewById(R.id.displayResult);
        displayStreak = findViewById(R.id.displayStreak);

        radioButton2 =findViewById(R.id.radioButton2);
        radioButton3 =findViewById(R.id.radioButton3);
        radioButton4 =findViewById(R.id.radioButton4);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();

        quizzer = new QuizLogic();//start quiz logic java , call quizlogic
        showNewEquation(quizzer);

        displayStreak.setText("Current Streak: 0"); //display streak point that include in QuizLogic class

        final Handler handler = new Handler(Looper.getMainLooper());

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                userInput.onEditorAction(EditorInfo.IME_ACTION_DONE);//take value form userinput

                result = quizzer.answerIsCorrect(userInput.getText().toString()); //call balik fun
                if (result.equals("Correct")) {
                    displayResult.setText(result.concat("!"));
                } else {
                    String solution = ": ".concat(Long.toString(quizzer.getSolution()));
                    displayResult.setText(result.concat(solution));
                }

                int currentStreak = quizzer.getStreak();//call currentstreak  from function is correct
                displayStreak.setText("Current Streak: ".concat(String.valueOf(currentStreak)));

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        }
                        catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                            ex.printStackTrace();
                        }
                        showNewEquation(quizzer);
                        userInput.getText().clear();
                        displayResult.setText("");
                    }
                });
            }
        });
    }

    public void showNewEquation(QuizLogic quizzer) {

        quizzer.generateEquation();

        TextView equationText = findViewById(R.id.equationText);
        equationText.setText(String.format("%s =", quizzer.getEquation()));
        showKeyboard(userInput);
    }

    public void showKeyboard(View inputField) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(inputField, InputMethodManager.SHOW_IMPLICIT);
    }

    public void clickeasy(View view){
        Toast.makeText(this, "Bro choose easy mode", Toast.LENGTH_SHORT).show();
    }

    public void clickmedium(View view){
        Toast.makeText(this, "Bro choose easy mode", Toast.LENGTH_SHORT).show();
    }

    public void clickhard(View view){
        Toast.makeText(this, "Bro choose easy mode", Toast.LENGTH_SHORT).show();
    }
}