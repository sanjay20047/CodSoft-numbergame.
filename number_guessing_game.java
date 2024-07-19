package NumberGame;

import java.util.Random;
import java.util.Scanner;

public class number_guessing_game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 10;
        int totalRounds = 6;
        int totalAttempts = 0;
        int totalRoundsWon = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        for (int round = 1; round <= totalRounds; round++) {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;

            System.out.println("\nRound " + round);
            System.out.println("Hello, " + playerName + "! Guess a number between " + lowerBound + " and " + upperBound);

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    totalRoundsWon++;
                    displayScore(totalRounds, totalRoundsWon);
                    break;
                } else if (attempts == maxAttempts) {
                    System.out.println("Sorry, you've reached the maximum attempts. The correct number was " + targetNumber);
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }
            }

            totalAttempts += attempts;

            if (round == totalRounds) {
                System.out.println("\nGame Over!");
                break;
            }

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                System.out.println("Thank you for playing! Goodbye.");
                break;
            }
        }

        scanner.close();
    }

    private static void displayScore(int totalRounds, int totalRoundsWon) {
        System.out.println("\nGame Over!");
        System.out.println("Total Score: " + totalRoundsWon + " out of " + totalRounds);
    }
}
