import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

public class SdkCell {
    private JTextField field;

    public SdkCell(JTextField field){
        this.field=field;
    }

    public void setNumber(int number){
        field.setText(number+"");
    }

    public JTextField getCell(){
        return field;
    }

    public void disableCell(){
        field.setEditable(false);
        
    }

    public void activateCell(){
        field.setEditable(true);
    }

    public void setCellSize(int width, int height){
        field.setSize(width, height);
    }

}
