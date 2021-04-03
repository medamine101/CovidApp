package com.example.covidapp;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private final QuestionLibrary questionLibrary = new QuestionLibrary();

    private TextView questionView;
    private Button buttonChoice1;
    private Button buttonChoice2;

    private String answer;
    private int score = 0;
    private int questionNumber = 0;
    private double percentage;

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_home, container, false );

        questionView = view.findViewById( R.id.questions );
        buttonChoice1 = view.findViewById( R.id.choice1 );
        buttonChoice2 = view.findViewById( R.id.choice2 );

        updateQuestion();

        // after click on the first button
        buttonChoice1.setOnClickListener( view1 -> {
            if (buttonChoice1.getText() == "Try Again") {
                score = 0;
                questionNumber = 0;
                buttonChoice2.setVisibility( View.VISIBLE );
                questionView.setText( questionLibrary.getQuestion( questionNumber ) );
                buttonChoice1.setText( questionLibrary.getChoice1( questionNumber ) );
                buttonChoice2.setText( questionLibrary.getChoice2( questionNumber ) );
            }
            if (buttonChoice1.getText() == answer) {
                score = score + 1;
            }
            if (questionNumber == questionLibrary.getLength()) {
                if(score-1 < 3){
                    updateScore( "low" );
                }
                else if (score-1 > 5){
                    updateScore( "high" );
                }
                else {
                    updateScore( "medium" );
                }
            }
            if (questionNumber < questionLibrary.getLength()) {
                updateQuestion();
            }
        } );

        // after click on the second button
        buttonChoice2.setOnClickListener( view12 -> {
            if (buttonChoice2.getText() == answer) {
                score = score + 1;
            }

            if (questionNumber == questionLibrary.getLength()) {
                if(score-1 < 3){
                    updateScore( "low" );
                }
                else if (score-1 > 5){
                    updateScore( "high" );
                }
                else {
                    updateScore( "medium" );
                }
            }
            if (questionNumber < questionLibrary.getLength()) {
                updateQuestion();
            }
        } );
        return view;
    }
    public void updateQuestion () {
        questionView.setText( questionLibrary.getQuestion( questionNumber ) );
        buttonChoice1.setText( questionLibrary.getChoice1( questionNumber ) );
        buttonChoice2.setText( questionLibrary.getChoice2( questionNumber ) );
        answer = questionLibrary.getCorrectAnswer( questionNumber );
        questionNumber++;
    }

    @SuppressLint("SetTextI18n")
    public void updateScore (String point){
        questionView.setText( "The chance of you having Covid-19 is " + point );
        buttonChoice1.setText( "Try Again" );
        buttonChoice2.setVisibility( View.GONE );
    }
}
