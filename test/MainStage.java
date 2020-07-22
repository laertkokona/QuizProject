package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage extends Application implements Controller {

    private Stage mainStage;
    private LoginScene loginScene;
    private MainMenuScene mainMenuScene;
    private QuizScene quizScene;
    private ScoreScene scoreScene;
    
    @Override
    public void start(Stage mainStage) throws Exception {
        this.mainStage = mainStage;
        loginScene = new LoginScene();
        loginScene.setController(this);
        setScene(loginScene.getScene());
        
        mainStage.setTitle("QUIZ");

        mainStage.show();
    }
    
    public void setScene(Scene scene) {
        mainStage.setScene(scene);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void displayMainMenuScene() {
        mainMenuScene = new MainMenuScene();
        mainMenuScene.setController(this);
        setScene(mainMenuScene.getScene());
    }

    @Override
    public void displayQuizScene() {
        quizScene = new QuizScene();
        quizScene.setController(this);
        setScene(quizScene.getScene());
    }

    @Override
    public void displayScoreScene(int score) {
        scoreScene = new ScoreScene(score);
        scoreScene.setController(this);
        setScene(scoreScene.getScene());
    }
    
}
