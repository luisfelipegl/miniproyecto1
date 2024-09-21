package com.example.soleclipsado;

import com.example.soleclipsado.views.StartView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class that extends Application.
 * This class is the entry point for the application.
 * @author Felipe Garcia
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Main method that launches the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method that is called at the start of the application.
     * Creates the start view using the singleton pattern.
     *
     * @param primaryStage the main window of the application
     * @throws IOException if an error occurs during the creation of the start view
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        StartView.getInstance();
    }
}
