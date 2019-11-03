package org.falcon.app;

import javafx.fxml.FXML;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class SecondaryController {
    private static BlockingQueue networkQueue;

    public void setQueue(BlockingQueue networkQueue) {
        this.networkQueue = networkQueue;
    }

    @FXML
    private void sendGyroCal() throws IOException {
        JSONObject request = new JSONObject();
        request.put("command", "gyro_cal");
        networkQueue.add(request.toString());
    }

    @FXML
    private void sendMagCal() throws IOException {
        JSONObject request = new JSONObject();
        request.put("command", "mag_cal");
        networkQueue.add(request.toString());
    }
}
