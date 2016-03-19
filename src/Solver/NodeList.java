package AStar.Solver;

import java.util.*;

public abstract class NodeList {
    private final List<Node> nodes;
    
    public NodeList()
    {
        nodes = new ArrayList<>();
    }
    
    public void add(Node node)
    {
        nodes.add(node);           
    }
    
    public void remove(Node node)
    {
        nodes.remove(node);
    }
    
    public List<Node> getList()
    {
        return this.nodes;
    }
    
    public void printList()
    {
        nodes.stream().forEach((node) -> {
            System.out.print("[" + node.getWidth() + "," + node.getHeight() + "," + node.getF() + "]" + "#");
        });
        System.out.println();
    }
}
