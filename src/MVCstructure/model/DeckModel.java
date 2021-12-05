package MVCstructure.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DeckModel {
	private ArrayList<CardModel> currentDeck;   // stores a deck as an arraylist
	/**
	 * @param array list of Cards called currentDeck
	 */
	public DeckModel(){
		currentDeck = new ArrayList<>();
	}


	/**
	 * @return to get the number of cards in the deck
	 */
	public int getNumOfCards() { return currentDeck.size(); }

	/**
	 *
	 * @return an ArrayList holding the cards
	 */
	public ArrayList<CardModel> getCards(){
		return (ArrayList<CardModel>) currentDeck.clone();
	}

	/**
	 * 	adds a card to the current Deck (one by one)
 	 */
	public void addCard(CardModel c) { this.currentDeck.add(c); }

	/**
	 *
	 * @param cards the array list of cards to add to deck
	 */
	public void addCard(ArrayList<CardModel> cards) { currentDeck.addAll(cards); }

	/**
	 *
	 * @param index to remove a card at a certain index
	 */
	public void removeCard(int index) { this.currentDeck.remove(index);}

	/**
	 *
	 * @return String formatted in a certain way
	 */
	public String toString(){
		String retStr = "";
		for(int i = 0; i < currentDeck.size(); i++){
			retStr += "   " + (i+1) + ".) " + currentDeck.get(i).line() + "\n";
		}
		return retStr;
	}

	/**
	 *
	 * @return an ArrayList of cardmodel which is the Deck
	 */
	public ArrayList<CardModel> getCard(){
		return currentDeck;
	}

	/**
	 * shuffleDeck, to shuffle the Deck
	 */
	public void shuffleDeck(){
		Collections.shuffle(currentDeck, new Random()); }

}
