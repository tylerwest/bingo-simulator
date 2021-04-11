package ca.tylerwest.bingosimulator.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import ca.tylerwest.bingosimulator.configuration.Configuration;
import ca.tylerwest.bingosimulator.events.GameLifecycleListener;
import ca.tylerwest.bingosimulator.events.GameStartedEvent;
import ca.tylerwest.bingosimulator.events.GameStoppedEvent;
import ca.tylerwest.bingosimulator.events.NumberCalledEvent;
import ca.tylerwest.bingosimulator.model.Player;

@SuppressWarnings("serial")
public class BingoSimulatorUI extends JFrame {

	private static final int EDGE_BUFFER = 50;
	private static final int VISIBLE_CELLS = 3;
	
	private Configuration configuration;
	
	private SimulatorUIGameLifecycleEventHandler eventHandler;

	private JPanel playerPanel;
	private List<PlayerUI> playerUIs;
	private NumberCallerUI numberCallerUI;

	public BingoSimulatorUI(Configuration configuration) {
		this.configuration = configuration;
		this.eventHandler = new SimulatorUIGameLifecycleEventHandler();
		this.playerUIs = new LinkedList<PlayerUI>();

		initializeFrameProperties();
		initializeNumberCallerUI();
		initializePlayerPanel();
	}

	private void initializeFrameProperties() {
		setTitle("Bingo Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	}
	
	private void initializeNumberCallerUI() {
		numberCallerUI = new NumberCallerUI(configuration);
		add(numberCallerUI, BorderLayout.NORTH);
	}

	private void initializePlayerPanel() {
		playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(0, VISIBLE_CELLS, 10, 10));
		
		JScrollPane scroll = new JScrollPane(playerPanel);
		scroll.setPreferredSize(new Dimension(PlayerUI.PREFERRED_SIZE.width * VISIBLE_CELLS + EDGE_BUFFER, PlayerUI.PREFERRED_SIZE.height * VISIBLE_CELLS + EDGE_BUFFER));
		add(scroll, BorderLayout.CENTER);
	}
	
	private void createPlayerUI(Player player) {
		PlayerUI playerUI = new PlayerUI(player);
		playerUIs.add(playerUI);
		playerPanel.add(playerUI);
	}
	
	private void updatePlayerUIs() {
		playerUIs.forEach(p -> p.update());
	}
	
	private void updateNumberCallerUI(NumberCalledEvent event) {
		numberCallerUI.update(event);
	}

	public void start() {
		pack();
		setVisible(true);
	}

	public SimulatorUIGameLifecycleEventHandler getEventHandler() {
		return eventHandler;
	}

	private class SimulatorUIGameLifecycleEventHandler implements GameLifecycleListener {
		@Override
		public void onGameStarted(GameStartedEvent event) {
			SwingUtilities.invokeLater(() -> {
				event.getGame().getPlayers().forEach(p -> createPlayerUI(p));
			});
		}

		@Override
		public void onGameStopped(GameStoppedEvent event) {
			SwingUtilities.invokeLater(() -> {
				updatePlayerUIs();
			});
		}

		@Override
		public void onNumberCalled(NumberCalledEvent event) {
			SwingUtilities.invokeLater(() -> {
				updateNumberCallerUI(event);
				updatePlayerUIs();
			});
		}
	}

}
