package ca.tylerwest.bingosimulator.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Game {
	
	private List<Player> players;
	private boolean completed;
	
	public Game() {
		this.players = new LinkedList<Player>();
		this.completed = false;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public List<Player> getPlayers() {
		return Collections.unmodifiableList(this.players);
	}

	public boolean hasCompleted() {
		return completed;
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public void numberCalled(int number) {
		players.parallelStream().forEach(p -> p.play(number));
	}

}
