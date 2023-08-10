/**
 * This class will eventually control the main game loop
 */
public class Game {
    private String targetWord;

    /**
     * Constructor for the Game
     */
    public Game() {
        Dictionary dict = new Dictionary();
        targetWord = dict.getRandomWord();
    }

    /**
     * Getter for the games "Target word"
     * @return the target word
     */
    public String getTargetWord() {
        return targetWord;
    }

    /**
     * Creates a new array the same size as the user's guess (5 letters), then updates
     * each index depending on if the letter is correct or not. 1 represents a correct letter,
     * -1 represents a correct letter in the wrong spot, and 0 represents an incorrect letter
     * @param guess the string of the user's guess
     * @return array with 5 length containing 1, -1, or 0
     */
    public int[] checkGuess(String guess) {
        int[] result = new int[guess.length()];

        for (int i = 0; i < guess.length(); i++) {
            char guessedChar = guess.charAt(i);
            char targetChar = targetWord.charAt(i);

            if (guessedChar == targetChar) {
                result[i] = 1; // Correct letter in the correct position
            } else if (targetWord.contains(String.valueOf(guessedChar))) {
                result[i] = -1; // Correct letter but in the wrong position
            } else {
                result[i] = 0; // Incorrect letter
            }
        }

        // The resulting array
        return result;
    }
}