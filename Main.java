import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.*;


public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        ArrayList<Character> wordState = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        int guesses = 0;
        String word;
        int score = 300;

        try(BufferedReader reader = new BufferedReader(new FileReader("words.txt"))){
            String line;
            while ((line = reader.readLine()) != null){
                words.add(line);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File could not be read.");
        }
        catch(IOException e){
            System.out.println("Error.");
        }
        word = words.get(random.nextInt(0, words.size()));

        for (int i = 0; i < word.length(); ++i){
            wordState.add('_');
        }

        System.out.println("*************************");
        System.out.println("Welcome to Java Hangman!");
        System.out.println("*************************");
        System.out.printf("Current Score: %d\n", score);
        System.out.println("*************************");


        System.out.print("Word: ");
        for (char i: wordState){
        System.out.print(i + " ");
        }
        System.out.print("\n");

        while (guesses < 6){
            System.out.print("\nGuess a letter: ");
            String input = scanner.next().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("\nInvalid input. Program exited.");
                System.exit(0);
            }

            char guess = input.charAt(0);


            System.out.printf("Your Guess: %c\n", guess);

            if(word.indexOf(guess) >= 0){
                System.out.println("\nCorrect Guess!");
                for(int i = 0; i < word.length(); ++i){
                    if(word.charAt(i) == guess){
                        wordState.set(i, guess);
                    }
                }
            }
            else{
                System.out.println("\nIncorrect Guess, " + (5 - guesses) + " remaining");
                guesses++;
                score -= 50;
                System.out.printf("Score Decreased: %d\n", score);
            }
            if(!wordState.contains('_')){
                break;
            }
            for (char c: wordState){
                System.out.print(c + " ");
            }
            System.out.print("\n");
            System.out.println(getHangmanArt(guesses));
        }
        if(guesses > 5){
            System.out.println(getHangmanArt(guesses));
            System.out.println("You Lost!");
        }
        else{
            System.out.println("You won!");
        }
        System.out.printf("\nThe word was %s", word);
        System.out.printf("\nScore Result: %d", score);
        scanner.close();
    }
    static String getHangmanArt(int guesses){
    return switch(guesses){
        case 0 -> """
                  +---+
                  |   |
                      |
                      |
                      |
                      |
                =========""";
        case 1 -> """
                  +---+
                  |   |
                  O   |
                      |
                      |
                      |
                =========""";
        case 2 -> """
                  +---+
                  |   |
                  O   |
                  |   |
                      |
                      |
                =========""";
        case 3 -> """
                  +---+
                  |   |
                  O   |
                 /|   |
                      |
                      |
                =========""";
        case 4 -> """
                  +---+
                  |   |
                  O   |
                 /|\\  |
                      |
                      |
                =========""";
        case 5 -> """
                  +---+
                  |   |
                  O   |
                 /|\\  |
                 /    |
                      |
                =========""";
        case 6 -> """
                  +---+
                  |   |
                  O   |
                 /|\\  |
                 / \\  |
                      |
                =========""";
        default -> "Invalid number of guesses";
    };
    }
}