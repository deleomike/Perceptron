package ImageParse;
import java.io.*;
public class ImageDecoder 
{
	FileInputStream img;
	byte Magic[] = new byte[4];
	byte numImages[]= new byte[4];
	byte row[]= new byte[4];
	byte col[]= new byte[4];
	String m;
	String nm;
	String rw;
	String cl;
	int mag;
	int numimg;
	int r;
	int c;
	double[][] pix;
	public ImageDecoder()
	{
		try
		{
		if(getFile())
		{
			pix = new double[numimg][r*c];
			for(int i = 0; i<numimg; i++)
			{
				for(int rc = 0; rc<r*c; rc++)
				{
						pix[i][rc] = img.read();
						pix[i][rc] = pix[i][rc]/255;
				}
			}
			
		}
		}
		catch(IOException ex)
		{
			System.out.println("Pixel error");
		}
	}
	public double[][] getPix()
	{
		return pix;
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
	boolean getFile()
	{
		try 
		{
			img = new FileInputStream("train-images.idx3-ubyte");
			img.read(Magic);
			img.read(numImages);
			img.read(row);
			img.read(col);
			m = bytetoString(Magic[0]) + bytetoString(Magic[1]) + bytetoString(Magic[2]) + bytetoString(Magic[3]);
			nm = bytetoString(numImages[0]) + bytetoString(numImages[1]) + bytetoString(numImages[2]) + bytetoString(numImages[3]);
			rw = bytetoString(row[0]) + bytetoString(row[1]) + bytetoString(row[2]) + bytetoString(row[3]);
			cl = bytetoString(col[0]) + bytetoString(col[1]) + bytetoString(col[2]) + bytetoString(col[3]);
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
		r = Integer.parseInt(rw,2);
		c = Integer.parseInt(cl,2);
	}
	
}
