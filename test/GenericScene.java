package test;

import java.util.ArrayList;
import javafx.scene.Scene;
import se.sample.project.Question;

public class GenericScene {
    
    protected Scene scene;
    protected Controller observer;
    
    protected ArrayList<Question> questionList;

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }
    
    public void addToQuestionList(Question question){
//        GenericScene.questionList.add(question);
    }
    
    public void resetQuestionList(){
//        GenericScene.questionList.removeAll(GenericScene.questionList);
    }

    public void setController(Controller controller) {
        observer = controller;
    }
    
    public Scene getScene() {
        return scene;
    }
    
}
