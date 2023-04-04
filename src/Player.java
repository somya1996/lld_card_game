import java.util.ArrayList;
import java.util.List;

public class Player {

	private List<Card> hand = new ArrayList<>();

	public void addCard(Card card) {
		hand.add(card);
	}

	public Card playCard(Deck deck) {
		Card card = getValidCard();
		if (card == null) {
			if (!deck.isEmpty()) {
				card = deck.drawCard();
				addCard(card);
			}
		} else {
			hand.remove(card);
		}
		return card;
	}

	public boolean isEmpty() {
		return hand.isEmpty();
	}

	private Card getValidCard() {
		for (Card card : hand) {
			if (card.isActionCard()) {
				return card;
			}
		}
		for (Card card : hand) {
			if (!card.isActionCard()) {
				return card;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Player with hand: " + hand;
	}
}
