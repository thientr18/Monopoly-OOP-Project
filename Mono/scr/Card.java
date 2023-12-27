package Mono.src;

public class Card {
	private CardType type;
	private CardAction action;
	private int value;
	private int travel = Integer.MAX_VALUE;
	private int travelTo = Integer.MAX_VALUE;
	private boolean increased;
	private String textA;
	private String textB;
	private String textC;

	Board location = new Board(travel, travel, travel, travel);

	public Card(CardType type, int a) {
		if (!type.equals(CardType.CHANCE) && !type.equals(CardType.COMMUNITY))
			throw new IllegalArgumentException("Card type invalid!");
		if (type.equals(CardType.CHANCE))
			chance(a);
		else
			community(a);
	}

	private void community(int a) {
		type = CardType.COMMUNITY;
		switch (a) {
			case 0:
				income();
				break;
			case 1:
				xmas();
				break;
			case 2:
				hospital();
				break;
			case 3:
				services();
				break;
			case 4:
				school();
				break;
			case 5:
				doctor();
				break;
			case 6:
				stock();
				break;
			case 7:
				life();
				break;
			default:
				break;
		}
	}

	private void life() {
		action = CardAction.BANK_MONEY;
		textA = "Life insurance matures";
		textB = "Collect $100";
		value = 100;
	}

	private void stock() {
		action = CardAction.BANK_MONEY;
		textA = "From sale of stock";
		textB = "You get $45";
		value = 45;
	}

	private void doctor() {
		action = CardAction.BANK_MONEY;
		textA = "Doctors Fee";
		textB = "Pay $50";
		value = -50;
	}

	private void school() {
		action = CardAction.BANK_MONEY;
		textA = "Pay School tax of $150";
		value = -150;
	}

	private void services() {
		action = CardAction.BANK_MONEY;
		textA = "Receive for Services $25";
		value = 25;
	}

	private void hospital() {
		action = CardAction.BANK_MONEY;
		textA = "Pay hospital $100";
		value = -100;
	}

	private void xmas() {
		action = CardAction.BANK_MONEY;
		textA = "Xmas fund matures";
		textB = "Collect $100";
		value = 100;
	}

	private void income() {
		action = CardAction.BANK_MONEY;
		textA = "Income Tax Refund";
		textB = "Collect $20";
		value = 20;
	}

	private void chance(int a) {
		type = CardType.CHANCE;
		switch (a) {
			case 0:
				ngheAn();
				break;
			case 1:
				dividend();
				break;
			case 2:
				hcm();
				break;
			case 3:
				loan();
				break;
			case 4:
				poor();
				break;
			case 5:
				community();
				break;
			case 6:
				go();
				break;
			case 8:
				beauty();
				break;
			default:
				break;
		}
	}

	private void beauty() {
		action = CardAction.BANK_MONEY;
		textA = "You have won second prize in a";
		textB = "beauty contest!";
		textC = "Collect $10";
		value = 10;
	}

	private void go() {
		action = CardAction.MOVE_TO;
		textA = "Move to Go";
		travelTo = 0;
		increased = false;
	}

	private void community() {
		action = CardAction.MOVE_TO;
		textA = "Travel to Community";
		travelTo = 3;
		increased = false;
	}

	private void poor() {
		action = CardAction.BANK_MONEY;
		textA = "Pay poor tax of $15";
		value = -15;
	}

	private void loan() {
		action = CardAction.BANK_MONEY;
		textA = "Your building and loan matures";
		textB = "Collect $150";
		value = 150;
	}

	private void hcm() {
		action = CardAction.MOVE_TO;
		textA = "Travel to HCM City";
		travelTo = 27;
		increased = false;
	}

	private void dividend() {
		action = CardAction.BANK_MONEY;
		textA = "Bank pays you dividend of $50";
		value = 50;
	}

	private void ngheAn() {
		action = CardAction.MOVE_TO;
		textA = "Take a ride on the Nghe An";
		travelTo = 8;
		increased = false;
	}

	public int value() {
		return value;
	}

	public int travel() {
		return travel;
	}

	public int travelTo() {
		return travelTo;
	}

	public boolean increased() {
		return increased;
	}

	public String textA() {
		return textA;
	}

	public String textB() {
		return textB;
	}

	public String textC() {
		return textC;
	}

	public CardType type() {
		return type;
	}

	public CardAction action() {
		return action;
	}

	public enum CardType {
		COMMUNITY, CHANCE
	}

	public enum CardAction {
		BANK_MONEY, MOVE_TO
	}
}
