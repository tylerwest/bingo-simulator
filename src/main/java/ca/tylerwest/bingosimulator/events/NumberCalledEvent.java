package ca.tylerwest.bingosimulator.events;

import java.util.List;

import ca.tylerwest.bingosimulator.model.Game;

public class NumberCalledEvent extends GameLifecycleEvent {

	private int calledNumber;
	private List<Integer> calledNumbers;
	
	public NumberCalledEvent(Game game, int calledNumber, List<Integer> calledNumbers) {
		super(game);
		this.calledNumber = calledNumber;
		this.calledNumbers = calledNumbers;
	}
	
	public int getCalledNumber() {
		return calledNumber;
	}
	
	public List<Integer> getCalledNumbers() {
		return calledNumbers;
	}

}
