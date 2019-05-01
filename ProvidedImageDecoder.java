/*
 * Java 311 Final Project
 * Neural Network MNIST Image Decoder
 */
package ImageParse;
import java.io.*;
public class ProvidedImageDecoder extends Decoder //takes the form of the MNIST decoder
{
	private FileInputStream img;
	private byte row[]= new byte[4];
	private byte col[]= new byte[4];
	private String rw;
	private String cl;
	private int r;
	private int c;
	private int file[];
	public ProvidedImageDecoder(boolean testTortrainF) //test == true, train == false
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
			OurImageEncoder OIE = new OurImageEncoder(file); //encodes image for later use in the OurImageDecoder class
			OIE.encode(testTortrainF);
			img.close();
		}
		catch(IOException ex)
		{
			System.out.println("Error while decoding the MNIST database file, ensure the files : t10k-images.idx3-ubyte and train-images.idx3-ubyte exist and have their intended content");
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
			//converts bytes to strings
			m = bytetoString(Magic[0]) + bytetoString(Magic[1]) + bytetoString(Magic[2]) + bytetoString(Magic[3]);
			nm = bytetoString(numImages[0]) + bytetoString(numImages[1]) + bytetoString(numImages[2]) + bytetoString(numImages[3]);
			rw = bytetoString(row[0]) + bytetoString(row[1]) + bytetoString(row[2]) + bytetoString(row[3]);
			cl = bytetoString(col[0]) + bytetoString(col[1]) + bytetoString(col[2]) + bytetoString(col[3]);
			convertInt();
			return true;
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("Image file not found, find t10k-images.idx3-ubyte and train-images.idx3-ubyte in the MNIST database and place them in the project file");
			return false;
		}
		catch(IOException ex)
		{
			System.out.println("t10k-images.idx3-ubyte and train-images.idx3-ubyte may not contain valid data, please replace them and try agian");
			return false;
		}
	}
	void convertInt() //converts strings to integers
	{
		mag = Integer.parseInt(m, 2);
		numimg = Integer.parseInt(nm, 2);
		r = Integer.parseInt(rw,2);
		c = Integer.parseInt(cl,2);
	}
	
}