package com.example.soleclipsado.controllers;

import com.example.soleclipsado.models.Game;
import com.example.soleclipsado.views.GameView;
import com.example.soleclipsado.views.alert.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.Normalizer;

/**
 * This class is the controller for the Start window.
 * @author Felipe Garcia
 * @version 1.0
 */
public class StartController {

    @FXML
    private TextField word;

    /**
     * This method normalizes the word to send it to the Game model.
     * @param event The event detect
     * @throws IOException IOException if an error occurs during the process
     */
    @FXML
    void onHandlePlayButton(ActionEvent event) throws IOException {
        String error, wordSecret;
        Game game = new Game();

        wordSecret = word.getText();
        wordSecret = wordSecret.toLowerCase();
        wordSecret = Normalizer.normalize(wordSecret, Normalizer.Form.NFD);
        wordSecret = wordSecret.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        error = game.setSecretWord(wordSecret);

        if (!error.isEmpty()) {
            AlertBox alertBox = new AlertBox();
            alertBox.showAlert("Error", "Error al ingresar la palabra secreta", error);
        } else {
            GameView.getInstance().getGameController().setGame(game);

            Stage stage = (Stage) word.getScene().getWindow();
            stage.close();
        }
    }

}