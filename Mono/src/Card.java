package src;

public class Card {
	private CardType type;
	private CardAction action;
	private int value;
	private int travelTo = Integer.MAX_VALUE;
	private String text;

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
		text = "Life insurance matures. Collect $100";
		value = 100;
	}

	private void stock() {
		action = CardAction.BANK_MONEY;
		text = "From sale of stock. You get $45";
		value = 45;
	}

	private void doctor() {
		action = CardAction.BANK_MONEY;
		text = "Doctors Fee! Pay $50";
		value = -50;
	}

	private void school() {
		action = CardAction.BANK_MONEY;
		text = "Pay School tax of $150";
		value = -150;
	}

	private void services() {
		action = CardAction.BANK_MONEY;
		text = "Receive for Services $25";
		value = 25;
	}

	private void hospital() {
		action = CardAction.BANK_MONEY;
		text = "Pay hospital $100";
		value = -100;
	}

	private void xmas() {
		action = CardAction.BANK_MONEY;
		text = "Xmas fund matures. Collect $100";
		value = 100;
	}

	private void income() {
		action = CardAction.BANK_MONEY;
		text = "Income Tax Refund, Collect $20";
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
			case 7:
				beauty();
				break;
			default:
				break;
		}
	}

	private void beauty() {
		action = CardAction.BANK_MONEY;
		text = "You have won second prize in a beauty contest! Collect $10";
		value = 10;
	}

	private void go() {
		action = CardAction.MOVE_TO;
		text = "Move to Go";
		travelTo = 0;
	}

	private void community() {
		action = CardAction.MOVE_TO;
		text = "Travel to Community";
		travelTo = 4;
	}

	private void poor() {
		action = CardAction.BANK_MONEY;
		text = "Pay poor tax of $15";
		value = -15;
	}

	private void loan() {
		action = CardAction.BANK_MONEY;
		text = "Your building and loan matures \nCollect $150";
		value = 150;
	}

	private void hcm() {
		action = CardAction.MOVE_TO;
		text = "Travel to HCM City";
		travelTo = 27;
	}

	private void dividend() {
		action = CardAction.BANK_MONEY;
		text = "Bank pays you dividend of $50";
		value = 50;
	}

	private void ngheAn() {
		action = CardAction.MOVE_TO;
		text = "Take a ride on the Nghe An";
		travelTo = 8;
	}

	public int value() {
		return value;
	}

	public int travelTo() {
		return travelTo;
	}

	public String text() {
		return text;
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
