package org.falcon.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static BlockingQueue serialPassThroughQueue;
    private static BlockingQueue networkSendQueue;
    private static BlockingQueue networkReceiveQueue;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("attitude.fxml"));
        Parent attitude = fxmlLoader.load();
        AttitudeController attitudeController = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(App.class.getResource("console.fxml"));
        Parent console = fxmlLoader.load();
        ConsoleController consoleController = fxmlLoader.getController();
        consoleController.setQueue(networkReceiveQueue);

        fxmlLoader = new FXMLLoader(App.class.getResource("secondary.fxml"));
        Parent secondary = fxmlLoader.load();
        SecondaryController secondaryController = fxmlLoader.getController();
        secondaryController.setQueue(networkSendQueue);
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
        serialPassThroughQueue = new ArrayBlockingQueue(1024);
        networkSendQueue = new ArrayBlockingQueue(1024);
        networkReceiveQueue = new ArrayBlockingQueue(1024);

        NetworkController networkThread = new NetworkController(networkSendQueue);
        networkThread.setDaemon(true);
        networkThread.start();

        SerialPassThroughNetworkController serialPassThroughNetworkControllerThread = new SerialPassThroughNetworkController(serialPassThroughQueue);
        serialPassThroughNetworkControllerThread.setDaemon(true);
        serialPassThroughNetworkControllerThread.start();

        Server serverThread = new Server(networkReceiveQueue);
        serverThread.setDaemon(true);
        serverThread.start();

        launch();
    }

}