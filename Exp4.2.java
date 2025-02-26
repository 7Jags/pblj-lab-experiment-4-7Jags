Experiment 4.2: Card Collection System

Objective:
Develop a Java program that collects and stores playing cards to help users find all the cards of a given symbol (suit).
The program should utilize the Collection interface (such as ArrayList, HashSet, or HashMap) to manage the card data efficiently.

Understanding the Problem Statement

1. Card Structure:
Each card consists of a symbol (suit) and a value (rank).

Example card representations:
Ace of Spades
King of Hearts
10 of Diamonds
5 of Clubs

2. Operations Required:
Add Cards → Store card details in a collection.
Find Cards by Symbol (Suit) → Retrieve all cards belonging to a specific suit (e.g., all "Hearts").
Display All Cards → Show all stored cards.

3. Collections Usage:
ArrayList: To store cards in an ordered manner.
HashSet: To prevent duplicate cards.
HashMap<String, List<Card>>: To organize cards based on suits for faster lookup.

CODE:
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Card {
    private String value;
    private String suit;

    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

   
    public String toString() {
        return value + " of " + suit;
    }
}

class CardCollectionSystem {
    private List<Card> cards;
    private HashSet<String> cardSet;
    private Map<String, List<Card>> cardsBySuit;

    public CardCollectionSystem() {
        cards = new ArrayList<>();
        cardSet = new HashSet<>();
        cardsBySuit = new HashMap<>();
    }

    public void addCard(String value, String suit) {
        String cardIdentifier = value + " of " + suit;
        if (cardSet.contains(cardIdentifier)) {
            System.out.println("Error: Card \"" + cardIdentifier + "\" already exists.");
            return;
        }
        Card newCard = new Card(value, suit);
        cards.add(newCard);
        cardSet.add(cardIdentifier);
        cardsBySuit.computeIfAbsent(suit, k -> new ArrayList<>()).add(newCard);
        System.out.println("Card added: " + newCard);
    }

    public void findCardsBySuit(String suit) {
        List<Card> foundCards = cardsBySuit.get(suit);
        if (foundCards == null || foundCards.isEmpty()) {
            System.out.println("No cards found for " + suit + ".");
        } else {
            for (Card card : foundCards) {
                System.out.println(card);
            }
        }
    }

    public void displayAllCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards found.");
        } else {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }

    public void removeCard(String value, String suit) {
        String cardIdentifier = value + " of " + suit;
        Card cardToRemove = null;
        for (Card card : cards) {
            if (card.getValue().equals(value) && card.getSuit().equals(suit)) {
                cardToRemove = card;
                break;
            }
        }
        if (cardToRemove != null) {
            cards.remove(cardToRemove);
            cardSet.remove(cardIdentifier);
            cardsBySuit.get(suit).remove(cardToRemove);
            System.out.println("Card removed: " + cardToRemove);
        } else {
            System.out.println("Error: Card \"" + cardIdentifier + "\" not found.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CardCollectionSystem cardSystem = new CardCollectionSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Test Case 1: Display All Cards");
        cardSystem.displayAllCards();

        System.out.println("\nTest Case 2: Adding Cards");
        cardSystem.addCard("Ace", "Spades");
        cardSystem.addCard("King", "Hearts");
        cardSystem.addCard("10", "Diamonds");
        cardSystem.addCard("5", "Clubs");

        System.out.println("\nTest Case 3: Finding Cards by Suit");
        cardSystem.findCardsBySuit("Hearts");

        System.out.println("\nTest Case 4: Searching Suit with No Cards");
        cardSystem.findCardsBySuit("Diamonds");

        System.out.println("\nTest Case 5: Displaying All Cards");
        cardSystem.displayAllCards();

        System.out.println("\nTest Case 6: Preventing Duplicate Cards");
        cardSystem.addCard("King", "Hearts");

        System.out.println("\nTest Case 7: Removing a Card");
        cardSystem.removeCard("10", "Diamonds");

        System.out.println("\nFinal Display of All Cards");
        cardSystem.displayAllCards();

        scanner.close();
    }
}

Test Cases

Test Case 1: No Cards Initially
Input:
Display All Cards
Expected Output:
No cards found.

Test Case 2: Adding Cards
Input:
Add Card: Ace of Spades
Add Card: King of Hearts
Add Card: 10 of Diamonds
Add Card: 5 of Clubs
Expected Output:
Card added: Ace of Spades
Card added: King of Hearts
Card added: 10 of Diamonds
Card added: 5 of Clubs

Test Case 3: Finding Cards by Suit
Input:
Find All Cards of Suit: Hearts
Expected Output:
King of Hearts

Test Case 4: Searching Suit with No Cards
Input:
Find All Cards of Suit: Diamonds
(If no Diamonds were added)
Expected Output:
No cards found for Diamonds.

Test Case 5: Displaying All Cards
Input:
Display All Cards
Expected Output:
Ace of Spades
King of Hearts
10 of Diamonds
5 of Clubs

Test Case 6: Preventing Duplicate Cards
Input:
Add Card: King of Hearts
Expected Output:
Error: Card "King of Hearts" already exists.

Test Case 7: Removing a Card
Input:
Remove Card: 10 of Diamonds
Expected Output:
Card removed: 10 of Diamonds
