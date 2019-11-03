package org.falcon.app;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class AttitudeController {

    @FXML
    ImageView backgroundImage;
    @FXML
    HBox pane;

    TimerTask task;
    Timer timer;


    @FXML
    private void initialize() {
        backgroundImage.fitWidthProperty().bind(pane.widthProperty());
        backgroundImage.fitHeightProperty().bind(pane.heightProperty());
        backgroundImage.setPreserveRatio(true);
        RotateTransition rt = new RotateTransition(Duration.millis(1000), pane);
        rt.setAxis(Rotate.Z_AXIS);
        task = new TimerTask()
        {
            public void run()
            {
                rt.setToAngle((System.currentTimeMillis()/100%360)-180);
                rt.play();
            }

        };
        timer = new Timer("Attitude Timer", true);
        timer.schedule(task, 0,1000);
    }
}
