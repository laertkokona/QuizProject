/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.sample.project;

import java.util.ArrayList;

/**
 *
 * @author gprok
 */
public class Question {
    String question;
    String correctAnswer;
    ArrayList<String> wrongAnswers;
    
    public Question(String q, String ca, ArrayList<String> wa) {
        question = q;
        correctAnswer = ca;
        wrongAnswers = wa;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList<String> getWrongAnswers() {
        return wrongAnswers;
    }
    
    public String toString() {
        return "Q: " + question + "\nC.A.: " + correctAnswer; 
    }
    
}
