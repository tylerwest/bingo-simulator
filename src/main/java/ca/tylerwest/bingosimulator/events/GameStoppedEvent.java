package ca.tylerwest.bingosimulator.events;

import ca.tylerwest.bingosimulator.model.Game;

public class GameStoppedEvent extends GameLifecycleEvent {

	public GameStoppedEvent(Game game) {
		super(game);
	}

}
