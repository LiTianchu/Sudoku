import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SdkFramework extends JFrame {
    private JButton restartBtn;
    private JButton settingBtn;
    private JButton checkBtn;
    private int rowColNum;
    private int numOfCells;
    private int[] sdkNumbers;

    public SdkFramework() {
        Container sdkContainer = getContentPane();
        sdkContainer.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());

        restartBtn = new JButton("Restart");
        settingBtn = new JButton("Setting");
        checkBtn = new JButton("Check");
        topPanel.add(restartBtn);
        topPanel.add(settingBtn);
        topPanel.add(checkBtn);
        sdkContainer.add(topPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 620);
        setTitle("Sudoku  Ver 1.0");
        setVisible(true);

       // numOfCells = (int) Math.pow(rowColNum, 2);
      //  JPanel sdkGrids = new JPanel(new GridLayout(rowColNum, rowColNum));
       // for (int i = 0; i < numOfCells; i++) {
            

      //  }

       // sdkContainer.add(sdkGrids);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SdkFramework sdkGame = new SdkFramework();
                SdkNumGenerator generator = new SdkNumGenerator(9);
                System.out.print(generator.generate());
                System.out.print("");
            }
        });
    }

}
