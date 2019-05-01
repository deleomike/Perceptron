/*
 * Java 311 Final Project
 * Neural Network Abstract Decoder
 */
package ImageParse;
import java.io.*;
public abstract class Decoder //abstract class that provides a building block for decoders using the MNIST file
{
	// variables
	FileInputStream img;
	byte Magic[] = new byte[4];
	byte numImages[]= new byte[4];
	String m;
	String nm;
	int mag;
	int numimg;
	//methods
	String bytetoString(Byte byt)
	{
		StringBuilder build = new StringBuilder();
		int tempbyt = byt;
		
		for (int i = 0; i < 8; i++) 
		{
			int temp = tempbyt  & 1;
			build.append(String.valueOf(temp));
			tempbyt = tempbyt >> 1;
		}
		build.reverse();
		return build.toString();
	}
	abstract boolean getFile(boolean testOrTrain);
	abstract void convertInt();
	
}