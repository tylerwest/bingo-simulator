package ca.tylerwest.bingosimulator.events;

public interface GameLifecycleListener {
	void onGameStarted(GameStartedEvent event);
	void onGameStopped(GameStoppedEvent event);
	void onNumberCalled(NumberCalledEvent event);
}
