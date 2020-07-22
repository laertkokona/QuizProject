package test;

import java.util.ArrayList;
import java.util.Collections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import se.sample.project.Question;

public class QuizScene extends GenericScene {
    
    private static int counter = 0;
    private int right;
    private Label question;
    private ArrayList<String> answers;

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        //QuizScene.right = right;
    }
    
    public QuizScene() {        
        GridPane grid = new GridPane(); 
        questionList = new ArrayList<Question>();
        
        question = new Label(questionList.get(counter).getQuestion());
        answers = new ArrayList<>();
        grid.setAlignment(Pos.CENTER);
        grid.add(question, 0, 0);
        ToggleGroup group = new ToggleGroup();
        int k = 1;
        for (String answer : getShuffeledAnswers(getQuestionList().get(counter))) {
            RadioButton rb = new RadioButton(answer);
            rb.setToggleGroup(group);
            grid.add(rb, 0, k);
            k++;
        }

        Button btn = new Button("NEXT");
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if(group.getSelectedToggle().toString().equals(questionList.get(counter).getCorrectAnswer())){
                    right++;
                }
                if(counter < 9) {
                    counter++;
                    observer.displayQuizScene();
                }
                else {
                    // update DB   
                    counter = 0;
                    observer.displayScoreScene(right);
                }
            }
        });
        grid.add(btn, 0, 5);
        
        scene = new Scene(grid, 300, 300);
        
    }
    
    public ArrayList<String> getShuffeledAnswers(Question q){
        ArrayList<String> answers = new ArrayList<>();
        answers.add(q.getCorrectAnswer());
        answers.addAll(q.getWrongAnswers());
        Collections.shuffle(answers);
        return answers;
    }
    
}