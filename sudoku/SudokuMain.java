package sudoku;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SudokuMain extends JFrame {

    // private variables
    GameBoard board = new GameBoard();

    // Start Menu
    Container startMenu = getContentPane(); // Start Menu
    JButton easyBtn = new JButton("Easy");
    JButton mediumBtn = new JButton("Medium");
    JButton hardBtn = new JButton("Hard");

    // Custom fonts
    static Font pixelMplus;
    static Font pixelMplusTitle;

    static ImageIcon sudokuIcon;

    // Constructor
    public SudokuMain() {
        // Load custom font
        try {
            String font_path = getClass().getResource("PixelMplus10-Regular.ttf").getPath();

            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File(font_path)).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(
                    Font.createFont(Font.TRUETYPE_FONT, new File(font_path)));

            pixelMplusTitle = Font.createFont(Font.TRUETYPE_FONT, new File(font_path)).deriveFont(40f);
            GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge2.registerFont(
                    Font.createFont(Font.TRUETYPE_FONT, new File(font_path)));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error on loading the font type", "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            final String icon_path = getClass().getResource("icon.png").getPath();
            sudokuIcon = new ImageIcon(icon_path);
           
            // cp.setIconImage(sudokuIcon.getImage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error on loading favicon", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Start Menu-----------------------------------
        startMenu.setLayout(new BorderLayout());
        // Title Panel
        JPanel title = new JPanel(new GridLayout(0, 1, 15, 5));
        JLabel label = new JLabel("SUDUKU", SwingConstants.CENTER);

        label.setFont(pixelMplusTitle);
        label.setBorder(new EmptyBorder(40, 0, 0, 0));
        title.add(label);

        // Level Option Panel
        easyBtn.setFont(pixelMplus);
        mediumBtn.setFont(pixelMplus);
        hardBtn.setFont(pixelMplus);
        JPanel center = new JPanel(new GridLayout(0, 1, 10, 10));
        center.add(easyBtn);
        center.add(mediumBtn);
        center.add(hardBtn);
        center.setBorder(new EmptyBorder(80, 80, 80, 80)); // top, left, btm, right

        startMenu.add(title, BorderLayout.NORTH);
        startMenu.add(center, BorderLayout.CENTER);
        

        // Buttons Listener
        AllButtonsListener listener = new AllButtonsListener();
        easyBtn.addActionListener(listener);
        mediumBtn.addActionListener(listener);
        hardBtn.addActionListener(listener);

        setIconImage(sudokuIcon.getImage());
        setSize(400, 400);
        setVisible(true);
        setTitle("Sudoku");
        setLocationRelativeTo(null);// display at the center
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Handle window closing

    }

    /** The entry main() entry method */
    public static void main(String[] args) {
        // [TODO 1] Check Swing program template on how to run the constructor
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SudokuMain sudokuMain = new SudokuMain();
            }
        });
    }

    private class AllButtonsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String btnLabel = evt.getActionCommand();
            startMenu.setVisible(false);
            TimeManagement.startTimer(); // start timer

            // All the different method according to the level
            // chosen!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            Random random = new Random(); // initialize random
            // assign each difficulty level the number of shown cells
            // the harder, the lesser cell to show
            if (btnLabel.equals("Easy")) {
                board.init(random.nextInt(6) + 70);
                new GamePanel(board, "Difficulty: Easy");

            } else if (btnLabel.equals("Medium")) {
                board.init(random.nextInt(6) + 45);
                new GamePanel(board, "Difficulty: Easy");

            } else {
                board.init(random.nextInt(6) + 20);
                new GamePanel(board, "Difficulty: Easy");
            }
        }
    }

}
