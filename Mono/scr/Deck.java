package Mono.src;

// Interface for decks and cards
interface Deck {
	Card drawCard();

	void initialize(Card[] cards);

	void returnOutOfJail();

	Iterable<Card> cards();
}
