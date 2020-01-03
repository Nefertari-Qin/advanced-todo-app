package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ToDoApp extends Application {
    private GridPane loginPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initializeLoginPane();
        primaryStage.setTitle("Done!");

        primaryStage.show();
    }

    private void initializeLoginPane() {
        loginPane = new GridPane();
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setHgap(15);
        loginPane.setVgap(10);
        loginPane.setPadding(new Insets(25,20,25,20));

        Text loginTitle = new Text("LOGIN");
        loginTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    }
}
