package ImageParse;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class OurImageEncoder 
{
	int[] toEncode;
	public OurImageEncoder(int[] file)
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
		if(testortrain)
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
		else
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
