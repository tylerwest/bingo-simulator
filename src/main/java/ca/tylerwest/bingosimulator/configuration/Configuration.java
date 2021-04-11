package ca.tylerwest.bingosimulator.configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public final class Configuration {

	private int playerCount = Defaults.DEFAULT_PLAYER_COUNT;
	private int boardMaxValue = Defaults.DEFAULT_BOARD_MAX_VALUE;
	private int numberCalloutDelay = Defaults.DEFAULT_NUMBER_CALLOUT_DELAY;
	private TimeUnit numberCalloutUnit = Defaults.DEFAULT_NUMBER_CALLOUT_UNIT;

	public Configuration(String[] args) {
		parseArguments(args);
	}

	private void parseArguments(String[] args) {
		Options options = null;
		try {
			options = new Options();
			CommandLineParser parser = new DefaultParser();
			
			options.addOption(Option.builder("b")
					.desc(String.format("Maximum number that can be called out on a board (default: %d)", Defaults.DEFAULT_BOARD_MAX_VALUE))
					.hasArg()
					.argName("value")
					.longOpt("board-max-value")
					.type(Integer.class)
					.build());
			
			options.addOption(Option.builder("p")
					.desc(String.format("Players per game (default: %d)", Defaults.DEFAULT_PLAYER_COUNT))
					.hasArg()
					.argName("count")
					.longOpt("player-count")
					.type(Integer.class)
					.build());
			
			options.addOption(Option.builder("t")
					.desc(String.format("Simulation speed. Interval between numbers being called out (default: %d %s)", Defaults.DEFAULT_NUMBER_CALLOUT_DELAY, String.valueOf(Defaults.DEFAULT_NUMBER_CALLOUT_UNIT)))
					.hasArg()
					.argName("interval")
					.longOpt("time-interval")
					.type(Integer.class)
					.build());
			
			options.addOption(Option.builder("u")
					.desc(String.format("Interval time unit (default: %s) %s", String.valueOf(Defaults.DEFAULT_NUMBER_CALLOUT_UNIT), Arrays.toString(TimeUnit.values())))
					.hasArg()
					.argName("unit")
					.longOpt("time-interval-unit")
					.type(String.class)
					.build());
			
			options.addOption(Option.builder("h")
					.longOpt("help")
					.desc("Display this help message.")
					.build());
			
			CommandLine commandLine = parser.parse(options, args);
			
			if (commandLine.hasOption("h")) {
				printHelp(options);
				System.exit(0);
			}
			
			if (commandLine.hasOption("board-max-value"))
				boardMaxValue = Integer.valueOf(commandLine.getOptionValue("board-max-value"));
			
			if (commandLine.hasOption("player-count"))
				playerCount = Integer.valueOf(commandLine.getOptionValue("player-count"));
			
			if (commandLine.hasOption("time-interval"))
				numberCalloutDelay = Integer.valueOf(commandLine.getOptionValue("time-interval"));
			
			if (commandLine.hasOption("time-interval-unit"))
				numberCalloutUnit = TimeUnit.valueOf(commandLine.getOptionValue("time-interval-unit").toUpperCase());
			
		} catch (ParseException | NumberFormatException e) {
			System.err.println(e.getMessage());
			printHelp(options);
			System.exit(-1);
		}
	}

	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.setWidth(150);
		formatter.printHelp("java -jar bingo-simulator.jar",
				"Bingo Simulator simulates the gameplay of a typical Bingo game, "
				+ "creating players, generating boards, calling numbers, and determining winners.",
				options, "https://github.com/tylerwest", true);
	}
	
	public int getPlayerCount() {
		return playerCount;
	}
	
	public int getBoardMaxValue() {
		return boardMaxValue;
	}
	
	public int getNumberCalloutDelay() {
		return numberCalloutDelay;
	}
	
	public TimeUnit getNumberCalloutUnit() {
		return numberCalloutUnit;
	}

	private static final class Defaults {
		private static final int DEFAULT_PLAYER_COUNT = 9;
		private static final int DEFAULT_BOARD_MAX_VALUE = 75;
		private static final int DEFAULT_NUMBER_CALLOUT_DELAY = 1;
		private static final TimeUnit DEFAULT_NUMBER_CALLOUT_UNIT = TimeUnit.SECONDS;
	}


}
