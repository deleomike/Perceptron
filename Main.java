

    	package com.company;

    	import java.io.*;
    	import java.util.*;
    	import Files_Operations.*;
import ImageParse.LabelDecoder;
import ImageParse.OurImageDecoder;
import ImageParse.ProvidedImageDecoder;

    	public class Main {

    	    public static void main(String[] args) {
    	    	boolean menu = true;
				NN first = new NN();
				ArrayList<Matrix> train; //needs to be used outside of option 1 and 2
				ArrayList<Matrix> test; //used in test menu
	    		int[] labels; //needs to be used outside of option 1 and 2
    	    	while(menu)
    	    	{
    	    		Scanner in = new Scanner(System.in);
    	    		String choice="";
    	    		do
    	    		{
    	    			System.out.println("--Training Menu--\n1)First Run?\n2)Train From File\n3)Load Neural Network (-1 exit)");
    	    			choice = in.next();
    	    		}while(!choice.equals("1") && !choice.equals("2")&&!choice.equals("3")&&!choice.equals("-1"));
    	    		if(choice.equals("1"))
    	    		{
    	    			System.out.println("Reading 60,000 images from MNIST database that have 28x28 data points, please wait..");
    	    			ProvidedImageDecoder img = new ProvidedImageDecoder(false);
    	    			System.out.println("Images decoded");
    	    			OurImageDecoder OID = new OurImageDecoder(false);
    	    			System.out.println("Java Object byte file created for training data, use \"train from file\" next time");
    	    			LabelDecoder lbl = new LabelDecoder(false);
    	    			System.out.println("Labels decoded");
    	    			double[][] images = OID.getPix();
    	    			labels = lbl.getval();
    	    			train = new ArrayList<Matrix>();
    	    	    	for(int i=0;i<images.length;i++)
    	    	    	{
    	    	    		train.add(new Matrix(images[i]));
    	    	    	}
    	    	    	System.out.println("Matrices setup");
    	    	    	for(int i = 0; i<train.size();i++)
    	    		    {
    	    		    	first.Train(train.get(i), labels[i]);
    	    		    }
    	    		}
    	    		else if(choice.equals("2"))
    	    		{
    	    			System.out.println("Using Object input stream from file \"TrainOI.obj\"");
    	    			OurImageDecoder OID = new OurImageDecoder(false);
    	    			System.out.println("Images decoded");
    	    			LabelDecoder lbl = new LabelDecoder(false);
    	    			System.out.println("Labels decoded");
    	    			double[][] images = OID.getPix();
    	    			labels = lbl.getval();
    	    			train = new ArrayList<Matrix>();
    	    	    	for(int i=0;i<images.length;i++)
    	    	    	{
    	    	    		train.add(new Matrix(images[i]));
    	    	    	}
    	    	    	System.out.println("Matrices setup");
    	    	    	for(int i = 0; i<train.size();i++)
    	    		    {
    	    		    	first.Train(train.get(i), labels[i]);
    	    		    }
    	    		}
    	    		else if(choice.equals("3"))
    	    		{
    	    			System.out.println("Loading Neural Network from file \"data\"");
    	    			first = loadItems();
    	    		}
    	    		else if(choice.equals("-1"))
    	    		{
    	    			menu=false;
    	    		}
    	    	}
    	    	menu=true;
    	    	while(menu)
    	    	{
    	    		Scanner in = new Scanner(System.in);
    	    		String choice="";
    	    		do
    	    		{
    	    			System.out.println("--Testing Menu--\n1)First Test?\n2)Test from file?\n3)Save Neural Network\n4)Load Neural Network (-1 exit)");
    	    			choice = in.next();
    	    		}while(!choice.equals("1") && !choice.equals("2")&&!choice.equals("3")&&!choice.equals("-1"));
    	    		if(choice.equals("1"))
    	    		{
    	    			System.out.println("Reading raw image data from file provided on MNIST database...");
    	    			ProvidedImageDecoder img = new ProvidedImageDecoder(true);
    	    			System.out.println("Using Object input stream from file \"TestOI.obj\"");
    	    			OurImageDecoder OID = new OurImageDecoder(true);
    	    			System.out.println("Images decoded");
    	    			LabelDecoder lbl = new LabelDecoder(true);
    	    			System.out.println("Labels decoded");
    	    			double[][] images = OID.getPix();
    	    			labels = lbl.getval();
    	    			test = new ArrayList<Matrix>();
    	    	    	for(int i=0;i<images.length;i++)
    	    	    	{
    	    	    		test.add(new Matrix(images[i]));
    	    	    	}
    	    	    	System.out.println("Matrices setup");
    	    	    	System.out.println("Testing data");
    	    	    	for(int i = 0; i<test.size();i++)
    	    		    {
    	    	    		first.Test(test.get(i),labels[i]);
    	    		    }
    	    		}
    	    		else if(choice.equals("2"))
    	    		{
    	    			System.out.println("Using Object input stream from file \"TestOI.obj\"");
    	    			OurImageDecoder OID = new OurImageDecoder(true);
    	    			System.out.println("Images decoded");
    	    			LabelDecoder lbl = new LabelDecoder(true);
    	    			System.out.println("Labels decoded");
    	    			double[][] images = OID.getPix();
    	    			labels = lbl.getval();
    	    			test = new ArrayList<Matrix>();
    	    	    	for(int i=0;i<images.length;i++)
    	    	    	{
    	    	    		test.add(new Matrix(images[i]));
    	    	    	}
    	    	    	System.out.println("Matrices setup");
    	    	    	System.out.println("Testing data");
    	    	    	for(int i = 0; i<test.size();i++)
    	    		    {
    	    	    		first.Test(test.get(i),labels[i]);
    	    		    }
    	    		}
    	    		else if(choice.equals("3"))
    	    		{
    	    			System.out.println("Saving Neural Network to file \"data\"");
    	    			quitSave(first);
    	    		}
    	    		else if(choice.equals("4"))
    	    		{
    	    			System.out.println("Loading Neural Network from file \"data\"");
    	    			first = loadItems();
    	    		}
    	    		else if(choice.equals("-1"))
    	    		{
    	    			menu=false;
    	    		}
    	    	}
    	        quitSave(first);

    	    }

    	    static void printMatrix(Matrix m){
    	        for (int i = 0; i < m.sizey(); i++){
    	            for (int j = 0; j < m.sizex(); j++){
    	                System.out.printf("%f ",m.getValue(i,j));
    	            }
    	            System.out.println();
    	        }
    	        System.out.println();
    	    }

    	    static NN loadItems(){
    	        //LOAD DATA
    	        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream("data"))) {
    	            while(true) {
    	                try {
    	                    NN it = ((NN) obj.readObject());
    	                    if (it == null){
    	                        break;
    	                    } else{
    	                        return it;
    	                    }

    	                } catch (ClassNotFoundException e) {
    	                    System.out.println("Error reading binary file");
    	                }
    	            }
    	        }catch(FileNotFoundException e){

    	        }catch(IOException e){

    	        }
    	        return null;
    	    }

    	    static void quitSave(NN it){
    	        try(ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("data"))){
    	            obj.writeObject(it);
    	            obj.flush();
    	        }catch(FileNotFoundException e){
    	            System.out.println("File does not exist");
    	        }catch(IOException e){

    	        }
    	    }
    	}