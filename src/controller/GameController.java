package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.card.Card;
import model.game.*;
import utils.CardImageLoader;
import java.util.List;

public class GameController {
    GameModel gameModel;
    private GameFlowManager flowManager;

    @FXML private Label sumLabel;
    @FXML private ImageView imgDeck;
    @FXML private ImageView imgTopCard;

    @FXML private ImageView card1;
    @FXML private ImageView card2;
    @FXML private ImageView card3;
    @FXML private ImageView card4;

    @FXML
    public void initialize(){}

    public void startNewGame(int numBots){
        this.gameModel = new GameModel();
        this.flowManager = new GameFlowManager(gameModel, this);

        gameModel.startNewGame(numBots);
        updateView();
    }

    public void updateView(){
        updateTopCard();
        updateSumLabel();
        updatePlayerHand();
    }

    public void showGameOver(){
        System.out.println("Game over");
        //Por terminar
    }

    private void updateSumLabel(){
        if(sumLabel != null && gameModel != null){
            sumLabel.setText(""+gameModel.getCurrentSum());
        }
    }

    private void updateTopCard(){
        if(imgTopCard != null && gameModel != null){
            Card top = gameModel.getTopCard();
            if(top != null){imgTopCard.setImage(CardImageLoader.getCardImage(top));}
        }
    }

    private void updatePlayerHand(){
        List<Card> hand = gameModel.getHumanPlayer().getHand();
        ImageView[] cardViews = {card1, card2, card3, card4};

        for(int i=0; i<=3; i++){
            cardViews[i].setImage(CardImageLoader.getCardImage(hand.get(i)));
            cardViews[i].setVisible(true);
        }
    }

    @FXML
    private void onCardClicked(MouseEvent event){
        if(!gameModel.getHumanPlayer().hasValidMove(gameModel.getCurrentSum())){
            showGameOver();
            return;
        }

        ImageView clicked = (ImageView) event.getSource();
        int index = getCardIndex(clicked);

        if(index < 0){return;}

        List<Card> hand = gameModel.getHumanPlayer().getHand();

        if(index >= hand.size()){return;}

        Card selected = hand.get(index);

        boolean played = gameModel.playCard(gameModel.getHumanPlayer(), selected);

        if(played){
            updateView();
            flowManager.checkNextTurn();
        }else{System.out.println("Movimiento inválido");}
    }

    private int getCardIndex(ImageView view){
        if(view == card1){return 0;}
        if(view == card2){return 1;}
        if(view == card3){return 2;}
        if(view == card4){return 3;}

        return -1;
    }

    public void setPlayerCardsClickable(boolean clickable){
        card1.setDisable(!clickable);
        card2.setDisable(!clickable);
        card3.setDisable(!clickable);
        card4.setDisable(!clickable);
    }
}
