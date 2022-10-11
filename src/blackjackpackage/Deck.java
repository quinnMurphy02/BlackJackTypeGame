package blackjackpackage;

import java.util.Random;
import java.util.Stack;

public class Deck {
	// Author: Quinn Murphy
	// Date: 10/5/22
	// Class: CS145
	// Lab: Deck of Cards
	// References: Old programs, book, StackOverflow

	// Here is the deck class. It has a lot of methods,
	// but mainly for simple things using the stack. 
	// Removing a card just subtracts 1 from the deck,
	// adding a card just adds one. Shuffle feature with
	// random number generator. Also we initialize the 
	// deck here, we set a for loop. For each value
	// of each suit, add a new card to the deck.

	// instance stack
	private Stack<Card> cards;

	// constructor for deck
	public Deck() {
		this.cards = new Stack<Card>();

	}

	// returns size of deck
	public int deckSize() {
		return this.cards.size();
	}

	public void moveCardsToDeck(Deck move) {
		int deckSize = this.cards.size(); // save deck size as int

		// move cards to move deck
		for (int i = 0; i < this.deckSize(); i++) {
			move.addCard(this.getOneCard(i)); // for size of deck, add cards
		}

		for (int i = 0; i < deckSize; i++) {
			this.removeOneCard(0);
		}
	}

	public void createFullDeck() {
		// make cards

		// for each value of each suit...
		for (Suit tmpSuit : Suit.values()) {
			for (Value tmpValue : Value.values()) {
				// add new card to deck
				this.cards.add(new Card(tmpSuit, tmpValue));

			}
		}

	}

	public String toString() {
		String cardOutput = ""; // save value to return

		for (Card theCard : this.cards) { // for each card, make a new line then toString();
			cardOutput += "\n" + theCard.toString();

		}

		return cardOutput;
	}

	public void shuffle() { // this uses a randomizer to set values to the stack
							// then use those values to add cards into a new temp deck.
							// for example, our deck stack is at 0,1,2,3,4 etc. But
							// now we set it with random numbers to something like
							// 34,2,17,51, etc.
							// Then set temp deck to current deck. Back to 0,1,2,3,4,
							// but with the new card values.
		Stack<Card> tmpDeck = new Stack<Card>(); // temp deck

		Random rand = new Random();
		int randCardIndex = 0;
		int originalSize = this.cards.size();

		// randomly select card index from original, then put in temp
		// then repeat 52 times until original deck is empty.
		for (int i = 0; i < originalSize; i++) {
			randCardIndex = rand.nextInt((this.cards.size() - 1 - 0) + 1) + 0;
			tmpDeck.add(this.cards.get(randCardIndex));

			// remove from original deck
			this.cards.remove(randCardIndex);
		}

		this.cards = tmpDeck; // now main cards will just equal our randomized deck

	}

	// simply removes one card if called
	public void removeOneCard(int c) {
		this.cards.remove(c);
	}

	// returns one card
	public Card getOneCard(int c) {
		return this.cards.get(c);
	}

	// add a card
	public void addCard(Card addedCard) {
		this.cards.add(addedCard);

	}

	// draws one card from top of deck
	public void draw(Deck origin) {
		this.cards.add(origin.getOneCard(0));
		origin.removeOneCard(0);
	}

	// returns value of cards in deck
	public int valueOfCards() {
		int value = 0;
		int aces = 0;

		for (Card tmpCard : this.cards) {
			// since this is blackjack we can just say that if
			// a card like HEART-TWO is pulled, we can just
			// add two to our "score". So we return an int value.
			switch (tmpCard.getCardValue()) {
			case TWO:
				value += 2;
				break;
			case THREE:
				value += 3;
				break;
			case FOUR:
				value += 4;
				break;
			case FIVE:
				value += 5;
				break;
			case SIX:
				value += 6;
				break;
			case SEVEN:
				value += 7;
				break;
			case EIGHT:
				value += 8;
				break;
			case NINE:
				value += 9;
				break;
			case TEN:
				value += 10;
				break;
			case JACK:
				value += 10;
				break;
			case QUEEN:
				value += 10;
				break;
			case KING:
				value += 10;
				break;
			case ACE:
				aces += 1;
				break;
			}
		}

		for (int i = 0; i < aces; i++) {
			if (value > 10) { // this simply says if we pull an ace and have
								// more then 10 as our score, we only add 1.
								// otherwise add usual 11.
				value += 1;
			} else {
				value += 11;
			}

		}

		return value;
	}

}
