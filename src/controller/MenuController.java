package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.Optional;

public class MenuController {
    @FXML
    public void handlePlayButton(ActionEvent event){
        try{
            ChoiceDialog<Integer> dialog = new ChoiceDialog<>(1, 1, 2, 3);
            dialog.setTitle("Cincuentazo");
            dialog.setHeaderText("¿Con cuantos bots quieres jugar?");
            dialog.setContentText("Número de bots: ");

            Optional<Integer> result = dialog.showAndWait();

            if(result.isPresent()) {
                int numBots = result.get();

                URL url = MenuController.class.getResource("../view/Game.fxml");

                FXMLLoader loader = new FXMLLoader(url);
                Parent gameRoot = loader.load();

                GameController gameController = loader.getController();
                gameController.startNewGame(numBots);

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Scene gameScene = new Scene(gameRoot);

                currentStage.setTitle("Cincuentazo - Partida");
                currentStage.setScene(gameScene);
                currentStage.show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleExitButton(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
