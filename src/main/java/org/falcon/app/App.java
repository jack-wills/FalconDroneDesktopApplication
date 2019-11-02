package org.falcon.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent attitude = loadFXML("attitude");
        Parent console = loadFXML("console");
        Parent secondary = loadFXML("secondary");
        GridPane root = new GridPane();

        RowConstraints rcons1 = new RowConstraints();
        rcons1.setVgrow(Priority.ALWAYS);

        RowConstraints rcons2 = new RowConstraints();
        rcons2.setVgrow(Priority.ALWAYS);


        ColumnConstraints cons1 = new ColumnConstraints();
        cons1.setHgrow(Priority.ALWAYS);

        ColumnConstraints cons2 = new ColumnConstraints();
        cons2.setHgrow(Priority.ALWAYS);

        root.getColumnConstraints().addAll(cons1, cons2);

        root.getRowConstraints().addAll(rcons1, rcons2);

        root.add(attitude, 0, 0);
        root.add(console, 1, 0);
        root.add(secondary, 0, 1);

        scene = new Scene(root);

        scene.getStylesheets().add(App.class.getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setHeight(700);
        stage.setWidth(1100);
        stage.setTitle("Falcon Control");
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}