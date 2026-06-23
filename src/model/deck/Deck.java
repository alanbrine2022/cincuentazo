package model.deck;

import model.card.Card;
import model.player.IPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements IDeck{
    private List<Card> cards = new ArrayList<>();

    public Deck(){
        reset();
    }

    @Override
    public void reset(){
        cards.clear();
        for(int suit = 1; suit < 5; suit++){
            for(int rank = 1; rank < 14; rank++){
                cards.add(new Card(rank, suit));
            }
        }
        shuffle();
    }

    @Override
    public void shuffle(){
        Collections.shuffle(cards);
    }

    @Override
    public Card drawCard(){
        if(cards.isEmpty()){
            throw new IllegalStateException("El mazo está vacío.");
        }
        return cards.remove(0);
    }

    @Override
    public void dealToPlayer(IPlayer player){
        while(player.getHandSize() < 4 && !cards.isEmpty()){
            player.addCard(drawCard());
        }
    }

    @Override
    public void addCards(List<Card> newCards){
        if(newCards != null){
            cards.addAll(newCards);
            shuffle();
        }
    }

    @Override
    public void recycleTableCards(List<Card> tablePile){
        if(tablePile != null && tablePile.size() > 1){
            List<Card> toRecycle = new ArrayList<>(tablePile.subList(0, tablePile.size() - 1));
            cards.addAll(toRecycle);
            tablePile.removeAll(toRecycle);
            shuffle();
        }
    }

    @Override
    public int size(){return cards.size();}

    @Override
    public boolean isEmpty(){return cards.isEmpty();}
}
