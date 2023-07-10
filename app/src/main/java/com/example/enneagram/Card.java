package com.example.enneagram;

public class Card {
    public enum CardType{
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE
    }
    String question;
    CardType cardType;
    Boolean agree;

    public Card(String question, CardType cardType, Boolean agree) {
        this.question = question;
        this.cardType = cardType;
        this.agree = agree;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Boolean getAgree() {
        return agree;
    }

    public void setAgree(Boolean agree) {
        this.agree = agree;
    }
}
