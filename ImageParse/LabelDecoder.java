package ImageParse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LabelDecoder extends Decoder
{
	FileInputStream lbl;
	byte numLabel[]= new byte[4];
	int trueval[];
	public LabelDecoder(boolean testTortrainF)
	{
		try
		{
		if(getFile(testTortrainF))
		{
			trueval = new int[numimg];
			for(int i = 0; i<numimg; i++)
			{
						trueval[i] = lbl.read();
			}
			
		}
		}
		catch(IOException ex)
		{
			System.out.println("Pixel error");
		}
	}
	public int[] getval()
	{
		return trueval;
	}
	boolean getFile(boolean testortrain)
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
			System.out.println("Image file not found");
			return false;
		}
		catch(IOException ex)
		{
			return false;
		}
	}
	void convertInt()
	{
		mag = Integer.parseInt(m, 2);
		numimg = Integer.parseInt(nm, 2);
	}
}
