package model.player;

import model.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Player implements IPlayer{
    protected String name;
    protected List<Card> hand;
    protected boolean isHuman;

    public Player(String name, boolean isHuman){
        this.name = name;
        this.isHuman = isHuman;
        this.hand = new ArrayList<>(4);
    }

    @Override
    public List<Card> getHand(){return hand;}

    @Override
    public boolean isHuman(){return isHuman;}

    @Override
    public void addCard(Card card){
        if(hand.size() < 4){hand.add(card);}
    }

    @Override
    public void removeCard(Card card){hand.remove(card);}

    @Override
    public int getHandSize(){return hand.size();}

    @Override
    public boolean hasValidMove(int currentSum){
        for(Card card : hand){
            if(card.getValue(currentSum) + currentSum < 50){
                return true;
            }
        }
        return false;
    }

    @Override
    public void clearHand(){hand.clear();}
}
