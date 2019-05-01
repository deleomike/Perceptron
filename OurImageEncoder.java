/*
 * Java 311 Final Project
 * Neural Network Image File Encoder
 */
package ImageParse;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class OurImageEncoder //Does the opposite of the decoder and takes the information and stores it in a file for faster use in the future (cuts off at least 5 minutes depending on size)
{
	private int[] toEncode;
	public OurImageEncoder(int[] file) //the int array named file is coming from the ProvidedImageDecoder class that produces the label array fromt he MNIST database, but this is far to slow to run every time
	{
		//deep copy
		toEncode = new int[file.length];
		for(int i = 0; i<file.length;i++)
		{
			toEncode[i] = file[i];
		}
		
	}
	void encode(boolean testortrain) throws FileNotFoundException, IOException
	{
		if(testortrain) //Test is testortrain == true, loads everything into the test object file for quicker use in the future
			try(ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream("TestOI.obj")))
			{
				for(int i = 0;i<toEncode.length;i++)
				{
					OOS.writeInt(toEncode[i]);
				}
			}
			catch(FileNotFoundException fnfe)
			{
				System.out.println("File not found");
			}
		else //train is testortrain == false, and loads data into the train object file
			try(ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream("TrainOI.obj")))
			{
				for(int i = 0;i<toEncode.length;i++)
				{
					OOS.writeInt(toEncode[i]);
				}
			}
			catch(FileNotFoundException fnfe)
			{
				System.out.println("File not found");
			}
	}
	
}
