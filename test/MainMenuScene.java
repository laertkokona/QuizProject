package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import se.sample.project.AlertBox;
import se.sample.project.Category;
import se.sample.project.Question;

public class MainMenuScene extends GenericScene{
    
    public static String difficulty;
    
    public static int categoryId;
    
        
    public MainMenuScene(){
        
        
        GridPane grid = new GridPane(); 
        
        Label lbl_difficulty = new Label("Difficulty: ");
        Label lbl_category = new Label("Category: ");
        
        ComboBox difficultyCombo = new ComboBox();
        difficultyCombo.getItems().add("easy");
        difficultyCombo.getItems().add("medium");
        difficultyCombo.getItems().add("hard");
        
        ComboBox categoryCombo = new ComboBox();
        
        getCategories().forEach((category) -> {
            categoryCombo.getItems().add(category.getName());
        });
        
        Button btn_start = new Button("Start Quiz");
        btn_start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override        
            public void handle(MouseEvent arg0) {
                if (difficultyCombo.getValue() == null || categoryCombo.getValue() == null) {
                    AlertBox.display("Error", "Please select from both comboboxes!");
                } else {
                    categoryId = getCategories().get(categoryCombo.getSelectionModel().getSelectedIndex()).getId();
                    difficulty = difficultyCombo.getValue().toString();
                    getQuestions(difficulty, categoryId);
                    
                    observer.displayQuizScene();
                }
            }
        });
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(lbl_difficulty, 1, 1);
        grid.add(difficultyCombo, 2, 1);
        grid.add(lbl_category, 1, 2);
        grid.add(categoryCombo, 2, 2);
        grid.add(btn_start, 1, 3);
        
        scene = new Scene(grid, 500, 300);
        
    }
    
    private ArrayList<Category> getCategories(){
        ArrayList<Category> categoryList = new ArrayList<>();
        try {
            InputStream is = new URL("https://opentdb.com/api_category.php").openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line = reader.readLine();
            
            JSONObject json = new JSONObject(line);
            JSONArray categories = json.getJSONArray("trivia_categories");
            
            for(int i = 0; i < categories.length(); i++){
                
                JSONObject category = categories.getJSONObject(i);
                int id = category.getInt("id");
                String name = category.get("name").toString();
                Category cat = new Category(id, name);
                categoryList.add(cat);
                                
            }
            
        } catch (IOException | JSONException e) {
            System.out.println("Error!");
        }
     
        return categoryList;
    }
    
    private ArrayList<Question> setTypes(String dif, int cat, String type){
        ArrayList<Question> qList = new ArrayList<>();
        try {
            InputStream is = new URL("https://opentdb.com/api.php?amount=5&category=" + String.valueOf(cat) + "&difficulty=" + dif + "&type=" + type).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line = reader.readLine();

            JSONObject json = new JSONObject(line);
            JSONArray questions = json.getJSONArray("results");

            for (int i = 0; i < questions.length(); i++) {

                JSONObject question = questions.getJSONObject(i);
                String q = question.get("question").toString();
                String ca = question.get("correct_answer").toString();
                JSONArray wrongAnswers = question.getJSONArray("incorrect_answers");
                ArrayList<String> wa = new ArrayList<>();
                for (int j = 0; j < wrongAnswers.length(); j++) {
                    wa.add(wrongAnswers.get(j).toString());
                }
                Question currentQuestion = new Question(q, ca, wa);
                
                qList.add(currentQuestion);
                

            }
            if(type != "boolean"){
                qList.addAll(setTypes(dif, cat, "boolean"));
            }
        } catch (Exception e) {
            System.out.println("??");
        }
        
        return qList;
    }
    
    private void getQuestions(String dif, int cat){
        setQuestionList(setTypes(dif, cat, "multiple"));
        
    }
    
    
}
