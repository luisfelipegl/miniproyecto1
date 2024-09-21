package com.example.soleclipsado.views.alert;

/**
 * This interface is implemented in the AlertBox class to generate an alert.
 * @author Felipe Garcia
 * @version 1.0
 */
public interface AlertBoxInterface {
    /**
     * This method is the template that AlertBox will have for generating alerts.
     * @param title title of the alert window
     * @param header head of the window
     * @param message Message that will be displayed.
     */
    public void showAlert(String title, String header, String message);
}
