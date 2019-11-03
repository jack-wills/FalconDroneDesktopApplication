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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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
        Parent map = loadFXML("map");
        GridPane root = new GridPane();

        RowConstraints rcons1 = new RowConstraints();
        rcons1.setPercentHeight(50);

        RowConstraints rcons2 = new RowConstraints();
        rcons2.setPercentHeight(50);


        ColumnConstraints cons1 = new ColumnConstraints();
        cons1.setPercentWidth(50);

        ColumnConstraints cons2 = new ColumnConstraints();
        cons2.setPercentWidth(50);

        root.getColumnConstraints().addAll(cons1, cons2);

        root.getRowConstraints().addAll(rcons1, rcons2);

        root.add(attitude, 0, 0);
        root.add(console, 1, 0);
        root.add(secondary, 0, 1);
        root.add(map, 1, 1);

        scene = new Scene(root);

        scene.getStylesheets().add(App.class.getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setHeight(700);
        stage.setWidth(1100);
        stage.setMinWidth(400);
        stage.setMinHeight(300);
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