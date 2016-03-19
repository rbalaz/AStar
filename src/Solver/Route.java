package AStar.Solver;

import java.util.*;


public class Route {
    private final List<Node> route;
    
    public Route()
    {
        this.route = new ArrayList<>();
    }
    
    public List<Node> retrack(Node finish, Node start)
    {
        route.add(finish);
        boolean isOver = false;
               
        while(isOver == false)
        {
            route.add(0,route.get(0).getParent());
            if(route.get(0).compare(start))
                isOver = true;

            if(route.get(0) == null && isOver == false)
                break;
        }
        if(isOver)
            return route;
        else
            return null;
    }
    
    public void printList(List<Node> route)
    {
        route.stream().forEach((node) -> {
            System.out.print("[" + node.getWidth() + "," + node.getHeight() + "]" + "#");
        });
        System.out.println();
    }
    
    public List<Node> getRoute()
    {
        return this.route;
    }
}
