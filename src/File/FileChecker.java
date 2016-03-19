package AStar.File;

import java.io.*;

public class FileChecker {
    File f;
    FileReader fr;
    BufferedReader bfr;
    
    public FileChecker(File f) throws FileNotFoundException
    {
        this.f = f;
        fr = new FileReader(f);
        bfr = new BufferedReader(fr);
    }
    
    public void checkFile() throws IOException, InvalidFileException
    {
       int width = loadWidth();
       int height = loadHeight();
       if(checkMapField(width,height) == false)
           throw new InvalidFileException();
    }
    
    private int loadWidth() throws IOException, InvalidFileException
    {
        try
        {
            return Integer.parseInt(bfr.readLine());
        }
        catch(NumberFormatException nfe)
        {
            throw new InvalidFileException(nfe);
        }
    }
    
    private int loadHeight() throws IOException
    {
        return Integer.parseInt(bfr.readLine());
    }
    
    private boolean checkMapField(int width, int height) throws IOException, InvalidFileException
    {
        String line;
        int lines = 0;
        boolean startFound = false;
        boolean finishFound = false;
        while((line = bfr.readLine()) != null)
        {
            lines++;
            if(line.length() != width)
                return false;  
            if(checkStart(line) && startFound)
                return false;
            if(checkStart(line) && startFound == false)
                startFound = true;
            if(checkFinish(line) && finishFound)
                return false;
            if(checkFinish(line) && finishFound == false)
                finishFound = true;
            if(checkIllegalSymbols(line))
                return false;
        }
        bfr.close();
        fr.close();
        return startFound && finishFound && lines == height;
    }
    
    private boolean checkStart(String line) throws InvalidFileException
    {
        int startOccurences = 0;
        for(int i = 0; i < line.length(); i++)
        {
            if(line.charAt(i) == 'S')
                startOccurences++;
        }
        switch(startOccurences)
        {
            case 0:
                return false;
            case 1: 
                return true;
            default:
                throw new InvalidFileException();
        }
    }
    
    private boolean checkFinish(String line) throws InvalidFileException
    {
        int finishOccurences = 0;
        for(int i = 0; i < line.length(); i++)
        {
            if(line.charAt(i) == 'C')
                finishOccurences++;
        }
        switch(finishOccurences)
        {
            case 0:
                return false;
            case 1: 
                return true;
            default:
                throw new InvalidFileException();
        }
    }
    
    private boolean checkIllegalSymbols(String line)
    {
        for(int i = 0; i < line.length(); i++)
        {
            if(line.charAt(i) != '-' && line.charAt(i) != '#' && line.charAt(i) != 'S' && line.charAt(i) != 'C')
                return true;
        }
        
        return false;
    }
}
