package AStar.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WarningMessage extends DialogMessage{
    private final JFrame frame;
    
    public WarningMessage(String title, String stringMessage, JFrame frame)
    {
        super(title,stringMessage);
        this.frame = frame;
    }
    
    @Override
    public void displayMessage()
    {
        JOptionPane.showMessageDialog(frame,getMessage(),getTitle(),JOptionPane.WARNING_MESSAGE);
    }
}
