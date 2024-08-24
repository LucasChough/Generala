import java.util.*;

public class Generala {
    // ANSI escape codes for text colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Create a single Scanner object

        // Ask how many players.
        System.out.println("Welcome to Generala!");
        System.out.println("Enter the amount of players (max 4 players):");
        int numOfPlayers = input.nextInt();
        input.nextLine(); // Consume the leftover newline

        // Error check for user input
        while (numOfPlayers < 2 || numOfPlayers > 4) {
            System.out.println("Wrong amount. Please try again...");
            numOfPlayers = input.nextInt();
            input.nextLine(); // Consume the leftover newline
        }

        // Create player objects based on number of players
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        // Game logic based on the number of players
        for (int i = 0; i < 11; i++) {
            roll(player1, input);
            System.out.println(BLUE + "PLAYER 1 SCORE" + RESET + "\n" + player1.toString() + GREEN + "Total score: "
                    + player1.getTotalScore() + RESET);
            System.out.println("\n" + RED + "NEXT PLAYER TURN" + RESET);

            roll(player2, input);
            System.out.println(BLUE + "PLAYER 2 SCORE" + RESET + "\n" + player2.toString() + GREEN + "Total score: "
                    + player2.getTotalScore() + RESET);
            System.out.println("\n" + RED + "NEXT PLAYER TURN" + RESET);

            if (numOfPlayers > 2) {
                roll(player3, input);
                System.out.println(BLUE + "PLAYER 3 SCORE" + RESET + "\n" + player3.toString() + GREEN + "Total score: "
                        + player3.getTotalScore() + RESET);
                System.out.println("\n" + RED + "NEXT PLAYER TURN" + RESET);
            }
            if (numOfPlayers > 3) {
                roll(player4, input);
                System.out.println(BLUE + "PLAYER 4 SCORE" + RESET + "\n" + player4.toString() + GREEN + "Total score: "
                        + player4.getTotalScore() + RESET);
                System.out.println("\n" + RED + "NEXT PLAYER TURN" + RESET);
            } // end if's
        } // end for

        // Determine winner
        String winner = "Player 1 with " + player1.getTotalScore() + " points";
        if (player1.getTotalScore() < player2.getTotalScore()) {
            winner = "Player 2 with " + player2.getTotalScore() + " points";
        } else if (player2.getTotalScore() < player3.getTotalScore()) {
            winner = "Player 3 with " + player3.getTotalScore() + " points";
        } else if (player3.getTotalScore() < player4.getTotalScore()) {
            winner = "Player 4 with " + player4.getTotalScore() + " points";
        }
        System.out.println(YELLOW + "The Winner is: " + winner);

