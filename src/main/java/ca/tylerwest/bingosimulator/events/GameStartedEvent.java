package ca.tylerwest.bingosimulator.events;

import ca.tylerwest.bingosimulator.model.Game;

public class GameStartedEvent extends GameLifecycleEvent {

	public GameStartedEvent(Game game) {
		super(game);
	}

}
