package com.example.soleclipsado.views.alert;

import javafx.scene.control.Alert;

/**
 * This class is an adapter for generating an alert.
 * @author Felipe Garcia
 * @version 1.0
 */
public class AlertBox implements AlertBoxInterface {
    /**
     * This method generates a window to notify the user if they have made a mistake.
     */
    @Override
    public void showAlert(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
