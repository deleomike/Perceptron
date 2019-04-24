package ImageParse;
import java.io.*;
public abstract class Decoder 
{
	FileInputStream img;
	byte Magic[] = new byte[4];
	byte numImages[]= new byte[4];
	String m;
	String nm;
	int mag;
	int numimg;
	String bytetoString(Byte byt)
	{
		StringBuilder build = new StringBuilder();
		int temp_byt = byt;
		
		for (int i = 0; i < 8; i++) 
		{
			int temp = temp_byt  & 1;
			build.append(String.valueOf(temp));
			temp_byt = temp_byt >> 1;
		}
		build.reverse();
		return build.toString();
	}
	abstract boolean getFile(boolean testOrTrain);
	abstract void convertInt();
	
}