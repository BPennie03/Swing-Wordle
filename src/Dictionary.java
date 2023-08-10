package src;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Custom Dictionary class implemented as an ArrayList to store and grab words from the list
 */
public class Dictionary extends ArrayList<String> {

    private File file;
    private Random random;

    /**
     * Constructor for the Dictionary object
     */
    public Dictionary() {

        // Initializes a new dictionary object with words from the word list
        file = new File("wordList.txt");
        Scanner input;

        try {
            input = new Scanner(file);

            while (input.hasNextLine()) {
                add(input.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        random = new Random();
    }

    /**
     * Gets and returns a random word from the dictionary
     * @return a random selected word from the file
     */
    public String getRandomWord() {
        if (isEmpty()) {
            throw new IllegalStateException("Word list is empty");
        }

        int randomIndex = random.nextInt(size());
        return get(randomIndex);
    }
}
