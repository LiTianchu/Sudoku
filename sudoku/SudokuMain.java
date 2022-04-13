package sudoku;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SudokuMain extends JFrame {
    // private variables
    private GameBoard board = new GameBoard();

    // Start Menu
    private Container startMenu = getContentPane(); // Start Menu
    private JButton easyBtn = new JButton("Easy");
    private JButton mediumBtn = new JButton("Medium");
    private JButton hardBtn = new JButton("Hard");

    // Custom fonts
    static Font pixelMplus;
    static Font pixelMplusTitle;

    static ImageIcon sudokuIcon;

    // Color
    static final Color darkblue = new Color(79, 93, 117);
    static final Color darkerblue = new Color(45, 49, 66);
    static final Color skyblue = new Color(234, 244, 244);
    static final Color yellow = new Color(252, 163, 17);
    static final Color grey = new Color(229, 229, 229);
    static final Color myRed = new Color(255, 94, 91);
    static final Color orange = new Color(239, 131, 84);

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
        JPanel title = new JPanel(new GridLayout(0, 1, 30, 5));
        JLabel label = new JLabel("SUDOKU", SwingConstants.CENTER);

        label.setFont(pixelMplusTitle);
        label.setBorder(new EmptyBorder(40, 0, 0, 0));
        title.add(label);

        // Level Option Panel
        easyBtn.setFont(pixelMplus);
        mediumBtn.setFont(pixelMplus);
        hardBtn.setFont(pixelMplus);

        easyBtn.setBackground(darkblue);
        mediumBtn.setBackground(darkblue);
        hardBtn.setBackground(darkblue);

        easyBtn.setForeground(Color.white);
        mediumBtn.setForeground(Color.white);
        hardBtn.setForeground(Color.white);

        JPanel center = new JPanel(new GridLayout(0, 1, 45, 10));
        center.add(easyBtn);
        center.add(mediumBtn);
        center.add(hardBtn);
        center.setBorder(new EmptyBorder(80, 80, 80, 80)); // top, left, btm, right

        startMenu.setBackground(Color.white);

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
                Music music = new Music();
                music.playMusic();
                SudokuMain sudokuMain = new SudokuMain();

            }
        });
    }

    private class AllButtonsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String btnLabel = evt.getActionCommand();
            startMenu.setVisible(false);
            TimeManager.startTimer(); // start timer

            // All the different method according to the level
            // chosen!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            Random random = new Random(); // initialize random
            // assign each difficulty level the number of shown cells
            // the harder, the lesser cell to show
            if (btnLabel.equals("Easy")) {
                board.init(random.nextInt(6) + 70);
                new SudokuPanel(board, "Easy");

            } else if (btnLabel.equals("Medium")) {
                board.init(random.nextInt(6) + 45);
                new SudokuPanel(board, "Medium");

            } else {
                board.init(random.nextInt(6) + 20);
                new SudokuPanel(board, "Hard");
            }
        }
    }

}
