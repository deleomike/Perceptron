package ImageParse;
import java.io.*;
public class ProvidedImageDecoder extends Decoder
{
	FileInputStream img;
	byte row[]= new byte[4];
	byte col[]= new byte[4];
	String rw;
	String cl;
	int r;
	int c;
	int file[];
	public ProvidedImageDecoder(boolean testTortrainF)
	{
		try
		{
			if(getFile(testTortrainF))
			{
				file = new int[numimg*r*c+4];
				file[0] = mag;
				file[1] =numimg;
				file[2] = r;
				file[3] = c;
				for(int i = 4; i<file.length; i++)
				{
					file[i] = img.read();
				}
			}
			OurImageEncoder OIE = new OurImageEncoder(file);
			OIE.encode(testTortrainF);
		}
		catch(IOException ex)
		{
			System.out.println("Pixel error");
		}
	}
	boolean getFile(boolean testortrain)
	{
		try 
		{
			if(testortrain)
				img = new FileInputStream("t10k-images.idx3-ubyte");
			else
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