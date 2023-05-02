import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TicTacToeBot extends JFrame {
    // Creates variables and components
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
    private JButton backToMenuButton;
    private JButton fullReset;
    private JLabel emptyLabel1;
    private JLabel emptyLabel2;
    private JPanel scorePanel;
    private JPanel controlPanel;
    private JPanel menuPanel;
    private ImageIcon Logo;

    private boolean isPlayerX;
    private boolean gameEnded;

    private int Xscore = 0;
    private int Oscore = 0;
    private int Tiescore = 0;

    public TicTacToeBot() {
        setTitle("Tic Tac Toe - Bot Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500,500));
        setMinimumSize(new Dimension(400,400));
        setLocationRelativeTo(null);

        // Initializing all the JComponents
        Logo = new ImageIcon("C:\\Kode\\Java\\TicTacToe\\TicTacToe\\src\\Logo.png");
        boardButtons = new JButton[3][3];
        controlPanel = new JPanel(new GridLayout(1, 3));
        boardPanel = new JPanel(new GridLayout(3, 3,5,5));
        scorePanel = new JPanel(new GridLayout(3, 3));
        menuPanel = new JPanel(new GridLayout(2,1));
        controlButtonPanel = new JPanel(new GridLayout(2, 1));
        playerLabel = new JLabel("Player to move", SwingConstants.CENTER);
        scoreLabel = new JLabel("Score", SwingConstants.CENTER);
        XscoreLabel = new JLabel(String.valueOf(Xscore), SwingConstants.CENTER);
        XTextLabel = new JLabel("X", SwingConstants.CENTER);
        OscoreLabel = new JLabel(String.valueOf(Oscore), SwingConstants.CENTER);
        OTextLabel = new JLabel("O", SwingConstants.CENTER);
        TiescoreLabel = new JLabel(String.valueOf(Tiescore), SwingConstants.CENTER);
        TieTextLabel = new JLabel("Tie", SwingConstants.CENTER);
        backToMenuButton = new JButton("Back To Menu");
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
        menuPanel.setBackground(Color.white);

        backToMenuButton.setBorder(BorderFactory.createEmptyBorder());
        backToMenuButton.setContentAreaFilled(false);
        backToMenuButton.setFocusPainted(false);

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
                // Making all the 9 board buttons
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

                        //finds what button was pressed
                        JButton clickedButton = (JButton) e.getSource();

                        // Checks if the button is already filled
                        if (!clickedButton.getText().equals("")) {
                            return;
                        }

                        // Fill the button with the player's symbol
                        if (isPlayerX) {
                            clickedButton.setText("X");
                            clickedButton.setForeground(Color.red);
                        }

                        // Checks if the game has ended
                        if (checkWinCondition()) {
                            playerLabel.setText("Player " + (isPlayerX ? "X" : "O") + " wins!");
                            Xscore++;
                            OscoreLabel.setText(String.valueOf(Oscore));
                            XscoreLabel.setText(String.valueOf(Xscore));

                            gameEnded = true;
                            return;
                        } else{
                            isPlayerX = !isPlayerX;
                        }

                        // Checks if the game has drawed
                        if (checkDrawCondition()) {
                            playerLabel.setText("It's a draw!");
                            Tiescore++;
                            TiescoreLabel.setText(String.valueOf(Tiescore));
                            gameEnded = true;
                            return;
                        }

                        botMove();
                    }
                });

                // Adds all the buttons to the JPanel that contains the board
                boardButtons[i][j] = button;
                boardPanel.add(button);
                boardPanel.setBackground(Color.BLACK);
            }
        }

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the current width and height of the window
                int width = getWidth();
                int height = getHeight();

                // Calculate the new font size based on the height of the window
                int fontSize = Math.round((width + height) / 15);


                // Set the new font size for the button
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        boardButtons[i][j].setFont(new Font("Arial", Font.PLAIN, fontSize));
                    }
                }
            }
        });

        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameMenu();
                dispose();
            }
        });

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

        // Adds all the JComponents to their respective JPanels
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

        controlPanel.add(menuPanel);
        menuPanel.add(playerLabel);
        menuPanel.add(backToMenuButton);

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

        // Checks the two possible diagonals
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

    // Resets the board
    private void reset() {
        // Runs through the whole board and resets the text and color of the buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j].setText("");
                boardButtons[i][j].setBackground(Color.white);
            }
        }
        isPlayerX = true;
        gameEnded = false;
        playerLabel.setText("Player to move");
    }

    // Resets the hole game
    private void fullReset(){
        reset();
        Xscore = 0;
        Oscore = 0;
        Tiescore = 0;
        XscoreLabel.setText("0");
        OscoreLabel.setText("0");
        TiescoreLabel.setText("0");
    }

    private void botMove(){
        // Puts the button array which contains the board, into a string array
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = boardButtons[i][j].getText();
            }
        }

        // Generate a random position on the two dimensional board
        int x = (int)(Math.random()*3);
        int y = (int)(Math.random()*3);

        // Checks if the random position is empty
        if(board[x][y].equals("")) {
            boardButtons[x][y].setForeground(Color.blue);
            boardButtons[x][y].setText("O");

            // Checks if the move the bot made was a win or a draw
            checkDrawCondition();
            checkWinCondition();

            // If there is a win condition when the bot made a move, the bot must have won
            if(checkWinCondition()){
                playerLabel.setText("Bot O wins!");
                Oscore++;
                OscoreLabel.setText(String.valueOf(Oscore));
            }
            isPlayerX = true;
        } else {
            // If the random position wasn't empty find a new one
            botMove();
        }
    }
}
