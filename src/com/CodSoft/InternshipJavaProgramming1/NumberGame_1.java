package com.CodSoft.InternshipJavaProgramming1;

import java.util.Random;
import java.util.Scanner;

public class NumberGame_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int attemptsLimit = 10;
        int rounds = 0;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            int targetNumber = rd.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nRound " + (rounds + 1));
            System.out.println("Guess the number between " + lowerBound + " and " + upperBound);

            while (attempts < attemptsLimit) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();

                if (userGuess == targetNumber) {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You guessed the correct number.");
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }

                attempts++;
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was: " + targetNumber);
            } else {
                score += attemptsLimit - attempts;
            }

            System.out.println("Your current score: " + score);
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = sc.next();

            if ("no".equalsIgnoreCase(playAgain)) {
                break;
            }

            rounds++;

        } while (true);

        System.out.println("Thanks for playing! Your final score: " + score);

        sc.close();
    }
}