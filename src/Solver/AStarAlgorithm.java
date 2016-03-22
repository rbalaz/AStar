package AStar.Solver;

import java.util.List;

public class AStarAlgorithm {
    private final List<Block> blocks;
    private final int startWidth,startHeight;
    private final int finishWidth, finishHeight;
    private final int fieldWidth, fieldHeight;
    private Closed closed = null;
    private Open open = null;
    
    public AStarAlgorithm(int startLocation[], int finishLocation[], int fieldParameters[], List<Block> blocks)
    {
        this.startWidth = startLocation[0];
        this.startHeight = startLocation[1];
        this.finishWidth = finishLocation[0];
        this.finishHeight = finishLocation[1];
        this.fieldWidth = fieldParameters[0];
        this.fieldHeight = fieldParameters[1];
        this.blocks = blocks;
    }
    
    public List<Node> solve()
    {
        boolean finishFlag = false;
        
        closed = new Closed();
        open = new Open();
        Route routeObject = new Route();
        
        Node start = new Node(startWidth, startHeight, 0, calculateH(startHeight, startWidth, finishHeight, finishWidth));      
        open.add(start);
        
        while(finishFlag == false)
        {
            Node current = open.findCheapest();
            if(current == null)
                break;
            
            if(current.getHeight() == finishHeight && current.getWidth() == finishWidth)                
                finishFlag = true;
            closed.add(current);
            open.remove(current);
            
            if(finishFlag)
                break;
            
            Operators operators = new Operators(blocks,fieldWidth,fieldHeight);
            Node newNode;
            
            newNode = operators.operate(1, 1, finishWidth, finishHeight, current);
            if(newNode != null)
                open.add(newNode);
            newNode = operators.operate(1, -1, finishWidth, finishHeight, current);
            if(newNode != null)
                open.add(newNode);
            newNode = operators.operate(-1, 1, finishWidth, finishHeight, current);
            if(newNode != null)
                open.add(newNode);
            newNode = operators.operate(-1, -1, finishWidth, finishHeight, current);
            if(newNode != null)
                open.add(newNode);
            newNode = operators.operate(1, 0, finishWidth, finishHeight, current);
            if(newNode != null)
                open.add(newNode);
            newNode = operators.operate(-1, 0, finishWidth, finishHeight, current);
            if(newNode != null)
                open.add(newNode);
            newNode = operators.operate(0, 1, finishWidth, finishHeight, current);
            if(newNode != null)
                open.add(newNode);
            newNode = operators.operate(0, -1, finishWidth, finishHeight, current);
            if(newNode != null)
                open.add(newNode);
            
            open.removeObsolete();
            open.removeAlreadyUsed(closed.getList());          
        }
        
        if(finishFlag == false)
        {
            return null;
        }
        else
            return routeObject.retrack(closed.getList().get(closed.getList().size() - 1), start);
    }
    
    public Open getOpen()
    {
        return open;
    }
    
    public Closed getClosed()
    {
        return closed;
    }   

    public static int calculateH(int start_height, int start_width, int finish_height, int finish_width)
    {
        return 10*Math.abs(start_height - finish_height) + 10*Math.abs(start_width - finish_width);
    }
}
