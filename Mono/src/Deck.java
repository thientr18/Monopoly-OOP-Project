package src;

// Interface for decks and cards
interface Deck {
	Card drawCard();

	void initialize(Card[] cards);

	Iterable<Card> cards();
}
