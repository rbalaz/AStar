package AStar.Solver;

import java.util.List;


public class Open extends NodeList{
    
    public Open()
    {
        super();
    }
    
    public Node findCheapest()
    {
        if(this.getList().isEmpty())
            return null;
        int f = this.getList().get(0).getF();
        for(int i = 1; i < this.getList().size(); i++)
        {
            if(this.getList().get(i).getF() < f)
                f = this.getList().get(i).getF();
        }
        
        for (Node node : this.getList()) {
            if (f == node.getF()) {
                return node;
            }
        }
        
        return null;
    }
    
    public void removeObsolete()
    {
        for(int i = 0; i < this.getList().size(); i++)
            for(int j = i + 1; j < this.getList().size(); j++)
            {
                if(this.getList().get(i).compare(this.getList().get(j)))
                    this.remove(this.getList().get(i).returnWorse(this.getList().get(j)));
            }
    }
    
    public void removeAlreadyUsed(List<Node> closed)
    {
        boolean modification = true;
        while(modification)
        {
            modification = false;
            for (Node node : this.getList()) 
            {
                for (Node closed1 : closed) 
                {
                    if (node.compare(closed1)) 
                    {
                        this.remove(node);
                        modification = true;
                        break;
                    }
                }
                if(modification)
                    break;
            }
        }
    }
}
