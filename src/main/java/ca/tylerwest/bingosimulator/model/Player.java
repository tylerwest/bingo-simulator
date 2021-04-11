package ca.tylerwest.bingosimulator.model;

public class Player {

	private int id;
	private boolean bingo;
	private Board board;

	public Player(int id, Board board) {
		this.id = id;
		this.board = board;
		this.bingo = false;
	}

	public int getId() {
		return id;
	}

	public Board getBoard() {
		return board;
	}

	public void setBingo(boolean bingo) {
		this.bingo = bingo;
	}

	public boolean isBingo() {
		return bingo;
	}

	public void play(int number) {
		board.markNumber(number);
	}
}
