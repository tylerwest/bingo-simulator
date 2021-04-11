package ca.tylerwest.bingosimulator.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ca.tylerwest.bingosimulator.model.Board;
import ca.tylerwest.bingosimulator.model.Board.Entry;

@SuppressWarnings("serial")
public class BoardUI extends JPanel {

	private Board board;
	
	private List<JLabel> cells;
	private Color stampColor;
	
	public BoardUI(Board board, Color stampColor) {
		this.board = board;
		this.stampColor = stampColor;
		cells = new LinkedList<JLabel>();
		
		setLayout(new GridLayout(6, 5, 5, 5));
		
		String[] header = {"B", "I", "N", "G", "O"};
		for (int i = 0; i < header.length; i++) {
			JLabel label = new JLabel(header[i]);
			label.setOpaque(true);
			label.setFont(Font.decode("Courier BOLD"));
			label.setBackground(Color.gray);
			label.setForeground(Color.white);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setVerticalAlignment(SwingConstants.CENTER);
			add(label);
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				JLabel cell = new JLabel();
				cell.setOpaque(true);
				cell.setFont(Font.decode("Courier BOLD 12"));
				cell.setBackground(Color.white);
				cell.setHorizontalAlignment(SwingConstants.CENTER);
				cell.setVerticalAlignment(SwingConstants.CENTER);
				cells.add(cell);
				
				add(cell);
			}	
		}
	}
	
	public void update() {
		int cellIndex = 0;
		Entry[][] values = board.getValues();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				JLabel cell = cells.get(cellIndex);
				cell.setText(values[i][j].toString());
				if (values[i][j].isMarked())
					cell.setBackground(stampColor);
				cellIndex++;
			}	
		}
	}
	
}
