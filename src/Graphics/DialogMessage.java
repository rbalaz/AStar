package AStar.Graphics;

public abstract class DialogMessage {
    private String stringMessage;
    private final String title;
    
    public DialogMessage(String title, String stringMessage)
    {
        this.stringMessage = stringMessage;
        this.title = title;
    }
    
    public void addToMessage(String aggregate)
    {
        stringMessage = stringMessage.concat(aggregate);
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getMessage()
    {
        return stringMessage;
    }
    
    public abstract void displayMessage();
}
