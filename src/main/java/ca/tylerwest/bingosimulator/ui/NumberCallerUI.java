package ca.tylerwest.bingosimulator.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ca.tylerwest.bingosimulator.configuration.Configuration;
import ca.tylerwest.bingosimulator.events.NumberCalledEvent;

@SuppressWarnings("serial")
public class NumberCallerUI extends JPanel {
	
	private Map<Integer, JLabel> numbers;
	
	public NumberCallerUI(Configuration configuration) {
		this.numbers = new HashMap<Integer, JLabel>();
		
		int numbersPerRow = configuration.getBoardMaxValue() / 5;
		setLayout(new GridLayout(0, numbersPerRow + 1, 5, 5));
		
		String[] header = {"B", "I", "N", "G", "O"};
		for (int i = 0; i < header.length; i++) {
			JLabel headerCell = new JLabel(header[i]);
			headerCell.setOpaque(true);
			headerCell.setFont(Font.decode("Courier BOLD 12"));
			headerCell.setBackground(Color.gray);
			headerCell.setForeground(Color.white);
			headerCell.setHorizontalAlignment(SwingConstants.CENTER);
			headerCell.setVerticalAlignment(SwingConstants.CENTER);
			add(headerCell);
			
			for (int j = 0; j < numbersPerRow; j++) {
				int value = (i * numbersPerRow) + (j + 1);
				JLabel cell = new JLabel(String.valueOf(value));
				cell.setOpaque(true);
				cell.setFont(Font.decode("Courier BOLD 12"));
				cell.setBackground(Color.white);
				cell.setHorizontalAlignment(SwingConstants.CENTER);
				cell.setVerticalAlignment(SwingConstants.CENTER);
				numbers.put(value, cell);
				
				add(cell);
			}	
		}
	}

	public void update(NumberCalledEvent event) {
		numbers.entrySet().stream().filter(entry -> event.getCalledNumbers().contains(entry.getKey())).forEach(entry -> {
			entry.getValue().setBackground(Color.lightGray);
			entry.getValue().setForeground(Color.white);
		});
		
		JLabel label = numbers.get(event.getCalledNumber());
		label.setBackground(Color.yellow);
		label.setForeground(Color.black);
	}
}
