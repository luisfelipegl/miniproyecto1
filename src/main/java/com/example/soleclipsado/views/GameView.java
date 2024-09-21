package com.example.soleclipsado.views;

import com.example.soleclipsado.controllers.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class generates the view of the Game.
 * @author Felipe Garcia
 * @version 1.0
 */
public class GameView extends Stage {
    private GameController gameController;

    /**
     * This method creates the window and assigns the Game scene to it.
     * @throws IOException if an error occurs during the creation of the Stage
     */
    public GameView() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/soleclipsado/game-view.fxml")
        );
        Parent root = loader.load();
        this.gameController = loader.getController();
        this.setTitle("Sol eclipsado");
        Scene scene = new Scene(root);
        this.getIcons().add(new Image(
                getClass().getResourceAsStream("/com/example/soleclipsado/images/favicon.png")
        ));
        this.setScene(scene);
        this.show();
    }

    /**
     * Returns the GameController associated with this view.
     *
     * @return the GameController instance
     */
    public GameController getGameController() {
        return this.gameController;
    }

    /**
     * Retrieves the instance of GameView.
     * If the instance does not exist, it creates a new one.
     * @return The instance of GameView.
     * @throws IOException if an error occurs during the creation of the GameView
     */
    public static GameView getInstance() throws IOException {
        if (GameViewHolder.INSTANCE == null) {
            return GameViewHolder.INSTANCE = new GameView();
        }
        else {
            return GameViewHolder.INSTANCE;
        }
    }

    /**
     * Holder class for the instance of GameView.
     */
    private static class GameViewHolder {
        private static GameView INSTANCE;
    }
}
