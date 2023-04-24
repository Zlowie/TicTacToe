import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class TicTacToe {
    //function initializes board
    private static char[][] board;
    private final char player1 = 'X';
    private final char player2 = 'O';
    private static char currentPlayer;

    //Starts game
    public TicTacToe() {
        board = new char[3][3];
        initializeBoard();
        currentPlayer = player1;
    }

    //Creates board
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    //prints board
    private void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    //checks if board is full
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    //runs when the player makes a move
    private static boolean makeMove(int row, int col) {
        //checker om movet er lovligt
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            //adding the move to the board
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    //checks if player has won
    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) return true;
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[0][i] == board[2][i]) return true;
        }

        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) return true;
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) return true;

        return false;
    }

    //Changes player turn
    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }


    //main function
    public static void main(String[] args) {
        //Creates GUI
        JFrame frame = new JFrame("Tic Tac Toe");
        JPanel gamePanel = new JPanel();
        JPanel sidePanel = new JPanel();


        //initializing elements for game panel
        JButton b1 = new JButton("");
        JButton b2 = new JButton("");
        JButton b3 = new JButton("");
        JButton b4 = new JButton("");
        JButton b5 = new JButton("");
        JButton b6 = new JButton("");
        JButton b7 = new JButton("");
        JButton b8 = new JButton("");
        JButton b9 = new JButton("");

        //initialize elements for side panel
        JButton bReset = new JButton("Reset");
        JLabel titleText = new JLabel("Tic Tac Toe");
        JLabel turnText = new JLabel("Player 1 turn");
        JLabel scoreText = new JLabel("1-1");

        //adding actionlisteners
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeMove(0,0);
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // adding elements to the game Panel
        gamePanel.add(b1); gamePanel.add(b2); gamePanel.add(b3);
        gamePanel.add(b4); gamePanel.add(b5); gamePanel.add(b6);
        gamePanel.add(b7); gamePanel.add(b8); gamePanel.add(b9);
        gamePanel.setBackground(Color.BLACK);

        //adding elements to side panel
        sidePanel.add(titleText);
        sidePanel.add(turnText);
        sidePanel.add(scoreText);
        sidePanel.add(bReset);


        //setting values on JFrame
        gamePanel.setLayout(new GridLayout(3,3,5,5));
        sidePanel.setLayout(new GridLayout(8,1));
        frame.setLayout(new BorderLayout());
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(sidePanel, BorderLayout.WEST);
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);


        //starts the game
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;




        //runs as long as the game is not over
        while (!gameOver) {
            System.out.println("Player " + game.currentPlayer + "'s turn. Enter row (1-3) and column (1-3):");
            turnText.setText(game.currentPlayer + " to move");

            //checks for player input
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            //checks the moevs result
            if (game.makeMove(row, col)) {
                game.printBoard();

                //checks if game is over

                if (game.checkWinner()) {
                    //checks if player has won
                    System.out.println("Player " + game.currentPlayer + " wins!");
                    turnText.setText(game.currentPlayer + " wins!");
                    gameOver = true;
                } else if (game.isBoardFull()) {
                    //checks if board is full
                    System.out.println("It's a draw!");
                    turnText.setText("It's a draw!");
                    gameOver = true;
                } else {
                    //change player turn
                    game.switchPlayer();
                }
            }
        }
    }



}
