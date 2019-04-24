package ImageParse;
import java.io.*;
public class OurImageDecoder
{
	ObjectInputStream img;
	int magic;
	int numimg;
	int row;
	int col;
	double[][] pix;
	int[][] input;
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
						pix[i][rc] = img.readInt();
						pix[i][rc] = pix[i][rc]/255;
				}
			}
		}
		}
		catch(IOException ex)
		{
			System.out.println("Error in OurImageDecoder");
			System.out.println(ex);
		}
	}
	public double[][] getPix()
	{
		return pix;
	}
	boolean getFile(boolean testortrain)
	{
		try 
		{
			if(testortrain)
				img = new ObjectInputStream(new FileInputStream("TestOI.obj"));
			else
				img = new ObjectInputStream(new FileInputStream("TrainOI.obj"));
			magic = img.readInt();
			numimg = img.readInt();
			row = img.readInt();
			col = img.readInt();
			System.out.println("Magic number: " + magic + ", Number of Images: " + numimg + ", Number of rows for each image: " + row + ", Number of collumns for each image: " +col);
			return true;
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("Image file not found");
			return false;
		}
		catch(IOException ex)
		{
			return false;
		}
	}

	
}