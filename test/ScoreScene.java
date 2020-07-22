package test;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ScoreScene extends GenericScene {
    
    
    public ScoreScene(int score) {
        
        GridPane grid = new GridPane(); 
        Label lbl = new Label(score + "/10");
        
        Button loginBtn = new Button("PLAY AGAIN");
        loginBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                observer.displayMainMenuScene();
            }
        });
        grid.setAlignment(Pos.CENTER);
        grid.add(lbl, 1, 0);
        grid.add(loginBtn, 1, 1);
        
        scene = new Scene(grid, 300, 300);
        
    }
    
    
}