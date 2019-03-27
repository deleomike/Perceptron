package com.company;

import java.util.*;

public class Matrix {
    private ArrayList<ArrayList<Double>> mat;
    private int Rows,Columns; //sizes

    /////////////////////////////////////////
    //Constructors
    Matrix(){
        mat = new ArrayList();
        ArrayList<Double> temp = new ArrayList();
        mat.add(temp);
        Rows = 0;
        Columns = 0;
    }

    Matrix(int n){
        mat = new ArrayList(n);
        ArrayList<Double> temp = new ArrayList(n);
        Double t = new Double(0);
        for(int i = 0; i < n; i++){
            temp.set(i,t);
        }
        for (int i = 0; i < n; i++){
            mat.add(temp);
        }
    }

    Matrix(int n, int m){
        mat = new ArrayList(n);
        ArrayList<Double> temp = new ArrayList(m);
        Double t = new Double(0);
        for(int i = 0; i < m; i++){
            temp.set(i,t);
        }
        for (int i = 0; i < n; i++){
            mat.add(temp);
        }
    }

    Matrix(ArrayList<ArrayList<Double>> x){
        mat = x;
        Rows = x.size();
        Columns = x.get(0).size();

    }
    ///////////////////////////////////

    private double sigmoid(double x){
        return (1/( 1 + Math.pow(Math.E,(-1*x))));
    }

    public void setValue(int row, int col, Double val){ mat.get(row).set(col,val);}

    public Double getValue(int row, int col){ mat.get(row).get(col);}

    //this matrix is on the left, the given matrix is on the right
    public Matrix multiply(Matrix m){
        //TODO: Exception for  invalid matrices


        Matrix res = new Matrix(this.Rows,m.Columns);

        //A is nxm and B is mxp
        //i = 1..n, j = 1..p
        for (int i = 0; i< this.Rows; i++){
            for (int j = 0; j < m.Columns; j++){
                Double weightedSum = new Double(0);
                for (int k = 0; k < this.Columns; k++){
                    //do the summation of the values
                    weightedSum += this.getValue(i,k)*m.getValue(k,j);

                }
                //do the sigmoid operation
                weightedSum = new Double(sigmoid(weightedSum));
                res.setValue(i,j,weightedSum);

            }
        }

        return res;

    }

    public void multipliyScalar(double c){
        for (int i = 0; i < Rows; i++){
            for (int j = 0; j < Columns; j++){
                Double temp = getValue(i,j);
                setValue(i,j,temp*c);
            }
        }
    }

    public int sizex(){
        try{
            return mat.get(0).size();
        } catch(IndexOutOfBoundsException){
            return 0;
        }
    }
    public int sizey(){ return mat.size();}


}
