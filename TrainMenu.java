/*
 * Java 311 Final Project
 * Neural Network Train Menu
 */
package MenuFiles;

import java.util.ArrayList;

import com.company.Matrix;
import com.company.NN;

import ImageParse.LabelDecoder;
import ImageParse.OurImageDecoder;
import ImageParse.ProvidedImageDecoder;

public class TrainMenu implements Menu
{
	private double[][] images;
	private int[] labels;
	private ArrayList<Matrix> train = new ArrayList<Matrix>();
	private NN first = new NN();
	@Override
	public void display() //outputs menu text for user to use the menu effectively
	{
		System.out.println("--Training Menu--\n1)First Run?\n2)Train From File\n3)Load Neural Network (-1 exit/next)");
	}

	@Override
	public void select(String input) //does alternative options based on user input
	{
		
		if(input.equals("1")) //first run of the program
		{
			loaddefaultImage(false);
			loadLable(false);
			setupMatrix();
			ToTNN();
		}
		else if(input.equals("2")) //train from file already created by first run
		{
			loadImage(false);
			loadLable(false);
			setupMatrix();
			ToTNN();
		}
		else if(input.equals("3"))
		{
			//This option loads neural network, however has no functionality in this class due to no access to Neural Network
			System.out.println("Loading Neural Network...");
		}
		else
		{
			System.out.println("Going to Test menu");
		}
		
	}
	@Override
	public void loadImage(boolean TrainTest)  //loads array of doubles by using images stored as numbers
	{
		System.out.println("Using Object input stream from file \"TrainOI.obj\"");
		OurImageDecoder OID = new OurImageDecoder(TrainTest);
		System.out.println("Images decoded");
		images = OID.getPix();
		
	}
	@Override
	public void loaddefaultImage(boolean TrainTest) //loads array of doubles using the MNIST database file
	{
		System.out.println("Reading 60,000 images from MNIST database that have 28x28 data points, please wait..");
		ProvidedImageDecoder img = new ProvidedImageDecoder(TrainTest);
		OurImageDecoder OID = new OurImageDecoder(TrainTest);
		System.out.println("Java Object byte file created for training data, use \"train from file\" next time");
		images = OID.getPix();
		
	}

	@Override
	public void loadLable(boolean TrainTest) //loads labels from MNIST database
	{
		LabelDecoder lbl = new LabelDecoder(TrainTest);
		System.out.println("Labels decoded");
		labels = lbl.getval();
		
	}

	@Override
	public double[][] getImage() //returns 2d array of images numbers
	{
		return images;
	}
	public double[] getImage(int num) //returns an image as a line of doubles 
	{
		return images[num];
	}

	@Override
	public int[] getLabel() //returns the label list that is parallel to the image list
	{
		return labels;
		
	}

	@Override
	public void setupMatrix() //fills the matrix with the image numbers
	{
		System.out.println("Allow for a minute of matrix allocation");
		for(int i=0;i< images.length;i++)
		{
			train.add(new Matrix(images[i]));
		}
		System.out.println("Matrix setup");
	}

	@Override
	public void ToTNN() //trains the nerual network using the filled matrix
	{
		System.out.println("10 minute training period for "+ 28*28*60000 + " variables"); //28*28 images and 60k images
		for(int i = 0; i<train.size();i++)
		{
			first.Train(train.get(i), labels[i]);
		}
		System.out.println("Neural Network trained");
	}

	@Override
	public NN getNN() //returns the neural network for use outside of the menu
	{
		return first;
	}
	

}