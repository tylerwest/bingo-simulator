package ca.tylerwest.bingosimulator.events;

import ca.tylerwest.bingosimulator.model.Game;

public class GameLifecycleEvent {
	private Game game;

	public GameLifecycleEvent(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
}
