/*
 * Java 311 Final Project
 * Neural Network Menu Interface
 */
package MenuFiles;

import java.util.ArrayList;

import com.company.Matrix;
import com.company.NN;

import ImageParse.LabelDecoder;
import ImageParse.OurImageDecoder;
import ImageParse.ProvidedImageDecoder;

public interface Menu 
{
	//user interaction
	public void display(); //outputs menu text
	public void select(String input); //selects one of the options
	//functionality
	public void loadImage(boolean TrainTest); //loads image 2d array from object file
	public void loaddefaultImage(boolean TrainTest); //loads 2d array from MNIST database and creates object file
	public void loadLable(boolean TrainTest); //loads array of integers for labels associated with the images
	public void setupMatrix(); //fills the matrix with image data
	public void ToTNN(); //Train or Test the neural network
	//getters
	public double[][] getImage();
	public int[] getLabel();
	public NN getNN();
}


