package AStar.Solver;

import java.util.List;

public class Operators {
    private final List<Block> blocks;
    private final int widthSize;
    private final int heightSize;
            
    public Operators(List<Block> blocks, int widthSize, int heightSize)
    {
        this.blocks = blocks;
        this.heightSize = heightSize;
        this.widthSize = widthSize;
    }
    
    public Node operate(int x, int y, int finishWidth, int finishHeight, Node current)
    {
        if(checkOperation(current,x,y))
        {
            Node newNode = new Node(current.getWidth() + x, current.getHeight() + y, calculateG(x,y) + current.getG(),AStarAlgorithm.calculateH(current.getHeight() + y,current.getWidth() + x,finishHeight,finishWidth),current);
            return newNode;
        }
        else
            return null;
    }
    
    private int calculateG(int x, int y)
    {
        if(Math.abs(x) > 0 && Math.abs(y) > 0)
            return 14;
        else
            return 10;
    }
    
    private boolean checkOperation(Node current,int x, int y)
    {
        if(current.getWidth() + x > widthSize || current.getWidth() + x <= 0)
            return false;
        if(current.getHeight() + y > heightSize || current.getHeight() + y <= 0)
            return false;
        return blocks.stream().noneMatch((block) -> (block.isBlock(current.getWidth() + x, current.getHeight() + y)));
    }
    
}
