package unistore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/unistore/view/pages/auth/login.fxml"));
        Scene scene = new Scene(loader.load()); 
        stage.setScene(scene);
        stage.setTitle("Unistore App");
        stage.show();
        stage.setWidth(1300);
        stage.setHeight(650);
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
