
package AStar.Graphics;

import AStar.Solver.*;
import AStar.GUI;
import AStar.File.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import javax.swing.JTextField;

public class ActionHandler implements ActionListener{
    private boolean fileIsLoaded;
    private final GUI gui;
    private Initialiser init;
    
    public ActionHandler(GUI gui)
    {
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) 
        {
            case "Solve":
                callSolvingAlgorithm();
                break;
            case "Load from file":
                try 
                {
                    loadFromFile();
                } 
                catch (IOException ex) 
                {
                    ErrorMessage error = new ErrorMessage("Fatal error","File " + getFileName() + " was not found!",gui.getFrame());
                    error.displayMessage();
                }
                catch(InvalidFileException ife)
                {
                    ErrorMessage error = new ErrorMessage("Fatal error","File " + getFileName() + " has unsupported format!",gui.getFrame());
                    error.displayMessage();
                    ife.printStackTrace();
                }
                break;
            case "Close":
                System.exit(0);
                break;
            case "QuickSolve":
                callQuickSolvingAlgorithm();
                break;
        }
    }
    
    private void callSolvingAlgorithm()
    {
        if(fileIsLoaded)
        {
            gui.resetCells();
            AStarAlgorithm solver = new AStarAlgorithm(init.getStartCoords(),init.getFinishCoords(),init.getFieldCoords(),init.getBlocks());
            List<Node> route = solver.solve();
            if(route == null)
            {
                gui.paintClosed(solver.getClosed().getList());
                ErrorMessage error = new ErrorMessage("Solving error","Given problem can't be solved. Please try a different field.",gui.getFrame());
                error.displayMessage();
            }
            else
            {
                gui.paintClosed(solver.getClosed().getList());
                gui.paintOptimal(route);
            }
        }
        else
        {
            WarningMessage warning = new WarningMessage("File warning","File might not have been properly loaded. Try again.",gui.getFrame());
            warning.displayMessage();
        }
    }
    
    private void callQuickSolvingAlgorithm()
    {
        if(fileIsLoaded)
        {
            gui.resetCells();
            AStarAlgorithm solver = new AStarAlgorithm(init.getStartCoords(),init.getFinishCoords(),init.getFieldCoords(),init.getBlocks());
            List<Node> route = solver.solve();
            if(route == null)
            {
                gui.paintClosed(solver.getClosed().getList());
                ErrorMessage error = new ErrorMessage("Solving error","Given problem can't be solved. Please try a different field.",gui.getFrame());
                error.displayMessage();
            }
            else
                gui.paintOptimal(route);
        }
        else
        {
            WarningMessage warning = new WarningMessage("File warning","File might not have been properly loaded. Try again.",gui.getFrame());
            warning.displayMessage();
        }
    }
    
    private void loadFromFile() throws IOException, InvalidFileException
    {
        JTextField field = null;
        for(int i = 0; i < gui.getToolBar().getComponentCount(); i++)
        {
            if(gui.getToolBar().getComponent(i) instanceof JTextField)
            {
                field = (JTextField)gui.getToolBar().getComponent(i);
            }
        }
        if(field != null)
        {
            FileChecker fch = new FileChecker(new File(field.getText()));
            fch.checkFile();
            gui.reloadUI();
            init = new Initialiser(field.getText());
            init.initializeField();
            gui.setFieldSize(init.getFieldCoords());
            gui.createBoard();
            gui.addPanels();
            gui.getFrame().pack();
            gui.paintBlocks(init.getBlocks());
            gui.markStartFinish(init.getStartCoords(),init.getFinishCoords());
            fileIsLoaded = true;
        }
    }
    
    private String getFileName()
    {
        JTextField field = null;
        for(int i = 0; i < gui.getToolBar().getComponentCount(); i++)
        {
            if(gui.getToolBar().getComponent(i) instanceof JTextField)
            {
                field = (JTextField)gui.getToolBar().getComponent(i);
            }
        }
        if(field != null)
            return field.getText();
        else
            return null;
    }
}
