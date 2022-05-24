package pl.pm.raportgenerator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Raport Generator");
        stage.setScene(scene);
        HelloController controller = fxmlLoader.getController();
        stage.setOnHidden(e -> {
            try {
                controller.shutdown();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}