package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	    /*double [][] mat = {{5,3,2,9},{10,-5,3,-1}};

	    Matrix m = new Matrix(mat);

	    Matrix AT = m.Transpose();

	    Matrix AT_A = AT.multiply(m);

	    printMatrix(m);
	    printMatrix(AT);
	    printMatrix(AT_A);
	    printMatrix(m.PseudoInverse());*/

	    NN first = new NN();

	    Matrix test = new Matrix(782,1);
	    //printMatrix(test);
	    first.Train(test,3);
	    first.Train(test,2);
	    first.printOutput();

	    first.Test(test,0);
	    first.printOutput();

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
}
