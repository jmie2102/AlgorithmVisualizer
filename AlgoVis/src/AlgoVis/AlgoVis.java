package AlgoVis;

import javax.swing.JFrame;

import AlgoVis.SortingAlgorithm.QuickSort;

class AlgoVis
{
    private static final int win_width=1280,win_height=720;
    
    private AlgoVis()
    {
        JFrame frame=new JFrame("Algorithm Visualizer");
        
        frame.setSize(win_width,win_height);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);  
        

        QuickSort quicksort=new QuickSort();
        quicksort.addButtons();
        frame.add(quicksort);
    }
    
    public static void main(String[] args) throws Exception
    {
        new AlgoVis();
    }    
}