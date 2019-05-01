/*
 * Java 311 Final Project
 * Neural Network Test Menu
 */
package MenuFiles;
import java.util.ArrayList;

import com.company.Matrix;
import com.company.NN;

import ImageParse.LabelDecoder;
import ImageParse.OurImageDecoder;
import ImageParse.ProvidedImageDecoder;

public class TestMenu implements Menu
{
	private double[][] images;
	private int[] labels;
	private ArrayList<Matrix> test = new ArrayList<Matrix>();
	private NN first= new NN();
	private int correct; //these are needed to asses the effectiveness of the neural network at any given time
	private int total;
	@Override
	public void display() //displays the test menu, with slight changes from the train menu
	{
		System.out.println("--Testing Menu--\n1)First Test?\n2)Test from file?\n3)Save Neural Network\n4)Load Neural Network (-1 exit)");
		
	}

	@Override
	public void select(String input)  //same as train menu, based on input provides those funcitons
	{
		if(input.equals("1")) //First test
		{
			loaddefaultImage(true);
			loadLable(true);
			setupMatrix();
			ToTNN();
		}
		else if(input.equals("2")) //test from file
		{
			loadImage(true);
			loadLable(true);
			setupMatrix();
			ToTNN();
		}
		else if(input.equals("3"))
		{
			//This option saves neural network, however this will be executed in the main where the functionality is in place
			System.out.println("Saving Neural Network...");
		}
		else if(input.equals("4"))
		{
			//This option loads neural network, however this will be executed in the main where the functionality is in place
			System.out.println("Loading Neural Network...");
		}
		else if(input.equals("-1"))
		{
			System.out.println("Exiting Program..."); //the test menu has no menus after it, so it ends the program
		}//no other possible outcomes are sent to the test menu, so an else statement is not necessary
		
	}

	@Override
	public void loadImage(boolean TrainTest)  //loads image from previously created using Object Output stream to speed up the process
	{
		System.out.println("Using Object input stream from file \"TestOI.obj\"");
		OurImageDecoder OID = new OurImageDecoder(TrainTest);
		System.out.println("Images decoded");
		images = OID.getPix();
	}

	@Override
	public void loaddefaultImage(boolean TrainTest) //loads image from MNIST database file, and then creates a new one using Object Output stream to speed up the process in the future
	{
		System.out.println("Reading raw image data from file provided on MNIST database...");
		ProvidedImageDecoder img = new ProvidedImageDecoder(TrainTest);
		System.out.println("Using Object input stream from file \"TestOI.obj\"");
		OurImageDecoder OID = new OurImageDecoder(TrainTest);
		System.out.println("Images decoded");
		images = OID.getPix();
	}

	@Override
	public void loadLable(boolean TrainTest) //loads labels from Mnist database file
	{
		LabelDecoder lbl = new LabelDecoder(TrainTest);
		System.out.println("Labels decoded");
		labels = lbl.getval();
	}

	@Override
	public double[][] getImage() //returns the image 2d array of doubles
	{
		return images;
	}

	@Override
	public int[] getLabel() //returns the number value of the labels for each image for gaging the effectiveness of the NN
	{
		
		return labels;
	}
	public int getTotal() //returns the total value (total number of guesses)
	{
		return total;
	}
	public int getCorrect() //returns the total number correctly guessed
	{
		return correct;
	}

	@Override
	public void setupMatrix() //sets up a matrix of the images for testing
	{
		System.out.println("Please wait 30 seconds for the matrix to be prepared");
		for(int i=0;i<images.length;i++)
    	{
    		test.add(new Matrix(images[i]));
    	}
		System.out.println("Matrix setup");
	}

	@Override
	public void ToTNN() //TrainORTest neural network, in this case tests the neural network
	{
		correct = 0;
		total = 0;
		System.out.println("Testing 10000 untrained images, estimated runtime is 4 minutes");
		for(int i = 0; i<test.size();i++)
	    {
    		if(first.Test(test.get(i),labels[i]))
    		{
    			correct++;
    			total++;
    		}
    		else
    			total++;
	    }
		System.out.println("Test complete");
	}

	@Override
	public NN getNN() //returns the neural network
	{
		return first;
	}
	public void setNN(NN set)
	{
		first=set;
	}
	
}