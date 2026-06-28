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
                Thread.sleep(1200);
                return null;
            }

            @Override
            protected void succeeded(){
                Platform.runLater(() -> {
                    gameModel.playCurrentMachineTurn();
                    controller.updateView();

                    if(gameModel.isGameOver()){controller.showGameOver(true);}
                    else{
                        checkNextTurn();
                    }
                });
            }
        };
        new Thread(task).start();
    }

    public void checkNextTurn(){
        if(gameModel.getCurrentPlayer().isHuman()){
            if(!gameModel.getHumanPlayer().hasValidMove(gameModel.getCurrentSum())){
                controller.showGameOver(false);
                return;
            }
            setPlayerInputEnabled(true);
        }else{
            startMachineTurn();
        }
    }

    private void setPlayerInputEnabled(boolean enabled){
        controller.setPlayerCardsClickable(enabled);
    }
}
