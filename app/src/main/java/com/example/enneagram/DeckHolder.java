package com.example.enneagram;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DeckHolder {
    private static List<Card> deck;
    private Integer index = -1;

    public DeckHolder(Activity activity){
        deck = new ArrayList<>();
        createDeck(activity);
    }

    private void createDeck(Activity activity){
        String jsonString = loadJSONFromAsset(activity);
        try {
            JSONObject obj = new JSONObject(jsonString);
            for (var t: Card.CardType.values()) {
                JSONArray typeQuestions = obj.getJSONArray(t.ordinal()+1+"");
                var type = t;
                for(int i=0; i < typeQuestions.length(); i++){
                    Card card = new Card(typeQuestions.getString(i), type, false);
                    deck.add(card);
                }
            }
            Collections.shuffle(deck);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setResult(Boolean agree){
        deck.get(index).agree = agree;
    }

    public Card getNextCard(){
        if(index+1 < deck.size()){
            return deck.get(++index);
        }
        return null;
    }

    public Card getPrevCard(){
        if(index-1 > 0) {
            return deck.get(--index);
        }
        return null;
    }

    public Integer getIndex(){
        return index;
    }

    public Integer getTotal(){
        return deck.size();
    }


    private String loadJSONFromAsset(Activity activity) {
        String json = null;
        try {

            InputStream is = activity.getAssets().open("quiz.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
            return json;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public Card.CardType getResults() {
        HashMap<Card.CardType, Integer> counter = new HashMap<>(){{
            put(Card.CardType.ONE, 0);
            put(Card.CardType.TWO, 0);
            put(Card.CardType.THREE, 0);
            put(Card.CardType.FOUR, 0);
            put(Card.CardType.FIVE, 0);
            put(Card.CardType.SIX, 0);
            put(Card.CardType.SEVEN, 0);
            put(Card.CardType.EIGHT, 0);
            put(Card.CardType.NINE, 0);
        }};
        for(var card: deck){
            if(card.agree){
                counter.put(card.cardType, counter.get(card.cardType)+1);
            }
        }
        Optional<Object> maxKey = counter.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        return (Card.CardType)maxKey.get();
    }
}
