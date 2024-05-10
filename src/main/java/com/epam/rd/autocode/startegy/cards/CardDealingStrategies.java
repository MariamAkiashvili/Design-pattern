package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {

        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {
                Map<String, List<Card>> stack = new HashMap<>();
                Map<String, List<Card>> playerStack = new HashMap<>();

                for (int i = 1; i <= 2; i++) {
                    for (int j = 1; j <= players; j++) {
                        if (playerStack.containsKey("Player " + j)) {
                            playerStack.get("Player " + j).add(deck.dealCard());
                        } else {
                            List<Card> cards = new ArrayList<>();
                            cards.add(deck.dealCard());
                            playerStack.put("Player " + j, cards);
                        }


                    }

                }
                List<Card> communityStack = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    communityStack.add(deck.dealCard());
                }

                stack.put("Community", communityStack);
                stack.putAll(playerStack);
                stack.put("Remaining", deck.restCards());


                return stack;
            }
        };
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {
                Map<String, List<Card>> stack = new HashMap<>();
                Map<String, List<Card>> playersCard = new HashMap<>();

                for (int i = 0; i < 5; i++) {
                    for (int j = 1; j <= players; j++) {
                        if (playersCard.containsKey("Player " + j)) {
                            playersCard.get("Player " + j).add(deck.dealCard());
                        } else {
                            List<Card> cards = new ArrayList<>();
                            cards.add(deck.dealCard());
                            playersCard.put("Player " + j, cards);
                        }
                    }

                }
                stack.putAll(playersCard);
                stack.put("Remaining", deck.restCards());


                return stack;
            }
        };
    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {
                Map<String, List<Card>> stack = new HashMap<>();
                Map<String, List<Card>> playersCard = new HashMap<>();

                for (int i = 0; i < 13; i++) {
                    for (int j = 1; j <= players; j++) {
                        if (playersCard.containsKey("Player " + j)) {
                            playersCard.get("Player " + j).add(deck.dealCard());
                        } else {
                            List<Card> cards = new ArrayList<>();
                            cards.add(deck.dealCard());
                            playersCard.put("Player " + j, cards);
                        }
                    }
                }

                stack.putAll(playersCard);

                return stack;

            }
        };
    }

    public static CardDealingStrategy foolCardDealingStrategy(){
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {
                Map<String, List<Card>> stack = new HashMap<>();
                Map<String, List<Card>> playerStack = new HashMap<>();

                for (int i = 0; i < 6; i++) {
                    for (int j = 1; j <= players; j++) {
                        if (playerStack.containsKey("Player " + j)) {
                            playerStack.get("Player " + j).add(deck.dealCard());
                        } else {
                            List<Card> cards = new ArrayList<>();
                            cards.add(deck.dealCard());
                            playerStack.put("Player " + j, cards);
                        }
                    }
                }
                List<Card> trumpCard = new ArrayList<>();
                trumpCard.add(deck.dealCard());
                stack.putAll(playerStack);
                stack.put("Remaining", deck.restCards());
                stack.put("Trump card", trumpCard);


                return stack;

            }
        };
    }

}
