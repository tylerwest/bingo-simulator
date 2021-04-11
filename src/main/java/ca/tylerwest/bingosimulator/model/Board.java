package ca.tylerwest.bingosimulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

	private static final Entry FREE_SPACE = new Entry(-1, true);
	private Entry[][] values;

	public Board(int maxValue) {
		List<Integer> list = IntStream.rangeClosed(1, maxValue / 5).boxed().collect(Collectors.toList());

		List<Integer[]> multipliers = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Collections.shuffle(list);
			Integer[] multiples = new Integer[5];
			for (int j = 0; j < 5; j++)
				multiples[j] = list.get(j);
			multipliers.add(multiples);
		}

		values = new Entry[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == 2 && j == 2)
					values[i][j] = FREE_SPACE;
				else
					values[i][j] = new Entry((j * (maxValue / 5)) + multipliers.get(j)[i], false);
			}
		}
	}

	public void markNumber(int number) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (values[i][j].getValue() == number)
					values[i][j].setMarked(true);
			}
		}
	}
	
	public Entry[][] getValues() {
		return values;
	}


	public static final class Entry {
		private int value;
		private boolean marked;

		private Entry(int value, boolean marked) {
			super();
			this.value = value;
			this.marked = marked;
		}

		public int getValue() {
			return value;
		}

		public boolean isMarked() {
			return marked;
		}

		public void setMarked(boolean marked) {
			this.marked = marked;
		}

		@Override
		public String toString() {
			if (value == -1) 
				return "FREE";
			else
				return String.valueOf(value);
		}
	}
}
