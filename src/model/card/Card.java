package model.card;

public class Card implements ICard{
    private final int rank;
    private final int suit;
    private final int value;

    public Card(){
        this.rank = 0;
        this.suit = 0;
        this.value = 0;
    }

    public Card(int rank, int suit){
        this.rank = rank;
        this.suit = suit;
        this.value = calculateValue();
    }

    public int calculateValue(){
        if(rank == 9){return 0;}

        if(rank  <= 10){return rank;}

        return -10;
    }

    @Override
    public String getRankString(){
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        return ranks[rank -1];
    }

    @Override
    public String getSuitString(){
        String[] suits = {"CLUBS", "DIAMONDS", "HEARTS", "SPADES"};
        return suits[suit -1];
    }

    @Override
    public int getValue(int currentSum){
        if(rank == 1){
            if(currentSum > 40){return 1;}
            else{return 10;}
        }
        return value;
    }

    @Override
    public int getRank(){return rank;}

    @Override
    public int getSuit(){return suit;}

    @Override
    public boolean isValidMove(int currentSum){
        return (currentSum + value) <= 50;
    }
}
