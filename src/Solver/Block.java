package AStar.Solver;

public class Block {
    private final int width;
    private final int height;
    
    public Block(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    public boolean isBlock(int x, int y)
    {
        return (width == y && height == x);
    }
    
    public int getWidth()
    {
        return this.width;
    }
    
    public int getHeight()
    {
        return this.height;
    }
}
