package NumberGame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class number_game_proj extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JLabel statusLabel;
    private JLabel attemptsLabel;
    private JLabel roundsLabel;
    private JLabel nameLabel;
    private JLabel scoreLabel;

    private int randomNumber;
    private int maxAttempts = 10; 
    private int currentAttempts;
    private int rounds;
    private int score;
    private String userName;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    number_game_proj frame = new number_game_proj();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public number_game_proj() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 901, 531);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Enter the Number Between 1 to 100");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(46, 338, 332, 25);
        contentPane.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField.setBounds(46, 385, 332, 30);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("ENTER");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });
        btnNewButton.setBounds(388, 385, 93, 30);
        contentPane.add(btnNewButton);

        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        statusLabel.setBounds(490, 145, 387, 69);
        contentPane.add(statusLabel);

        attemptsLabel = new JLabel("");
        attemptsLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        attemptsLabel.setBounds(645, 325, 180, 25);
        contentPane.add(attemptsLabel);

        roundsLabel = new JLabel("");
        roundsLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        roundsLabel.setBounds(645, 360, 180, 25);
        contentPane.add(roundsLabel);

        nameLabel = new JLabel("Enter Player Name:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameLabel.setBounds(60, 128, 200, 25);
        contentPane.add(nameLabel);

        JTextField nameTextField = new JTextField();
        nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameTextField.setBounds(60, 163, 200, 30);
        contentPane.add(nameTextField);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userName = nameTextField.getText();
                startNewGame();
            }
        });
        startButton.setBounds(270, 165, 150, 30);
        contentPane.add(startButton);

        scoreLabel = new JLabel("");
        scoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        scoreLabel.setBounds(645, 410, 180, 25);
        contentPane.add(scoreLabel);
        
        lblNewLabel = new JLabel("Status");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(490, 121, 234, 39);
        contentPane.add(lblNewLabel);
        
        lblNewLabel_2 = new JLabel("NUMBER GAME");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 34));
        lblNewLabel_2.setBounds(60, 10, 407, 89);
        contentPane.add(lblNewLabel_2);

    }

    private void startNewGame() {
        score = 0;
        rounds = 0;
        startNewRound();
        nameLabel.setText("Player: " + userName);
    }

    private void startNewRound() {
        if (rounds == 6) {
            score = 0;
            rounds = 0;
        }

        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        currentAttempts = 0;
        rounds++;
        updateLabels();
    }

    private void handleGuess() {
        try {
            int userGuess = Integer.parseInt(textField.getText());

            if (userGuess < 1 || userGuess > 100) {
                statusLabel.setText("Please enter a number between 1 and 100");
            } else {
                currentAttempts++;

                if (userGuess == randomNumber) {
                    statusLabel.setText("Congratulations!The number is correct.");
                    score += calculateScore();
                    startNewRound();
                } else if (currentAttempts >= maxAttempts) {
                    statusLabel.setText("Maximum attempts Reached. Sorry!");
                    startNewRound();
                } else {
                    statusLabel.setText(getHint(userGuess));
                }
            }

            updateLabels();
        } catch (NumberFormatException ex) {
            statusLabel.setText("Invalid input.Please enter a valid number.");
        }
    }

    private String getHint(int userGuess) {
        if (userGuess < randomNumber) {
            return "Try a higher number.";
        } else {
            return "Try a lower number.";
        }
    }

    private void updateLabels() {
        attemptsLabel.setText("Attempts: " + currentAttempts + "/" + maxAttempts);
        roundsLabel.setText("Round: " + (rounds == 6 ? "Final Round" : rounds));
        scoreLabel.setText("Score: " + score);
    }

    private int calculateScore() {
        return maxAttempts - currentAttempts + 1;
    }
}
