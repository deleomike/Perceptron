/*
 * Java 311 Final Project
 * Neural Network Image File Decoder
 */
package ImageParse;
import java.io.*;
public class OurImageDecoder //does not inherit from the MNIST Decoder due to having alternative functionality, works with but not as the same thing
{
	private ObjectInputStream img;
	private int magic;
	private int numimg;
	private int row;
	private int col;
	private double[][] pix;
	private int[][] input;
	public OurImageDecoder(boolean testTortrainF) //takes boolean to denote if it is decoding the training or the testing data
	{
		try
		{
			if(getFile(testTortrainF))
			{
				pix = new double[numimg][row*col];
				for(int i = 0; i<numimg; i++)
				{
					for(int rc = 0; rc<row*col; rc++)
					{
						pix[i][rc] = img.readInt(); //all elements are stored as integers
						pix[i][rc] = pix[i][rc]/255; //these integers range from 0 to 255 and can be stored as a value from 0 to 1 for the Neural Network calculations
					}
				}
			}
			img.close();
		}
		catch(IOException ex)
		{
			System.out.println("Error in OurImageDecoder");
			System.out.println(ex);
		}
	}
	public double[][] getPix() //returns 2d array of pixel values
	{
		return pix;
	}
	boolean getFile(boolean testortrain) //takes input from an object file, that stores 4 integers and then the row*col*numImages integer values
	{
		try 
		{
			if(testortrain)
				img = new ObjectInputStream(new FileInputStream("TestOI.obj")); //28*28*10000
			else
				img = new ObjectInputStream(new FileInputStream("TrainOI.obj")); //28*28*60000
			//4 integer values at the start
			magic = img.readInt();
			numimg = img.readInt();
			row = img.readInt();
			col = img.readInt();
			System.out.println("Magic number: " + magic + ", Number of Images: " + numimg + ", Number of rows for each image: " + row + ", Number of collumns for each image: " +col);
			return true;
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("Image file not found, verify you ran the (First time) menu item for each process (testing and training) beforehand, or verify your [TestOI.obj] and [TrainOI.obj] files exist and have not been edited");
			return false;
		}
		catch(IOException ex)
		{
			System.out.println("Verify your [TestOI.obj] and [TrainOI.obj] files exist and have not been edited, please rerun the first time? options for both menus");
			return false;
		}
	}

	
}