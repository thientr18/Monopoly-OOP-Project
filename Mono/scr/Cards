package Mono.src;

public class Cards {
    private final int DECK_SIZE = 8; //8 cards in either type of deck
    private final Deck deck; //store deck of cards
    private String name;
    private int position;
    private Card.CardType type;
    
    // Constuctor square of type cards
    public Cards (String name, int position, Card.CardType type, Deck deck) {
        this.deck = deck;

        if (type != Card.CardType.COMMUNITY && type != Card.CardType.CHANCE) {
            throw new IllegalArgumentException("Card type invalid!");
        }

        if (type == Card.CardType.CHANCE) {
			chance();
        }
		else {
			community();
        }

        this.name = name;
        this.position = position;
        this.type = type;
    }

    public Card.CardType type() {
        return type;
    }

	public boolean isOwnable() {
		return false;
	}

	public int position() {
		return position;
	}

	public String name() {
		return name;
	}

	public boolean isOwned() {
		return false;
	}

    // Create deck of community chest cards
    private void community() {
        Card[] cards = new Card[DECK_SIZE];

        for (int i = 0; i < DECK_SIZE; i++) {
            cards[i] = new Card(Card.CardType.COMMUNITY, i);
        }

        deck.initialize(cards);
    }

    // Create deck of chance cards
    private void chance() {
        Card[] cards = new Card[DECK_SIZE];

        for (int i = 0; i < DECK_SIZE; i++) {
            cards[i] = new Card(Card.CardType.CHANCE, i);
        }

        deck.initialize(cards);
    }
}   
