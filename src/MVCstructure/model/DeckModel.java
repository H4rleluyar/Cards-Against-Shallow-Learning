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
	 *
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

	// adds a card to the current Deck (one by one, or as a list)
	public void addCard(CardModel c) { this.currentDeck.add(c); }
	public void addCard(ArrayList<CardModel> cards) { currentDeck.addAll(cards); }

	// removes a card from the current Deck
	public void removeCard(int index) { this.currentDeck.remove(index);}

	public String toString(){
		String retStr = "";
		for(int i = 0; i < currentDeck.size(); i++){
			retStr += "   " + (i+1) + ".) " + currentDeck.get(i).line() + "\n";
		}
		return retStr;
	}

	//use this to get cards from ArrayList, not a clone version
	public ArrayList<CardModel> getCard(){
		return currentDeck;
	}

	/* *shiffleDeck, enter the deck that you want to shuffle */
	public void shuffleDeck(){
		Collections.shuffle(currentDeck, new Random()); }

}
