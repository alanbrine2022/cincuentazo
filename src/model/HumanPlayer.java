package model;

public class HumanPlayer extends Player{
    public HumanPlayer(String name){
        super(name, true);
    }

    @Override
    public Card chooseCardToPlay(int currentSum){
        //I don't know how to do
        return null;
    }

    public boolean playSelectedCard(Card card, GameModel gameModel){
        if(card == null || !getHand().contains(card)){
            return false;
        }
        return gameModel.playCard(this, card);
    }
}
