package model.game;

import controller.GameController;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class GameFlowManager {
    private final GameModel gameModel;
    private final GameController controller;

    public GameFlowManager(GameModel gameModel, GameController controller){
        this.gameModel = gameModel;
        this.controller = controller;
    }

    public void startMachineTurn(){
        setPlayerInputEnabled(false);

        Task<Void> task = new Task<>(){
            @Override
            protected Void call() throws Exception{
                Thread.sleep(1100);
                return null;
            }

            @Override
            protected void succeeded(){
                Platform.runLater(() -> {
                    gameModel.playCurrentMachineTurn();
                    controller.updateView();

                    if(gameModel.isGameOver()){controller.showGameOver();}
                    else{
                        gameModel.nextTurn();
                        checkNextTurn();
                    }
                });
            }
        };
        new Thread(task).start();
    }

    private void checkNextTurn(){
        if(gameModel.getCurrentPlayer().isHuman()){
            setPlayerInputEnabled(true);
        }else{
            startMachineTurn();
        }
    }

    private void setPlayerInputEnabled(boolean enabled){
        controller.setPlayerCardsClickable(enabled);
    }
}
