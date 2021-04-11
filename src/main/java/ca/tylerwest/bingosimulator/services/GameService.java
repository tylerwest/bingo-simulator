package ca.tylerwest.bingosimulator.services;

import java.util.LinkedList;
import java.util.List;

import ca.tylerwest.bingosimulator.configuration.Configuration;
import ca.tylerwest.bingosimulator.events.GameLifecycleListener;
import ca.tylerwest.bingosimulator.events.GameStartedEvent;
import ca.tylerwest.bingosimulator.events.GameStoppedEvent;
import ca.tylerwest.bingosimulator.events.NumberCalledEvent;
import ca.tylerwest.bingosimulator.model.Board;
import ca.tylerwest.bingosimulator.model.Game;
import ca.tylerwest.bingosimulator.model.Player;
import ca.tylerwest.bingosimulator.services.WinValidatorService.WinValidatorResult;

public class GameService {
	private Configuration configuration;
	private NumberCallerService numberCallerService;
	private WinValidatorService winValidatorService;

	private boolean running;

	private List<GameLifecycleListener> lifecycleListeners;

	public GameService(Configuration configuration) {
		this.configuration = configuration;
		this.numberCallerService = new NumberCallerService(configuration.getBoardMaxValue());
		this.winValidatorService = new WinValidatorService();
		this.lifecycleListeners = new LinkedList<GameLifecycleListener>();

		this.running = false;
	}

	public void start() {
		running = true;

		Game game = new Game();
		
		for (int i = 0; i < configuration.getPlayerCount(); i++) {
			Player player = new Player(i, new Board(configuration.getBoardMaxValue()));
			game.addPlayer(player);
		}
		
		fireGameStartedEvents(game);
		
		while (!game.hasCompleted() && running) {
			numberCallerService.nextNumber(game);
			fireNumberCalledEvents(game, numberCallerService.getCurrentNumber(), numberCallerService.getCalledNumbers());
			
			WinValidatorResult result = winValidatorService.checkForWin(game);
			if (result.isWin()) {
				game.setCompleted(true);
			} else {
				try {
					Thread.sleep(configuration.getNumberCalloutUnit().toMillis(configuration.getNumberCalloutDelay()));
				} catch (InterruptedException e) {
					System.err.println("Game thread interrupted!");
				}
			}
		}
		
		fireGameStoppedEvents(game);
	}

	public void stop() {
		running = false;
	}

	public void addGameLifecycleListener(GameLifecycleListener listener) {
		lifecycleListeners.add(listener);
	}

	public void removeGameLifecycleListener(GameLifecycleListener listener) {
		lifecycleListeners.remove(listener);
	}

	private void fireGameStartedEvents(Game game) {
		lifecycleListeners.forEach(listener -> listener.onGameStarted(new GameStartedEvent(game)));
	}

	private void fireGameStoppedEvents(Game game) {
		lifecycleListeners.forEach(listener -> listener.onGameStopped(new GameStoppedEvent(game)));
	}
	
	private void fireNumberCalledEvents(Game game, int calledNumber, List<Integer> calledNumbers) {
		lifecycleListeners.forEach(listener -> listener.onNumberCalled(new NumberCalledEvent(game, calledNumber, calledNumbers)));
	}
}
