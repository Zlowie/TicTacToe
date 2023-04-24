import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame {

    private JButton[][] boardButtons;
    private JPanel boardPanel;
    private JPanel controlButtonPanel;
    private JLabel playerLabel;
    private JButton resetButton;
    private JLabel scoreLabel;
    private JLabel XscoreLabel;
    private JLabel XTextLabel;
    private JLabel OscoreLabel;
    private JLabel OTextLabel;
    private JLabel TiescoreLabel;
    private JLabel TieTextLabel;
    private JButton fullReset;
    private JLabel emptyLabel1;
    private JLabel emptyLabel2;
    private JPanel scorePanel;
    private JPanel controlPanel;
    private ImageIcon Logo;

    private boolean isPlayerX;
    private boolean gameEnded;

    private int Xscore = 0;
    private int Oscore = 0;
    private int Tiescore = 0;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500,500));
        setMinimumSize(new Dimension(400,400));
        setLocationRelativeTo(null);

        Logo = new ImageIcon("C:\\Kode\\Java\\TicTacToe\\TicTacToe\\src\\Logo.png");
        boardButtons = new JButton[3][3];
        controlPanel = new JPanel(new GridLayout(1, 3));
        boardPanel = new JPanel(new GridLayout(3, 3,5,5));
        scorePanel = new JPanel(new GridLayout(3, 3));
        controlButtonPanel = new JPanel(new GridLayout(2, 1));
        playerLabel = new JLabel("X to move", SwingConstants.CENTER);
        scoreLabel = new JLabel("Score", SwingConstants.CENTER);
        XscoreLabel = new JLabel(String.valueOf(Xscore), SwingConstants.CENTER);
        XTextLabel = new JLabel("X", SwingConstants.CENTER);
        OscoreLabel = new JLabel(String.valueOf(Oscore), SwingConstants.CENTER);
        OTextLabel = new JLabel("O", SwingConstants.CENTER);
        TiescoreLabel = new JLabel(String.valueOf(Tiescore), SwingConstants.CENTER);
        TieTextLabel = new JLabel("Tie", SwingConstants.CENTER);
        resetButton = new JButton("Reset Board");
        fullReset = new JButton("New Game");
        emptyLabel1 = new JLabel("");
        emptyLabel2 = new JLabel("");
        isPlayerX = true;
        gameEnded = false;

        //Styling section
        controlPanel.setBackground(Color.white);
        controlButtonPanel.setBackground(Color.white);
        scorePanel.setBackground(Color.white);
        fullReset.setBorder(BorderFactory.createEmptyBorder());
        fullReset.setContentAreaFilled(false);
        fullReset.setFocusPainted(false);
        resetButton.setBorder(BorderFactory.createEmptyBorder());
        resetButton.setContentAreaFilled(false);
        resetButton.setFocusPainted(false);
        XTextLabel.setForeground(Color.red);
        OTextLabel.setForeground(Color.blue);
        setIconImage(Logo.getImage());

        // Initialize the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //making all the 9 board buttons
                JButton button = new JButton("");
                button.setFont(new Font("Arial", Font.PLAIN, 60));
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setBackground(Color.white);
                button.setFocusPainted(false);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameEnded) {
                            return;
                        }

                        JButton clickedButton = (JButton) e.getSource();
                        int row = -1;
                        int col = -1;

                        // Find the row and column of the clicked button
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (boardButtons[i][j] == clickedButton) {
                                    row = i;
                                    col = j;
                                }
                            }
                        }

                        if (row == -1 || col == -1) {
                            return;
                        }

                        // Check if the button is already filled
                        if (!clickedButton.getText().equals("")) {
                            return;
                        }

                        // Fill the button with the player's symbol
                        if (isPlayerX) {
                            clickedButton.setText("X");
                            clickedButton.setForeground(Color.red);
                            playerLabel.setText("O to move");
                        } else {
                            clickedButton.setText("O");
                            clickedButton.setForeground(Color.blue);
                            playerLabel.setText("X to move");
                        }

                        // Check if the game has ended
                        if (checkWinCondition()) {
                            playerLabel.setText("Player " + (isPlayerX ? "X" : "O") + " wins!");
                            if(isPlayerX){
                                Xscore++;
                            }
                            else{
                                Oscore++;
                            }
                            OscoreLabel.setText(String.valueOf(Oscore));
                            XscoreLabel.setText(String.valueOf(Xscore));

                            gameEnded = true;
                            System.out.println(gameEnded);
                            return;
                        }

                        if (checkDrawCondition()) {
                            playerLabel.setText("It's a draw!");
                            Tiescore++;
                            TiescoreLabel.setText(String.valueOf(Tiescore));
                            gameEnded = true;
                            return;
                        }

                        // Switch to the next player
                        isPlayerX = !isPlayerX;
                    }
                });

                boardButtons[i][j] = button;
                boardPanel.add(button);
                boardPanel.setBackground(Color.BLACK);
            }
        }

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        fullReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullReset();
            }
        });


        controlPanel.add(scorePanel);
        scorePanel.add(emptyLabel1);
        scorePanel.add(scoreLabel);
        scorePanel.add(emptyLabel2);
        scorePanel.add(XscoreLabel);
        scorePanel.add(TiescoreLabel);
        scorePanel.add(OscoreLabel);
        scorePanel.add(XTextLabel);
        scorePanel.add(TieTextLabel);
        scorePanel.add(OTextLabel);

        controlPanel.add(playerLabel);

        controlPanel.add(controlButtonPanel);
        controlButtonPanel.add(resetButton);
        controlButtonPanel.add(fullReset);

        add(boardPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Check if the game has ended in a draw
    private boolean checkDrawCondition() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardButtons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if any player has won
    private boolean checkWinCondition() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = boardButtons[i][j].getText();
            }
        }

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("")) {
                for(int j = 0; j < 3; j++){
                    boardButtons[i][j].setBackground(Color.lightGray);
                }
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j].equals(board[1][j]) && board[1][j].equals(board[2][j]) && !board[0][j].equals("")) {
                for(int i = 0; i < 3; i++){
                    boardButtons[i][j].setBackground(Color.lightGray);
                }
                return true;
            }
        }

        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("")) {
            for(int i = 0; i < 3; i++){
                boardButtons[i][i].setBackground(Color.lightGray);
            }
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("")) {
            boardButtons[0][2].setBackground(Color.lightGray);
            boardButtons[1][1].setBackground(Color.lightGray);
            boardButtons[2][0].setBackground(Color.lightGray);

            return true;
        }

        return false;
    }

    // Reset the board
    private void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j].setText("");
                boardButtons[i][j].setBackground(Color.white);
            }
        }
        isPlayerX = true;
        gameEnded = false;
        playerLabel.setText("X to move");
    }

    // Reset the hole game
    private void fullReset(){
        reset();
        Xscore = 0;
        Oscore = 0;
        Tiescore = 0;
        XscoreLabel.setText("0");
        OscoreLabel.setText("0");
        TiescoreLabel.setText("0");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}