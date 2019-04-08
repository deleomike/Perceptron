package ImageParse;
import java.awt.image.BufferedImage;
//since the RGB values for a greyscale image are the same I will be grabbing just the R value for the 'value' of the square and dividing it by 255 for a max of 1 and a min of 0
class ImageDataManager
{
	//28x28 array
	double pixel[][] = new double[28][28];
	int truenum;
	
	void parseImage(BufferedImage a, int t) //sent image and the actual value associated with that image
	{
		truenum = t;
		for(int r=0;r<28;r++)
		{
			for(int c=0;c<28;c++)
			{
				int rgb = a.getRGB(r, c);
				//cite this
				int red= (rgb >> 16) & 255; // >> is a binary right shift, the value for red is stored 16 bits in inside the TYPE_INT_RGB of Buffered image so we shift 16 bits over to get to the red integer
				pixel[r][c] = red/255; 
			}
		}
	}
	double getPix(int r, int c)
	{
		return pixel[r][c];
	}
}
