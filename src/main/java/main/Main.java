package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;


public class Main extends Application {

    double x, y;

    @Override
    public void start(Stage primaryStage) throws  Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/menu.fxml")));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });

        primaryStage.setScene(new Scene(root, 869, 604));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
