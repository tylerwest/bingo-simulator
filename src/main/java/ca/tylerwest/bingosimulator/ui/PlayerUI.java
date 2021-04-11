package ca.tylerwest.bingosimulator.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import ca.tylerwest.bingosimulator.model.Player;

@SuppressWarnings("serial")
public class PlayerUI extends JPanel {

	public static final Dimension PREFERRED_SIZE = new Dimension(250, 150);
	
	private static final Color[] STAMP_COLORS = { Color.cyan, Color.magenta, Color.green, Color.yellow, Color.red };
	private static final int FLASH_INTERVAL = 250;

	private Player player;

	private BoardUI boardUI;

	private JLabel playerName;
	private Timer timer;
	private LineBorder blackLineBorder;
	private LineBorder greenLineBorder;

	public PlayerUI(Player player) {
		this.player = player;
		this.blackLineBorder = new LineBorder(Color.black, 3, true);
		this.greenLineBorder = new LineBorder(Color.green, 3, true);
		this.timer = new Timer(FLASH_INTERVAL, new FlashBorderAction(this, blackLineBorder, greenLineBorder));
		
		setLayout(new BorderLayout());
		setPreferredSize(PREFERRED_SIZE);
		

		playerName = new JLabel();
		playerName.setText("Player " + player.getId());
		add(playerName, BorderLayout.NORTH);

		boardUI = new BoardUI(player.getBoard(), STAMP_COLORS[new Random().nextInt(STAMP_COLORS.length)]);
		add(boardUI, BorderLayout.CENTER);
	}

	public void update() {
		if (player.isBingo()) {
			setBackground(Color.green);
			timer.start();
		}
		boardUI.update();
	}

	private static class FlashBorderAction implements ActionListener {
		private PlayerUI playerUI;
		private LineBorder blackLineBorder;
		private LineBorder greenLineBorder;
		private boolean flash;
		
		public FlashBorderAction(PlayerUI playerUI, LineBorder blackLineBorder, LineBorder greenLineBorder) {
			super();
			this.playerUI = playerUI;
			this.blackLineBorder = blackLineBorder;
			this.greenLineBorder = greenLineBorder;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (flash)
				playerUI.setBorder(greenLineBorder);
			else
				playerUI.setBorder(blackLineBorder);
			flash = !flash;
		}
	}

}
