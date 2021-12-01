package MVCstructure.model;

import MVCstructure.model.CardModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DeckModel {
	private ArrayList<CardModel> currentDeck;   // stores a deck as an arraylist
	/*
	 * @param array list of Cards called currentDeck
	 */
	public DeckModel(){
		currentDeck = new ArrayList<>();
	}

	// setter and getter for number of cards in a deck
	public int getNumOfCards() { return currentDeck.size(); }

	public ArrayList<CardModel> getCards(){
		return (ArrayList<CardModel>) currentDeck.clone();
	}

	//use this to get cards from ArrayList, might remove this later, keep for now
	public ArrayList<CardModel> getCard(){
		return currentDeck;
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

	/* *shiffleDeck, enter the deck that you want to shuffle */
	public void shuffleDeck(){
		Collections.shuffle(currentDeck, new Random()); }

}
