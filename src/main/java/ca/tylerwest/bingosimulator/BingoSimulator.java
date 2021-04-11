package ca.tylerwest.bingosimulator;

import javax.swing.SwingUtilities;

import ca.tylerwest.bingosimulator.configuration.Configuration;
import ca.tylerwest.bingosimulator.services.GameService;
import ca.tylerwest.bingosimulator.ui.BingoSimulatorUI;

public class BingoSimulator {

	private GameService gameService;
	private BingoSimulatorUI bingoUI;
	
	public BingoSimulator(Configuration configuration) {
		this.gameService = new GameService(configuration);
		this.bingoUI = new BingoSimulatorUI(configuration);
		
		this.gameService.addGameLifecycleListener(this.bingoUI.getEventHandler());
	}
	
	public void start() {
		this.gameService.start();
	}
	
	
	public void ui() {
		this.bingoUI.start();
	}
	
	public static void main(String[] args) {
		BingoSimulator simulator = new BingoSimulator(new Configuration(args));
		
		SwingUtilities.invokeLater(() -> simulator.ui());
		simulator.start();
	}

}
