public class Card {
	private final Rank rank;
	private final Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public boolean isActionCard() {
		return rank == Rank.ACE || rank == Rank.KING || rank == Rank.QUEEN || rank == Rank.JACK;
	}

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}
