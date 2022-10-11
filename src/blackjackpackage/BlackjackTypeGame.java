package blackjackpackage;

import java.util.Scanner;

public class BlackjackTypeGame {
	
	// Author: Quinn Murphy
	// Date: 10/5/22
	// Class: CS145
	// Lab: Deck of Cards
	// References: Old programs, book, StackOverflow
	
	// Here is the main class. It will play a game of blackjack with the user. 
	// It will make two decks, then deal them to the player. It will
	// ask if they want to hit or stay. It also has a betting feature.
	// The loop is basically just letting the user (and possibly dealer)
	// draw, and then we have the big block of if statements for the 
	// things like checking if there was a push, or if we won. 

	public static void main(String[] args) {

		System.out.println("Welcome to Blackjack!");

		// create deck we play with
		Deck playDeck = new Deck();
		playDeck.createFullDeck();
		playDeck.shuffle();

		// create a deck for player
		Deck playerDeck = new Deck();
		Deck dealerDeck = new Deck();

		double money = 250.00;

		Scanner input = new Scanner(System.in);

		// game loop
		while (money > 0) {
			// play

			System.out.println("You have $" + money + ", how much do you wanna bet?");
			System.out.println("--------------------------------------------- \n");
			double bet = input.nextDouble();
			if (bet > money) {
				System.out.println("You cannot bet more than you have!!");
				System.out.println("--------------------------------------------- ");
				break;
			}
			boolean endRound = false;
			
			// deal cards
			// player gets two cards
			playerDeck.draw(playDeck);
			playerDeck.draw(playDeck);

			// dealer gets two cards
			dealerDeck.draw(playDeck);
			dealerDeck.draw(playDeck);

			while (true) {
				// print player cards with value of cards method
				System.out.println("Your cards are:");
				System.out.print(playerDeck.toString() + "\n");
				System.out.println("--------------------------------------------- ");
				System.out.println("Your hand is valued at: " + playerDeck.valueOfCards() + "\n");
				System.out.println("--------------------------------------------- ");

				// display dealer hand
				System.out.println("Dealer Hand: " + dealerDeck.getOneCard(0).toString() + " and (hidden)");
				System.out.println("--------------------------------------------- ");
				
				// what does player do?
				System.out.println("Press 1 if you want to hit!");
				System.out.println("Press 2 if you want to stand!");
				System.out.println("--------------------------------------------- ");
				int answer = input.nextInt();
				
				// player hits
				if(answer == 1) {
					playerDeck.draw(playDeck);
					System.out.println("You drew a: " + playerDeck.getOneCard(playerDeck.deckSize()-1).toString());
					
					if(playerDeck.valueOfCards() > 21) {
						System.out.println("Bust! Valued at: " + playerDeck.valueOfCards());
						money -= bet;
						endRound = true;
						break;
					}
				}
				
				// player stands
				if(answer == 2) {
					break;
				}
				
				
			}
			
			// show dealer cards
			System.out.println("Dealer hand: " + dealerDeck.toString());
			
			// does dealer have more points than player?
			if((dealerDeck.valueOfCards() > playerDeck.valueOfCards()) && endRound == false) {
				System.out.println("Dealer wins!");
				money -= bet;
				endRound = true;
			}
			
			while((dealerDeck.valueOfCards() < 17) && endRound == false) {
				dealerDeck.draw(playDeck);
				System.out.println("Dealer Draws: " + dealerDeck.getOneCard(dealerDeck.deckSize()-1).toString());
				// dealer draws at 16, and stands at 17. It looks complex, but we are just drawing and 
				// calling a toString(); once we hit 17.
			}
			
			// show total value of dealer
			System.out.println("Dealer hand valued at: " + dealerDeck.valueOfCards());
			
			// see if dealer is busted
			if((dealerDeck.valueOfCards() > 21) && endRound == false) {
				System.out.println("Dealer bust! You win!");
				money += bet;
				endRound = true;
			}
			
			// determine if push (dealer and player hand value is match)
			if((playerDeck.valueOfCards() == dealerDeck.valueOfCards()) && endRound == false) {
				System.out.println("Push");
				endRound = true;
			}
			// if our cards are worth more then dealer and we passed all if statemetes, we win.
			if((playerDeck.valueOfCards() > dealerDeck.valueOfCards()) && endRound == false) {
				System.out.println("You win!");
				money += bet;
				endRound = true;
				
			} else if(endRound == false){
				System.out.println("You lose the hand");
				money -= bet;
				endRound = true;
				
			}
			//moving all cards back to deck at end of hand
			
			playerDeck.moveCardsToDeck(playDeck);
			dealerDeck.moveCardsToDeck(playDeck);
			System.out.println("End of hand");
			
			
			
		}
		System.out.println("You lose!");

	}

}
