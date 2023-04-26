import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameMenu extends JFrame {
    public GameMenu() {

        // Create the menu components
        ImageIcon Logo = new ImageIcon("C:\\Kode\\Java\\TicTacToe\\TicTacToe\\src\\Logo.png");
        JLabel titleLabel = new JLabel("Tic Tac Toe", SwingConstants.CENTER);
        JLabel creditLabel = new JLabel("by Asger S. & Casper R.", SwingConstants.CENTER);
        JPanel textPanel = new JPanel(new GridLayout(2,1));
        JButton local2P = new JButton("Local 2 Player");
        JButton exitButton = new JButton("Exit");
        JButton singlePlayer = new JButton("Singleplayer");
        JPanel menuPanel = new JPanel(new GridLayout(4, 1,0,5));

        setIconImage(Logo.getImage());

        //Styling section
        //styling menuPanel
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        menuPanel.setBackground(Color.white);

        //styling textPanel
        textPanel.setBackground(Color.white);

        //styling titleLabel
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 30));

        //styling local2P
        local2P.setFont(new Font("Arial", Font.BOLD, 20));
        local2P.setForeground(Color.WHITE);
        local2P.setBackground(new Color(52, 152, 219));
        local2P.setBorder(BorderFactory.createRaisedBevelBorder());
        local2P.setFocusPainted(false);

        //styling singlePlayer
        singlePlayer.setFont(new Font("Arial", Font.BOLD, 20));
        singlePlayer.setForeground(Color.WHITE);
        singlePlayer.setBackground(new Color(52, 152, 219));
        singlePlayer.setBorder(BorderFactory.createRaisedBevelBorder());
        singlePlayer.setFocusPainted(false);

        //styling exitButton
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(52, 152, 219));
        exitButton.setBorder(BorderFactory.createRaisedBevelBorder());
        exitButton.setFocusPainted(false);

        // Add the menu components to a JPanel
        menuPanel.add(textPanel);
        textPanel.add(titleLabel);
        textPanel.add(creditLabel);
        menuPanel.add(local2P);
        menuPanel.add(singlePlayer);
        menuPanel.add(exitButton);


        // Add an ActionListener to each button
        local2P.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //starts a local 2 player game
                new TicTacToe();
                dispose();
            }
        });

        singlePlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //starts a singleplayer game
                new TicTacToeBot();
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle exit button click
                System.exit(0);
            }
        });


        // Add the menu panel to the JFrame
        add(menuPanel);

        // Configure the JFrame
        setTitle("Tic Tac Toe - Menu");
        setSize(400, 300);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }


    //main function / initializes game
    public static void main(String[] args) {
        new GameMenu();
    }
}
