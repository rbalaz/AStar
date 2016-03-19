package AStar.File;

import AStar.Solver.Block;
import java.io.*;
import java.util.*;


public class Initialiser {
    private final String filename;
    private int startWidth;
    private int startHeight;
    private int finishWidth;
    private int finishHeight;
    private int width;
    private int height;
    private final List<Block> blocks;
    
    public Initialiser(String filename)
    {
        this.filename = filename;
        blocks = new ArrayList<>();
    }
    
    public void initializeField() throws IOException,FileNotFoundException
    {
        File f = new File(filename);
        FileReader fr;
        fr = new FileReader(f);
        BufferedReader bfr;
        bfr = new BufferedReader(fr);
        String riadok;
        int poradie = 0;
        while((riadok = bfr.readLine()) != null)
        {
            poradie++;
            if(poradie == 1)
                width = Integer.parseInt(riadok);
            else if(poradie == 2)
                height = Integer.parseInt(riadok);
            else
            {
                for(int i = 0; i < riadok.length();i++)
                {
                    if(riadok.charAt(i) == 'S')
                    {
                        startWidth = i + 1;
                        startHeight = poradie - 2;
                    }
                    if(riadok.charAt(i) == 'C')
                    {
                        finishWidth = i + 1;
                        finishHeight = poradie - 2;
                    }
                    if(riadok.charAt(i) == '#')
                    {
                            blocks.add(new Block(poradie - 2,i + 1));
                    }
                }
            }            
        }
        bfr.close();
        fr.close();   
    }
    
    public int[] getStartCoords()
    {
        int start[] = new int[2];
        start[0] = startWidth;
        start[1] = startHeight;
        
        return start;
    }
    
    public int[] getFinishCoords()
    {
        int finish[] = new int[2];
        finish[0] = finishWidth;
        finish[1] = finishHeight;
        
        return finish;
    }
    
    public int[] getFieldCoords()
    {
        int coords[] = new int[2];
        coords[0] = width;
        coords[1] = height;
        
        return coords;
    }
    
    public List<Block> getBlocks()
    {
        return blocks;
    }
}
