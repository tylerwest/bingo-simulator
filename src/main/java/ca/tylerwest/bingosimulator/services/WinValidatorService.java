package ca.tylerwest.bingosimulator.services;

import ca.tylerwest.bingosimulator.model.Board;
import ca.tylerwest.bingosimulator.model.Board.Entry;
import ca.tylerwest.bingosimulator.model.Game;
import ca.tylerwest.bingosimulator.model.Player;

public class WinValidatorService {

	public WinValidatorService() {

	}

	public WinValidatorResult checkForWin(Game game) {
		// validate all of the player boards for a win
		boolean playersWon = false;
		for (Player player : game.getPlayers()) {
			if (checkRows(player.getBoard()) || checkColumns(player.getBoard()) || checkDiagonals(player.getBoard())) {
				player.setBingo(true);
				playersWon = true;
			}
		}

		return new WinValidatorResult(game, playersWon);
	}

	private boolean checkRows(Board board) {
		boolean result = false;
		Entry[][] values = board.getValues();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				result = values[i][j].isMarked();
				if (!result)
					break;
			}
			if (result)
				break;
		}
		return result;
	}

	private boolean checkColumns(Board board) {
		boolean result = false;
		Entry[][] values = board.getValues();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				result = values[j][i].isMarked();
				if (!result)
					break;
			}
			if (result)
				break;
		}
		return result;
	}

	private boolean checkDiagonals(Board board) {
		boolean result = false;
		Entry[][] values = board.getValues();

		for (int i = 0; i < 5; i++) {
			result = values[i][i].isMarked();
			if (!result)
				break;
		}

		if (result)
			return result;

		for (int i = 0; i < 5; i++) {
			result = values[i][4 - i].isMarked();
			if (!result)
				break;
		}
		return result;
	}

	public static final class WinValidatorResult {
		private Game game;
		private boolean win;

		public WinValidatorResult(Game game, boolean win) {
			super();
			this.game = game;
			this.win = win;
		}
		
		public Game getGame() {
			return game;
		}

		public boolean isWin() {
			return win;
		}
	}
}
