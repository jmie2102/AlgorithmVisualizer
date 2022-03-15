package AlgoVis.SortingAlgorithm;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import AlgoVis.Visualizer;

public class QuickSort extends Visualizer
{   
    SwingWorker<Void,Void> sorter;

    //Generate a random array upon initialization
    public QuickSort()
    {
        generateArray();
    }
    
    public int partition(int[] arr,int low,int high)
    {
        int i=low-1;
        int pivot=arr[high];
        
        for(int j=low;j<high;j++)
        {
            if(arr[j]<=pivot)
            {
                i++;
                
                swap(i,j);
                repaint();
                dispArray();
            }
        }
        swap(high,i+1);
        repaint();
        dispArray();
        
        return i+1;
    }
    
    public void quickSort(int arr[],int low,int high)
    {   
        dispArray();

        if(low<high)
        {
            int pivot=partition(arr,low,high);
            quickSort(arr,low,pivot-1);
            quickSort(arr,pivot+1,high);
        }
    }
    
    public void performSort(int[] arr,int low,int high)
    {
        sorter=new SwingWorker<>()
        {
            @Override
            public Void doInBackground() throws InterruptedException
            {
                quickSort(arr, low, high);
                
                return null;
            }
        };
        sorter.execute();
    }

    @Override   
    public void addButtons()
    {
        //Sort button configuration
        JButton sort=new JButton("Sort");
        sort.setBackground(Color.WHITE);
        sort.setFocusPainted(true);
        sort.setBorderPainted(false);
        sort.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                performSort(Visualizer.arr,0,Visualizer.num_bar-1);
            }
        });
        this.add(sort);
        
        //Generate button configuration
        JButton generate=new JButton("Generate");
        generate.setBackground(Color.WHITE);
        generate.setFocusPainted(true);
        generate.setBorderPainted(false);
        generate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                generateArray();
            }
        });
        this.add(generate);
        
        //Shuffle the array
        JButton shuffle_array=new JButton("Shuffle array");
        shuffle_array.setBackground(Color.WHITE);
        shuffle_array.setFocusPainted(true);
        shuffle_array.setBorderPainted(false);
        shuffle_array.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                shuffleArray();
            }
        });
        this.add(shuffle_array);
    }
}