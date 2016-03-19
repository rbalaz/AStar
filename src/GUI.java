package AStar;

import AStar.Solver.*;
import AStar.Graphics.ActionHandler;
import java.util.List;
import java.awt.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUI {
    private int fieldWidth;
    private int fieldHeight;
    private JFrame frame;
    private JPanel gui;
    private JPanel board;
    private JPanel cells[][];
    private JToolBar tools,miscTools;
    private final ActionHandler handler;
    
    public GUI()
    {
        this.handler = new ActionHandler(this);
    }
    
    public void setFieldSize(int[] fieldCoords)
    {
        this.fieldWidth = fieldCoords[0];
        this.fieldHeight = fieldCoords[1];        
        this.cells = new JPanel[this.fieldHeight][this.fieldWidth];
    }
    
    public void createBoard()
    {
        board = new JPanel(new GridLayout(this.fieldHeight,this.fieldWidth));
        board.setBorder(new LineBorder(Color.BLACK));
        gui.add(board);
    }
    
    public void addPanels()
    {
        for(int i = 0; i < this.fieldHeight; i++)
        {
            for(int j = 0; j < this.fieldWidth; j++)
            {
                JPanel p = new JPanel() {
                    @Override
                    public Dimension getPreferredSize()
                    {
                        return new Dimension(45,45);
                    }
                };
                p.setBorder(new LineBorder(Color.BLACK));
                p.setBackground(Color.LIGHT_GRAY);
                cells[i][j] = p;
            }
        }
        
        for(int i = 0; i < this.fieldHeight; i++)
            for(int j = 0; j < this.fieldWidth; j++)
            {
                board.add(cells[i][j]);
            }
    }
    
    private void addToolbar()
    {
        gui = new JPanel(new BorderLayout(3,3));
        gui.setBorder(new EmptyBorder(5,5,5,5));
        tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools,BorderLayout.PAGE_START);
        tools.add(createExecuter());
        tools.addSeparator();
        tools.add(createFileReader());
        tools.addSeparator();
        JLabel text = new JLabel("Please load from file:");
        text.setFont(new Font("Arial",Font.ITALIC,14));
        tools.add(text);
        tools.addSeparator();
        tools.add(new JTextField("Tester.txt"));
        miscTools = new JToolBar();
        miscTools.setFloatable(false);
        gui.add(miscTools,BorderLayout.SOUTH);
        miscTools.add(createCloser());
    }
    
    private JButton createExecuter()
    {
        JButton execute = new JButton() {
            @Override
            public Dimension getPreferredSize()
            {
                return new Dimension(90,30);
            }
        };
        execute.setFont(new Font("Arial",Font.ITALIC,14));
        execute.setText("Solve");
        execute.addActionListener(handler);
        return execute;
    }
    
    private JButton createCloser()
    {
        JButton closer = new JButton() {
            @Override
            public Dimension getPreferredSize()
            {
                return new Dimension(90,30);
            }
        };
        closer.setFont(new Font("Arial",Font.ITALIC,14));
        closer.setText("Close");
        closer.addActionListener(handler);
        return closer;
    }
    
    private JButton createFileReader()
    {
        JButton fileReader = new JButton() {
            @Override
            public Dimension getPreferredSize()
            {
                return new Dimension(150,30);
            }
        };
        fileReader.setFont(new Font("Arial",Font.ITALIC,14));
        fileReader.setText("Load from file");
        fileReader.addActionListener(handler);
        return fileReader;
    }
    
    public void paintClosed(List<Node> closed)
    {
        closed.stream().forEach((closed1) -> {
            cells[closed1.getHeight() - 1][closed1.getWidth() - 1].setBackground(Color.ORANGE);
            cells[closed1.getHeight() - 1][closed1.getWidth() - 1].paintImmediately(0,0,frame.getWidth(),frame.getHeight());
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void paintOptimal(List<Node> route)
    {
        route.stream().forEach((route1) -> {
            cells[route1.getHeight() - 1][route1.getWidth() - 1].setBackground(Color.GREEN);
        });
    }
    
    public void paintBlocks(List<Block> blocks)
    {
        blocks.stream().forEach((block) -> {
            cells[block.getWidth() - 1][block.getHeight() - 1].setBackground(Color.BLACK);
        });
        gui.paintImmediately(0,0,frame.getWidth(),frame.getHeight());
    }
    
    public void markStartFinish(int[] startPosition, int[] finishPosition)
    {
        JLabel start = new JLabel("S");
        start.setFont(new Font("Arial",Font.BOLD,24));
        start.setForeground(Color.red);
        JLabel finish = new JLabel("F");
        finish.setFont(new Font("Arial",Font.BOLD,24));
        finish.setForeground(Color.red);
        cells[startPosition[1]-1][startPosition[0]-1].add(start);
        cells[finishPosition[1]-1][finishPosition[0]-1].add(finish);
    }
    
    private void createFrame()
    {
        Runnable r = () -> {
            frame = new JFrame("A* pathfinding solution.");
            frame.add(gui);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationByPlatform(true);
            frame.pack();
            frame.setMinimumSize(frame.getSize());
            frame.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
    
    public void reloadUI()
    {
        if(board != null)
        {
            gui.remove(board);
            gui.revalidate();
        }
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
    
    public JToolBar getToolBar()
    {
        return tools;
    }

    public static void main(String[] agrs)
    {
        GUI gui = new GUI();
        gui.addToolbar();
        gui.createFrame();
    }
}
