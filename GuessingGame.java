import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        while (playAgain) {
            int min = 1;
            int max = 100;
            int randomNumber = random.nextInt((max - min) + 1) + min;
            int maxAttempts = 10;
            int attempts = 0;
            boolean hasGuessedCorrectly = false;
            System.out.println("Guess the number between " + min + " and " + max + ":");
            while (attempts < maxAttempts && !hasGuessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;
                if (userGuess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    hasGuessedCorrectly = true;
                    System.out.println("Congratulations! You've guessed the correct number in " + attempts + " attempts.");
                }               
            }          
            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + randomNumber + ".");
            }
            int score = (maxAttempts - attempts+1) * 10;
            System.out.println("Your score is " + score + "%");
            System.out.print("Do you want to play again? (yes/no): ");
            String userResponse = scanner.next();
            playAgain = userResponse.equalsIgnoreCase("yes");
        }
        System.out.println("Thanks for playing! Goodbye.");
        scanner.close();
    }
}
