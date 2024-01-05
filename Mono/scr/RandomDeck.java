package Mono.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RandomDeck implements Deck{

	private final ArrayList<Card> deck; //store array of cards
	private int SIZE; //store number of cards
	private int current;  //store current card

	//create shuffled deck of cards
	public RandomDeck() {
		deck = new ArrayList<>();
	}

	public void initialize(Card[] cards) {
		if (!deck.isEmpty())
			return;
		SIZE = cards.length;
		deck.addAll(Arrays.asList(cards));
		Collections.shuffle(deck);
	}

	//draw next card from deck
	public Card drawCard() {
		if (current == SIZE) {
			Collections.shuffle(deck);
			current = 0;
		}
        
		Card card = deck.get(current++);

		return card;
	}

	public Iterable<Card> cards() {
		return new ArrayList<>(deck);
	}
}
