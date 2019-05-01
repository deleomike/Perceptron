/*
 * Java 311 Final Project
 * Neural Network Main
 */
    	package com.company;

    	import java.io.*;
    	import java.util.*;
    	import MenuFiles.*;

    	public class Main {

    	    public static void main(String[] args) {
    	    	boolean menu = true;
				NN first = new NN();
    	    	while(menu)
    	    	{
    	    		Scanner in = new Scanner(System.in);
    	    		String choice="";
    	    		TrainMenu Mrain = new TrainMenu(); 
    	    			do
    	    			{
    	    				Mrain.display();
    	    				choice = in.next();
    	    			}while(!choice.equals("1") && !choice.equals("2")&&!choice.equals("3")&&!choice.equals("-1"));
    	    		Mrain.select(choice);
    	    		if(choice.equals("1") ||choice.equals("2"))
    	    		{
    	    			first= Mrain.getNN();
    	    		}
    	    		if(choice.equals("3"))
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
    	    		TestMenu Mest = new TestMenu();
    	    		do
    	    		{
    	    			Mest.display();
    	    			choice = in.next();
    	    		}while(!choice.equals("1") && !choice.equals("2")&&!choice.equals("3")&&!choice.equals("4")&&!choice.equals("-1"));
    	    		Mest.setNN(first);
    	    		Mest.select(choice);
    	    		if(choice.equals("1") ||choice.equals("2"))
    	    			System.out.println("Neural Network Effectiveness: " +(double)Mest.getCorrect()/Mest.getTotal() *100 + " %");
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