package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.game.*;

public class GameController {
    GameModel gameModel;
    private GameFlowManager flowManager;

    public void startNewGame(int numBots){
        this.gameModel = new GameModel();
        this.flowManager = new GameFlowManager(gameModel, this);

        gameModel.startNewGame(numBots);
        updateView();
    }

    public void setPlayerCardsClickable(boolean enabled){
        //IDK dude
    }

    public void updateView(){
        //Mambo
    }

    public void showGameOver(){
        //GameOver
    }
}
