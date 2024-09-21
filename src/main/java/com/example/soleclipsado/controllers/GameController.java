package com.example.soleclipsado.controllers;

import com.example.soleclipsado.models.Game;
import com.example.soleclipsado.views.alert.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.text.Normalizer;
import java.util.ArrayList;

/**
 * This class is the controller for the Game window.
 * @author Felipe Garcia
 * @version 1.0
 */
public class GameController {
    private Game game;

    @FXML
    private HBox lettersContainer;

    @FXML
    private ImageView imageErrors;

    @FXML
    private Label resultLabel;

    @FXML
    private Label aidCountLabel;

    ArrayList<TextField> listTextField = new ArrayList<>();
    AlertBox alertBox = new AlertBox();

    /**
     * This method receives the Game object from the start window and generates the TextFields according to the number of letters in the secret word.
     * @param game Instance of Game with the secret word already defined.
     */
    public void setGame(Game game) {
        this.game = game;

        for (int i = 0; i < game.getWordSize(); i++) {
            listTextField.add(new TextField());
            listTextField.get(i).setMaxWidth(30);

            final int currentIndex = i;
            listTextField.get(i).textProperty().addListener((observable, oldValue, newValue) -> handleTextChange(newValue, currentIndex));
            lettersContainer.getChildren().add(listTextField.get(i));
        }
    }

    /**
     * This method controls the requirements of the TextFields and validates whether the letter is correct.
     * @param newValue Letter to validate.
     * @param currentIndex Index to compare it with the secret word's letter.
     */
    public void handleTextChange(String newValue, int currentIndex) {
        if (newValue.length() == 1){
            if (!newValue.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
                listTextField.get(currentIndex).clear();
                alertBox.showAlert("Error", "Error letra ingresada", "Solo puedes ingresar letras del abecedario");
            } else {
                newValue = Normalizer.normalize(newValue.toLowerCase(), Normalizer.Form.NFD)
                        .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

                if (!game.verifyLetter(newValue.charAt(0), currentIndex)) {
                    listTextField.get(currentIndex).clear();
                    listTextField.get(currentIndex).setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    updateImages();
                    verifyLost();
                } else {
                    listTextField.get(currentIndex).setStyle("-fx-border-color: green; -fx-border-width: 2px;");
                    listTextField.get(currentIndex).setEditable(false);
                    verifyWin();
                }

                if (currentIndex + 1 < listTextField.size()) {
                    listTextField.get(currentIndex + 1).requestFocus();
                }
            }
        }
    }

    /**
     * This method checks each time an incorrect letter is entered to see if the player has lost.
     */
    public void verifyLost() {
        if (game.getErrors() == 5) {
            resultLabel.setStyle("-fx-text-fill: red;");
            resultLabel.setText("¡Perdiste!");

            for (int i = 0; i < game.getWordSize(); i++) {
                listTextField.get(i).setEditable(false);
            }
        }
    }

    /**
     * This method updates the eclipse image based on the number of mistakes.
     */
    public void updateImages() {
        Image image = new Image(getClass().getResourceAsStream("/com/example/soleclipsado/images/" + game.getErrors() + ".png"));
        imageErrors.setImage(image);
    }

    /**
     * This method checks each time a correct letter is entered to see if the player has won.
     */
    public void verifyWin() {
        if (game.verifyWin()) {
            resultLabel.setStyle("-fx-text-fill: green;");
            resultLabel.setText("¡Ganaste!");
        }
    }

    /**
     * This method receives the event from the help button and checks if there are hints available to provide.
     * @param event The event detect
     */
    @FXML
    void onHandleHelpButton(ActionEvent event) {
        if (game.getAidCount() < 3 && game.getErrors() < 5 && !(game.verifyWin())) {
            int random;

            while (true) {
                random = (int) (Math.random() * game.getWordSize());

                if (listTextField.get(random).getText().isEmpty()) {
                    break;
                }
            }

            game.increaseHelps();
            aidCountLabel.setText(String.valueOf(game.getAidCount()));
            listTextField.get(random).setText(String.valueOf(game.getCharacter(random)));
        }
        else {
            if (game.getErrors() >= 3) {
                alertBox.showAlert("Error", "ERROR", "¡Ops! Ya usaste todas las ayudas disponibles");
            } else {
                alertBox.showAlert("Error", "ERROR", "¡Ops! No puedes usar ayudas porque ya ganaste");
            }
        }
    }
}
