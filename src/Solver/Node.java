package AStar.Solver;

public class Node {
    private final int width;
    private final int height;
    private final int g;
    private final int h;
    private final int f;
    private Node parent = null;
    
    public Node(int width, int height, int g, int h)
    {
        this.width = width;
        this.height = height;
        this.g = g;
        this.h = h;
        this.f = g + h;
    }
    
    public Node(int width, int height, int g, int h, Node parent)
    {
        this(width,height,g,h);
        this.parent = parent;
    }
    
    public boolean compare(Node node)
    {
        return (this.height == node.getHeight()) && (this.width == node.getWidth());
    }
    
    public int getWidth()
    {
        return this.width;
    }
    
    public int getHeight()
    {
        return this.height;
    }
    
    public Node returnWorse(Node node)
    {
        if(this.f < node.getF())
            return node;
        else
            return this;
    }
    
    public int getF()
    {
        return this.f;
    }
    
    public int getG()
    {
        return this.g;
    }
    
    public int getH()
    {
        return this.h;
    }
    
    public boolean oneMove(Node node)
    {
        return Math.abs(this.height - node.getHeight()) <= 1 && Math.abs(this.width - node.getWidth()) <= 1;
    }
    
    public void printNode()
    {
        System.out.println("X: " + width + " Y: "+ height + " F: " + f);
    }
    
    public Node getParent()
    {
        return parent;
    }
    
    public void setParent(Node node)
    {
        this.parent = node;
    }
}
