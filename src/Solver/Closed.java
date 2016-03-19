
package AStar.Solver;

public class Closed extends NodeList{
    
    public Closed()
    {
        super();
    }
    
    public Node getByParams(int width, int height)
    {
        for (Node node : this.getList()) {
            if (node.getHeight() == height && node.getWidth() == width) 
                return node;
        }
        return null;
    }
}
