package test;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class LoginScene extends GenericScene {
    
    
    public LoginScene() {
        
        GridPane grid = new GridPane(); 
        
        Label lbl_username = new Label("Username: ");
        TextField txt_username = new TextField();
        Label lbl_password = new Label("Password: ");
        TextField txt_password = new TextField();
        Button loginBtn = new Button("LOGIN");
        loginBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                observer.displayMainMenuScene();
            }
        });
        grid.setAlignment(Pos.CENTER);
        grid.add(lbl_username, 1, 1);
        grid.add(txt_username, 2, 1);
        grid.add(lbl_password, 1, 2);
        grid.add(txt_password, 2, 2);
        grid.add(loginBtn, 1, 3);
        
        scene = new Scene(grid, 300, 300);
        
    }
    
    
}
