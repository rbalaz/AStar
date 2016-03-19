package AStar.File;

public class InvalidFileException extends Exception{
    public InvalidFileException()
    {
        super();
    }
    
    public InvalidFileException(Exception e)
    {
        super(e);
    }
}
