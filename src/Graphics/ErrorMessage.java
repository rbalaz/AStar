package AStar.Graphics;

import javax.swing.*;

public class ErrorMessage extends DialogMessage{
    private final JFrame frame;
    
    public ErrorMessage(String title, String stringMessage, JFrame frame)
    {
        super(title,stringMessage);
        this.frame = frame;
    }
    
    @Override
    public void displayMessage()
    {
        JOptionPane.showMessageDialog(frame,getMessage(),getTitle(),JOptionPane.ERROR_MESSAGE);
    }
}
