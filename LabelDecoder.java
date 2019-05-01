/*
 * Java 311 Final Project
 * Neural Network MNIST Label Decoder
 */
package ImageParse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LabelDecoder extends Decoder //takes the MNIST file and gets an array of integers that are the labels associated with the written digits
{
	private FileInputStream lbl;
	private byte numLabel[]= new byte[4];
	private int trueval[];
	public LabelDecoder(boolean testTortrainF) //loads different files depending on if the boolean is true or false
	{										//test is True, train is False
		try
		{
		if(getFile(testTortrainF))
		{
			trueval = new int[numimg];
			for(int i = 0; i<numimg; i++) //if the number of images does not match the labels an error will occur, this is ideal due to the fact we don't want the program to have mismatched labels and images
			{
						trueval[i] = lbl.read(); //the number of images == the number of labels, we read each byte of the file input stream as an integer after the first few depicting the specifications
			}
			
		}
		}
		catch(IOException ex)
		{
			System.out.println("Error in the label decoder, please verify the files [t10k-labels.idx1-ubyte] and [train-labels.idx1-ubyte] exist and have not been modified");
		}
	}
	public int[] getval() //returns the array of label values
	{
		return trueval;
	}
	boolean getFile(boolean testortrain) //does the initial startup and casting of the file stream. the MNIST file has a magic number and number of labels at the start of the file
	{
		try 
		{
			if(testortrain)
				lbl = new FileInputStream("t10k-labels.idx1-ubyte");
			else
				lbl = new FileInputStream("train-labels.idx1-ubyte");
			lbl.read(Magic);
			lbl.read(numLabel);
			m = bytetoString(Magic[0]) + bytetoString(Magic[1]) + bytetoString(Magic[2]) + bytetoString(Magic[3]);
			nm = bytetoString(numLabel[0]) + bytetoString(numLabel[1]) + bytetoString(numLabel[2]) + bytetoString(numLabel[3]);
			convertInt();
			return true;
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("Image file not found, please verify the files [t10k-labels.idx1-ubyte] and [train-labels.idx1-ubyte] exist and have not been modified");
			return false;
		}
		catch(IOException ex)
		{
			System.out.println("Error in intial syntax, Please verify the files [t10k-labels.idx1-ubyte] and [train-labels.idx1-ubyte] exist and have not been modified");
			return false;
		}
	}
	void convertInt() //simply takes the bytes and makes them integers
	{
		mag = Integer.parseInt(m, 2);
		numimg = Integer.parseInt(nm, 2);
	}
}
