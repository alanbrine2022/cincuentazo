package model.card;

public interface ICard {
    int getRank();
    int getSuit();
    String getRankString();
    String getSuitString();
    int getValue(int currentSum);
    boolean isValidMove(int currentSum);
}