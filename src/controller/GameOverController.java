package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;

public class GameOverController {
    @FXML private Label wonLabel;

    @FXML
    private void handleBackToMenu(ActionEvent event){
        try{
            URL url = GameOverController.class.getResource("../view/Menu.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent menuRoot = loader.load();

            menuRoot.getStylesheets().add(GameOverController.class.getResource("../view/css/styles.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(menuRoot));
            stage.setTitle("Cincuentazo");
        }catch(Exception e){e.printStackTrace();}
    }

    @FXML
    private void handleExit(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleNewGame(ActionEvent event){
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

    public void setWon(boolean won){
        if(won) {
            wonLabel.setText("¡GANASTE!");
        }else{wonLabel.setText(("¡PERDISTE!"));}
    }
}
