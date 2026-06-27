package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.card.Card;
import model.game.*;
import model.player.IPlayer;
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

    @FXML private ImageView botTopCard1;
    @FXML private ImageView botTopCard2;
    @FXML private ImageView botTopCard3;
    @FXML private ImageView botTopCard4;

    @FXML private ImageView botLeftCard1;
    @FXML private ImageView botLeftCard2;
    @FXML private ImageView botLeftCard3;
    @FXML private ImageView botLeftCard4;

    @FXML private ImageView botRightCard1;
    @FXML private ImageView botRightCard2;
    @FXML private ImageView botRightCard3;
    @FXML private ImageView botRightCard4;

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
        updateBotsHands();
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

    private void updateBotsHands(){
        clearBotCards(botTopCard1, botTopCard2, botTopCard3, botTopCard4);
        clearBotCards(botLeftCard1, botLeftCard2, botLeftCard3, botLeftCard4);
        clearBotCards(botRightCard1, botRightCard2, botRightCard3, botRightCard4);

        List<IPlayer> activePlayers = gameModel.getPlayers();
        int botIndex = 0;

        for(IPlayer player : activePlayers){
            if(player.isHuman()){continue;}
            ImageView[] currentBotCards = getBotCardViews(botIndex);

            for(int i=0; i<4; i++){
                if(currentBotCards[i] != null) {
                    currentBotCards[i].setImage(CardImageLoader.getBackImage());
                    currentBotCards[i].setVisible(true);
                }
            }
            botIndex++;
        }
    }

    private void clearBotCards(ImageView... cards){
        for(ImageView card : cards){
            if(card != null){card.setVisible(false);}
        }
    }

    private ImageView[] getBotCardViews(int botIndex){
        if(botIndex == 0){return new ImageView[]{botTopCard1, botTopCard2, botTopCard3, botTopCard4};}
        if(botIndex == 1){return new ImageView[]{botLeftCard1, botLeftCard2, botLeftCard3, botLeftCard4};}
        if(botIndex == 2){return new ImageView[]{botRightCard1, botRightCard2, botRightCard3, botRightCard4};}
        return new ImageView[0];
    }

    @FXML
    private void onCardClicked(MouseEvent event){
        ImageView clicked = (ImageView) event.getSource();
        int index = getCardIndex(clicked);

        if(index < 0){return;}

        List<Card> hand = gameModel.getHumanPlayer().getHand();

        if(index >= hand.size()){return;}

        Card selected = hand.get(index);

        boolean played = gameModel.getHumanPlayer().playSelectedCard(selected, gameModel);

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
