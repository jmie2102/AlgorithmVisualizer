package AlgoVis;

import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Visualizer extends JPanel
{
    public static int win_width=1280,win_height=720;

    //Default width
    public static int rect_width=10;
    
    //Number of bars
    public static int num_bar=(win_width/rect_width)/2;
    
    public static int arr[]=new int[num_bar];
    
    SwingWorker<Void,Void> shuffler;
    
    //Generate random numbers and assign them to an array
    public void generateArray()
    {
        System.out.println("Generating an array");
        repaint();
        Random rng=new Random();
        
        for(int i=0;i<num_bar;i++)
        {
            arr[i]=rng.nextInt(500);
        }
    }

    //Swap elements
    public void swap(int i, int j) 
    {
        int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
        
        //Animation speed
        try
        {
            Thread.sleep(15);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        repaint();
	}  
    
    //Function to shuffle the existing array
    public void shuffleArray() 
    {
        System.out.println("Shuffling the array");

		shuffler=new SwingWorker<>() 
        {
			@Override
			public Void doInBackground() throws InterruptedException 
            {
                int mid=num_bar/2;
				for(int i=0,j=mid;i<mid;i++,j++) 
                {
					int random_index = new Random().nextInt(num_bar);
					swap(i,random_index);
					
                    random_index = new Random().nextInt(num_bar);
					swap(j,random_index);
				}
				return null;
			}
		};
		shuffler.execute();
    }

    public void dispArray()
    {   
        System.out.print("[ ");
        for(int i=0;i<num_bar;i++)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.println("]");

    }

    abstract public void addButtons();

    //Draw bars
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d=(Graphics2D) g;
        
        super.paintComponent(g2d);
        
        setBackground(new Color(30,30,30));

        g2d.setColor(Color.WHITE);
        
        for(int i=0;i<num_bar;i++)
        {
            int rect_height=arr[i];
            int rectY=win_height-rect_height-100;
            int rectX=((rect_width-1)*i)+320;
            
            g2d.fillRect((rectX+i),rectY,rect_width,rect_height);
        }
    }
}
