package com.example.covidapp;

public class QuestionLibrary {

    private final String[] questions = {
            "1. Have you experienced any symptoms of COVID-19? \n\n" +
                    "(ex. sore throat, fever over 100, cough, headache, shortness of breath or difficulty breathing, fatigue, " +
                    "vomiting, body aches, loss of taste or smell.)",
            "2. Have someone in your house experienced any symptoms of COVID-19?  \n\n" +
                    "(ex. sore throat, fever over 100, cough, headache, shortness of breath or difficulty breathing, " +
                    "fatigue, vomiting, body aches, loss of taste or smell.)",
            "3. Have you been asked to quarantine or been exposed to a person who has is confirmed positive for COVID-19 in the past 2 weeks?",
            "4. Have someone in your home been asked to quarantine or been exposed to a person who has is confirmed positive for COVID-19 in the past 2 weeks?",
            "5. Have you or someone in your house traveled outside of the United States in the past week?",
            "6. Have you or someone in your house recently traveled to areas with a high risk of COVID-19?",
            "7. Do you feel like you are at a higher risk for contracting COVID-19?"
    };

    private final String[][] choices = {
            {"yes", "no"},
            {"yes", "no"},
            {"yes", "no"},
            {"yes", "no"},
            {"yes", "no"},
            {"yes", "no"},
            {"yes", "no"}
    };

    public int getLength(){ return questions.length;}


    private final String[] correctAnswers = {"yes", "yes", "yes", "yes", "yes", "yes", "yes"};

    public String getQuestion(int a){
        return questions[a];
    }

    public String getChoice1(int a){
        return choices[a][0];
    }

    public String getChoice2(int a){
        return choices[a][1];
    }

    public String getCorrectAnswer(int a){
        return correctAnswers[a];
    }

}
