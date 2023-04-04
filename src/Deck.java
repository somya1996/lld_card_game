import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> cards = new ArrayList<>();

	public Deck() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card drawCard() {
		if (isEmpty()) {
			return null;
		}
		return cards.remove(cards.size() - 1);
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}
}