        input.close(); // Close the Scanner after all the input is done
    } // end main

    // Method that represents a player turn
    public static void roll(Player player, Scanner scanner) {
        int[] cup = new int[5];

        // Initial roll
        for (int i = 0; i < cup.length; i++) {
            cup[i] = singleRoll();
        }
        System.out.println("Roll 1");
        printDice(cup);

        // Allow up to 3 turns
        for (int roll = 2; roll <= 3; roll++) {
            System.out.println("\n" + "Roll " + roll);

            // Ask the player which dice they want to keep
            System.out.println("Enter the numbers of the dice you want to keep (1-5), separated by spaces:");
            String input = scanner.nextLine();
            String[] keepDiceIndices = input.split(" ");

            // Convert input to a list of indices
            List<Integer> keepIndices = new ArrayList<>();
            for (String index : keepDiceIndices) {
                try {
                    int dieIndex = Integer.parseInt(index) - 1; // Convert to zero-based index
                    if (dieIndex >= 0 && dieIndex < 5) {
                        keepIndices.add(dieIndex);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter numbers between 1 and 5.");
                } // end try/catch
            } // end for

            // Create a new array for the dice values after this roll
            int[] newCup = new int[5];
            for (int i = 0; i < cup.length; i++) {
                if (keepIndices.contains(i)) {
                    newCup[i] = cup[i]; // Keep the dice values unchanged
                } else {
                    newCup[i] = singleRoll(); // Re-roll dice not kept
                } // end if
            } // end for

            cup = newCup; // Update the cup with new values

            System.out.println("Roll after roll " + roll + ":");
            printDice(cup);
        } // end for

        // After 3 turns, determine options
        determineOptions(cup, player);
    } // end roll

    // Method to represent a single dice roll
    public static int singleRoll() {
        return (int) (Math.random() * 6) + 1; // Simulate rolling a die (1-6)
    } // end singleRoll

    // Method to print rolled dice
    public static void printDice(int[] cup) {
        for (int i = 0; i < cup.length; i++) {
            System.out.println("Dice " + (i + 1) + ": " + cup[i]);
        } // end for
    } // end pintDice

    // Method to determine score option
    public static void determineOptions(int[] cup, Player player) {
        int[] frequencies = RollPatterns.countFrequencies(cup);

        System.out.println("Possible options:");

        boolean hasPrimaryPattern = false;
        List<String> options = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Add primary patterns to options
        if (RollPatterns.isGenerala(frequencies) && player.getGenerala() == 0) {
            options.add("Generala");
            hasPrimaryPattern = true;
        }
        if (RollPatterns.isGenerala(frequencies) && player.getGenerala() > 0 && player.getDoubleGenerala() == 0) {
            options.add("Double Generala");
            hasPrimaryPattern = true;
        }
        if (RollPatterns.isPoker(frequencies) && player.getPoker() == 0) {
            options.add("Poker");
            hasPrimaryPattern = true;
        }
        if (RollPatterns.isFull(frequencies) && player.getFull() == 0) {
            options.add("Full House");
            hasPrimaryPattern = true;
        }
        if (RollPatterns.isStraight(frequencies) && player.getStraight() == 0) {
            options.add("Straight");
            hasPrimaryPattern = true;
        } // end if's

        // Add number-based scoring options
        for (int i = 1; i <= 6; i++) {
            int score = calculateScoreForNumber(i, frequencies);
            switch (i) {
                case 1:
                    if (player.getOne() == 0 && score != 0) {
                        options.add("Score for number 1: " + score);
                    }
                    break;
                case 2:
                    if (player.getTwo() == 0 && score != 0) {
                        options.add("Score for number 2: " + score);
                    }
                    break;
                case 3:
                    if (player.getThree() == 0 && score != 0) {
                        options.add("Score for number 3: " + score);
                    }
                    break;
                case 4:
                    if (player.getFour() == 0 && score != 0) {
                        options.add("Score for number 4: " + score);
                    }
                    break;
                case 5:
                    if (player.getFive() == 0 && score != 0) {
                        options.add("Score for number 5: " + score);
                    }
                    break;
                case 6:
                    if (player.getSix() == 0 && score != 0) {
                        options.add("Score for number 6: " + score);
                    }
                    break;
            } // end switch
        } // end for

        if (!options.isEmpty()) {
            // Print available options
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ": " + options.get(i));
            } // end ro4

            // Ask the player to choose an option
            System.out.println("Choose an option by entering the number: ");
            int choice = scanner.nextInt() - 1;

            if (choice >= 0 && choice < options.size()) {
                String selectedOption = options.get(choice);
                updatePlayerScore(selectedOption, player);
            } else {
                System.out.println("Invalid choice. No score updated.");
            } // end if
        } else {
            // If no options are available, ask the player to scratch out a category
            System.out.println("No scoring options available. Please choose a category to scratch out:");

            // List of categories that can be scratched out
            List<String> scratchOptions = new ArrayList<>();

            if (player.getDoubleGenerala() == 0) {
                scratchOptions.add("Double Generala");
            }
            if (player.getGenerala() == 0) {
                scratchOptions.add("Generala");
            }
            if (player.getPoker() == 0) {
                scratchOptions.add("Poker");
            }
            if (player.getFull() == 0) {
                scratchOptions.add("Full House");
            }
            if (player.getStraight() == 0) {
                scratchOptions.add("Straight");
            }
            if (player.getOne() == 0) {
                scratchOptions.add("Score for number 1");
            }
            if (player.getTwo() == 0) {
                scratchOptions.add("Score for number 2");
            }
            if (player.getThree() == 0) {
                scratchOptions.add("Score for number 3");
            }
            if (player.getFour() == 0) {
                scratchOptions.add("Score for number 4");
            }
            if (player.getFive() == 0) {
                scratchOptions.add("Score for number 5");
            }
            if (player.getSix() == 0) {
                scratchOptions.add("Score for number 6");
            } // end if's

            // Print available scratch options
            for (int i = 0; i < scratchOptions.size(); i++) {
                System.out.println((i + 1) + ": " + scratchOptions.get(i));
            } // end for

            // Ask the player to choose a scratch option
            System.out.println("Choose an option to scratch by entering the number: ");
            int scratchChoice = scanner.nextInt() - 1;

            if (scratchChoice >= 0 && scratchChoice < scratchOptions.size()) {
                String selectedScratchOption = scratchOptions.get(scratchChoice);
                scratchPlayerCategory(selectedScratchOption, player);
            } else {
                System.out.println("Invalid choice. No category scratched out.");
            } // end if
        } // end if
    } // end determineOptions

    // Method to update the player's score based on the chosen option
    public static void updatePlayerScore(String selectedOption, Player player) {
        if (selectedOption.contains("Generala")) {
            player.setGenerala(50); // Generala score
        } else if (selectedOption.equals("Double Generala")) {
            player.setDoubleGenerala(100); // Double Generala score
        } else if (selectedOption.contains("Poker")) {
            player.setPoker(40); // Poker score
        } else if (selectedOption.contains("Full House")) {
            player.setFull(30); // Full House score
        } else if (selectedOption.contains("Straight")) {
            player.setStraight(20); // Straight score
        } else if (selectedOption.contains("Score for number 1")) {
            int score = Integer.parseInt(selectedOption.split(": ")[1]);
            player.setOne(score);
        } else if (selectedOption.contains("Score for number 2")) {
            int score = Integer.parseInt(selectedOption.split(": ")[1]);
            player.setTwo(score);
        } else if (selectedOption.contains("Score for number 3")) {
            int score = Integer.parseInt(selectedOption.split(": ")[1]);
            player.setThree(score);
        } else if (selectedOption.contains("Score for number 4")) {
            int score = Integer.parseInt(selectedOption.split(": ")[1]);
            player.setFour(score);
        } else if (selectedOption.contains("Score for number 5")) {
            int score = Integer.parseInt(selectedOption.split(": ")[1]);
            player.setFive(score);
        } else if (selectedOption.contains("Score for number 6")) {
            int score = Integer.parseInt(selectedOption.split(": ")[1]);
            player.setSix(score);
        } // end if
    } // updatePlayerScore

    // Method to scratch out a category for the player
    public static void scratchPlayerCategory(String selectedScratchOption, Player player) {
        if (selectedScratchOption.equals("Double Generala")) {
            player.setDoubleGenerala(-1);
        } else if (selectedScratchOption.equals("Generala")) {
            player.setGenerala(-1);
        } else if (selectedScratchOption.equals("Poker")) {
            player.setPoker(-1);
        } else if (selectedScratchOption.equals("Full House")) {
            player.setFull(-1);
        } else if (selectedScratchOption.equals("Straight")) {
            player.setStraight(-1);
        } else if (selectedScratchOption.equals("Score for number 1")) {
            player.setOne(-1);
        } else if (selectedScratchOption.equals("Score for number 2")) {
            player.setTwo(-1);
        } else if (selectedScratchOption.equals("Score for number 3")) {
            player.setThree(-1);
        } else if (selectedScratchOption.equals("Score for number 4")) {
            player.setFour(-1);
        } else if (selectedScratchOption.equals("Score for number 5")) {
            player.setFive(-1);
        } else if (selectedScratchOption.equals("Score for number 6")) {
            player.setSix(-1);
        } // end if
    } // end scratchPlayerCategory

    // Method to compute the score for a number
    public static int calculateScoreForNumber(int number, int[] frequencies) {
        return number * frequencies[number - 1]; // Multiply number by the frequency of that number
    } // end calculateScoreForNumber

} // end Generala
