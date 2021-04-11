# Bingo Simulator

## Why?
I've been partaking in some online bingo events during quarantine, and considering my desire to automate *everything*, figured I would build a configurable simulation of a typical bingo game. Why not, right?

## How?
The Bingo Simulator utilizes a `GameService` to configure and manage the game state, a `NumberCallerService` to represent an announcer calling out numbers to the players, and a `WinValidatorService` to validate each players' card when they have achieved bingo.

The UI is decoupled from the `GameService`, utilizing an event handler to receive game life cycle notifications from the simulation, and update in turn.

The simulation speed defaults to a number being called at 1-second intervals, however this simulation speed can be configured to be any interval and `TimeUnit`. Called-out numbers are displayed at the top of the UI, and the simulation stops once a player achieves a bingo.

The set of possible numbers defaults to 75 for a standard bingo game, but can be configured to use a different number space. Player count is configurable, keeping in mind that the larger the number of players the faster a bingo will be achieved, simply by having more board permutations in play. It is possible for multiple players to achieve bingo on the same turn.

## Usage
Usage is fairly straightforward.
- Clone the repository and run `mvn package` to create the executable jar file
- Run the executable jar file, or run via the command line to configure the simulation parameters

## Requirements
- Apache Maven (to build)
- Java 1.8+

## Command line arguments
```
usage: java -jar bingo-simulator.jar [-b <value>] [-h] [-p <count>] [-t <interval>] [-u <unit>]
Bingo Simulator simulates the gameplay of a typical Bingo game, creating players, generating boards, calling numbers, and determining winners.
 -b,--board-max-value <value>     Maximum number that can be called out on a board (default: 75)
 -h,--help                        Display this help message.
 -p,--player-count <count>        Players per game (default: 9)
 -t,--time-interval <interval>    Simulation speed. Interval between numbers being called out (default: 1 SECONDS)
 -u,--time-interval-unit <unit>   Interval time unit (default: SECONDS) [NANOSECONDS, MICROSECONDS, MILLISECONDS, SECONDS, MINUTES, HOURS, DAYS]
 ```
