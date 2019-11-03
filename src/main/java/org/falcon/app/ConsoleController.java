package org.falcon.app;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ConsoleController {
    private static BlockingQueue networkQueue;
    @FXML
    ListView consoleList;

    TimerTask task;
    Timer timer;

    @FXML
    private void initialize() {
        task = new TimerTask()
        {
            public void run()
            {
                Platform.runLater(new Runnable() {
                    public void run() {
                        String outputLine;
                        while ((outputLine = (String) networkQueue.poll()) != null) {
                            ObservableList<String> items = consoleList.getItems();
                            items.add(outputLine);
                            consoleList.setItems(items);
                            consoleList.scrollTo(items.size());
                        }
                    }
                });
            }

        };
        timer = new Timer("Console Timer", true);
        timer.schedule(task, 0,100);
    }

    public void setQueue(BlockingQueue networkQueue) {
        this.networkQueue = networkQueue;
    }
}
