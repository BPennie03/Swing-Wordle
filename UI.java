import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class UI extends JFrame {

    private Color bgColor = new Color(18, 18, 19);
    private ArrayList<JLabel> labels;
    private Game game;

    /**
     * Constructor for the Game's UI. It creates the Frame and adds all
     * necessary elements
     */
    public UI() {
        game = new Game();
        labels = new ArrayList<>();

        initFrame();
        initGrid();

        // Creates panels with custom methods
        JPanel guessPanel = createGuessPanel();
        JPanel titlePanel = createTitlePanel();
        JPanel textPanel = createTextPanel();

        // Adds them all to the frame in the correct layout
        add(guessPanel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.SOUTH);

        // Packs and sets the main frame/window visible
        pack();
        setVisible(true); // MUST be done after everything else
    }

    /**
     * Initializes the main frame that the Game is contained in
     */
    private void initFrame() {
        setTitle("SwingWordle");
        setPreferredSize(new Dimension(580, 800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(bgColor);
        setResizable(false);
        setLayout(new BorderLayout());
    }

    /**
     * Initializes the grid of labels that represent the letters and guesses
     */
    private void initGrid() {
        for (int i = 0; i < 5 * 6; i++) {
            JLabel label = new JLabel();
            label.setOpaque(true);
            label.setBackground(bgColor);
            label.setBorder(BorderFactory.createLineBorder(Color.white));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setText(" ");
            labels.add(label);
        }
    }

    /**
     * Creates a new panel to store the grid of labels and main game UI. A Panel is created then populated
     * with the previously created labels into a gridLayout
     * @return the created GuessPanel
     */
    private JPanel createGuessPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 350));
        panel.setLayout(new GridLayout(6, 5, 10, 10));
        panel.setBackground(bgColor);

        for (JLabel label : labels) {
            panel.add(label);
        }

        return panel;
    }

    /**
     * Creates a new Panel for the top of the frame to display the main Game title
     * @return the created titlePanel
     */
    private JPanel createTitlePanel() {
        JPanel panel = new JPanel();
        JLabel title = new JLabel();

        title.setOpaque(true);
        title.setBackground(bgColor);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setText("SwingWordle");

        panel.setBackground(bgColor);
        panel.add(title);
        return panel;
    }

    /**
     * Creates the panel and all necessary components of the TextPanel. This panel contains the JTextField
     * and submit button for the user to enter their guesses
     * @return the completed TextPanel
     */
    private JPanel createTextPanel() {
        JPanel inputPanel = new JPanel();
        JTextField guessTextField = new JTextField(10);
        JButton guessButton = new JButton("Guess");

        GuessManager guessManager = new GuessManager(game, labels);

        // Adds an action listener to the guess button to allow the submit button to enter
        // the user's submitted guess. 
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guess = guessTextField.getText().trim().toLowerCase();
                guessManager.makeGuess(guess);
                guessTextField.setText("");
            }
        });

        // Adds an action listener to the <Enter> key on the keytboard to allow the user to enter
        // their submitted guess. 
        guessTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String guess = guessTextField.getText().trim().toLowerCase();
                    guessManager.makeGuess(guess);
                    guessTextField.setText("");
                }
            }
        });

        // Configure and return panel
        inputPanel.setBackground(bgColor);
        inputPanel.setPreferredSize(new Dimension(this.getWidth(), 80));
        inputPanel.add(guessTextField);
        inputPanel.add(guessButton);

        return inputPanel;
    }

    /**
     * Getter for the list of labels
     * @return the list of labels that represent each character of a guess
     */
    public ArrayList<JLabel> getLabels() {
        return labels;
    }
}
