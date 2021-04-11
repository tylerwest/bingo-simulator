package ca.tylerwest.bingosimulator.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ca.tylerwest.bingosimulator.model.Game;

public class NumberCallerService {

	private List<Integer> possibleNumbers;
	private List<Integer> calledNumbers;
	
	private int currentNumber;
	
	public NumberCallerService(int maxValue) {
		this.possibleNumbers = IntStream.rangeClosed(1, maxValue).boxed().collect(Collectors.toList());
		this.calledNumbers = new ArrayList<Integer>(this.possibleNumbers.size());
	}

	public void nextNumber(Game game) {
		Collections.shuffle(possibleNumbers);
		currentNumber = possibleNumbers.remove(0);
		calledNumbers.add(currentNumber);
		
		game.numberCalled(currentNumber);
	}
	
	public List<Integer> getPossibleNumbers() {
		return Collections.unmodifiableList(possibleNumbers);
	}
	
	public List<Integer> getCalledNumbers() {
		return Collections.unmodifiableList(calledNumbers);
	}

	public int getCurrentNumber() {
		return currentNumber;
	}

}
