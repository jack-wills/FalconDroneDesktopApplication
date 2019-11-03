package org.falcon.app;

import java.io.IOException;
import javafx.fxml.FXML;

public class ConsoleController {

    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println("test1234");
        //App.setRoot("secondary");
    }
}
