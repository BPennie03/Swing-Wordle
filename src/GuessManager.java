package src;
import java.awt.*;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GuessManager {
    private Game game;
    private ArrayList<JLabel> labelBoxes;
    private ArrayList<String> guessHistory;

    // Colors taken directly from the real Wordle game
    private Color greenColor = new Color(83, 141, 78); 
    private Color yellowColor = new Color(181, 159, 59);

    /**
     * Constructor for the GuessManager
     * @param game the current game
     * @param labelBoxes the list of labels that were created from the UI
     */
    public GuessManager(Game game, ArrayList<JLabel> labelBoxes) {
        this.game = game;
        this.labelBoxes = labelBoxes;
        this.guessHistory = new ArrayList<>();
    }

    /**
     * Takes a uses guess and sanity checks its length, then passes it on to the helper method to update the UI
     * @param guess string representation of the user's submitted guess
     */
    public void makeGuess(String guess) {
        // Sends an alert/error if the user submits a guess that is not 5 characters
        if (guess.length() != game.getTargetWord().length()) {
            JOptionPane.showMessageDialog(null, "Guess must be 5 letters");
            return;
        }

        int[] correctPositions = game.checkGuess(guess);
        updateUI(correctPositions, guess);

        // Add the current guess to the history
        guessHistory.add(guess);
    }

    /**
     * Helper method to the makeGuess method to update the UI based on the user's guess
     * @param correctPositions
     * @param guess
     */
    private void updateUI(int[] correctPositions, String guess) {
        int row = guessHistory.size();
        int col = 5; // Columns are always 5

        // A "sum" used to check if all 5 letters are correct 
        int guessNum = 0;

        for (int i = 0; i < guess.length(); i++) {
            
            int index = row * col + i;

            // Avoid IndexOutOfBoundsException
            if (index >= labelBoxes.size()) { 
                break; 
            }

            JLabel label = labelBoxes.get(index);

            // 1 if the letter is completely correct
            // -1 if the letter is in the word, but in the wrong spot
            // 0 if the letter is not in the word at all
            if (correctPositions[i] == 1) {
                label.setText(Character.toString(guess.charAt(i)).toUpperCase());
                label.setBackground(greenColor);
                guessNum++;
            } else if (correctPositions[i] == -1) {
                label.setText(Character.toString(guess.charAt(i)).toUpperCase());
                label.setBackground(yellowColor);
            } else {
                label.setText(Character.toString(guess.charAt(i)).toUpperCase());
                label.setBackground(Color.gray);
            }
        }

        // If all 5 letters were in correct spot 
        if (guessNum == 5) {
            JOptionPane.showMessageDialog(null, "YOU WIN");
        }else if (guessHistory.size()==5){

            JOptionPane.showMessageDialog(null, "You lose, the word was "+game.getTargetWord());
        }
    }
}
