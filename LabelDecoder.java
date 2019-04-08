package ImageParse;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LabelDecoder 
{
	FileInputStream lbl;
	byte Magic[] = new byte[4];
	byte numLabel[]= new byte[4];
	String m;
	String nm;
	int mag;
	int numimg;
	int trueval[];
	public LabelDecoder()
	{
		try
		{
		if(getFile())
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
	boolean getFile()
	{
		try 
		{
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
	
	String bytetoString(Byte a)
	{
		StringBuilder b = new StringBuilder();
		int temp_a = a;
		
		for (int i = 0; i < 8; i++) 
		{
			int temp = temp_a  & 1;
			b.append(String.valueOf(temp));
			temp_a = temp_a >> 1;
		}
		b.reverse();
		return b.toString();
	}
}
