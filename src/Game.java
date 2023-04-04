import java.util.ArrayList;
import java.util.List;

public class Game {
	private Deck deck;
	private int currentPlayerIndex = 0;
	private List<Player> players = new ArrayList<>();

	public Game(List<Player> players) {
		this.players = players;
		this.deck = new Deck();
		deck.shuffle();

		// Deal 5 cards to each player
		for (Player player : players) {
			for (int i = 0; i < 5; i++) {
				player.addCard(deck.drawCard());
			}
		}
	}
	public void play() {
			Player currentPlayer = players.get(this.currentPlayerIndex);

			// Keep playing until the deck is empty
			while (!deck.isEmpty()) {
				// Get current player to play a card. Draw if invalid.
				Card card = currentPlayer.playCard(deck);

				if (card != null && card.isActionCard()) {
					// Execute action card logic
					if (card.getRank() == Rank.ACE) {
						int nextPlayerIndex = (currentPlayerIndex + 2) % players.size();
						currentPlayer = players.get(nextPlayerIndex);
						currentPlayerIndex = nextPlayerIndex;
					} else if (card.getRank() == Rank.KING) {
						reverseDirection();
					} else if (card.getRank() == Rank.QUEEN) {
						int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
						Player nextPlayer = players.get(nextPlayerIndex);
						if (!nextPlayer.isEmpty()) {
							nextPlayer.addCard(deck.drawCard());
						}
					} else if (card.getRank() == Rank.JACK) {
						int nextPlayerIndex = (currentPlayerIndex + 3) % players.size();
						Player nextPlayer = players.get(nextPlayerIndex);
						if (!nextPlayer.isEmpty()) {
							nextPlayer.addCard(deck.drawCard());
						}
					}
				}

				// Move to next player's turn. Reverse direction if applicable.
				this.currentPlayerIndex = (this.currentPlayerIndex + (isClockwise() ? 1 : -1) + players.size()) % players.size();
			}

			Player winner = checkForWinner();
			if (winner != null) {
				System.out.println(winner + " wins!");
			} else {
				System.out.println("It's a draw!");
			}
		}

	private Player checkForWinner() {
		for (Player player : players) {
			if (player.isEmpty()) {
				return player;
			}
		}
		return null;
	}

	private boolean isClockwise() {
		return currentPlayerIndex == 0 || currentPlayerIndex == players.size() - 1;
	}

	private void reverseDirection() {
		currentPlayerIndex = (currentPlayerIndex + players.size() - (isClockwise() ? 1 : 2)) % players.size();
	}
}
