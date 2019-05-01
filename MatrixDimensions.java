package com.company;

import java.io.Serializable;

public class MatrixDimensions extends Exception{
    int Matrix1_N,Matrix1_M,Matrix2_N,Matrix2_M;

    MatrixDimensions(){
        Matrix1_N = Matrix2_N = Matrix2_M = Matrix1_M = 0;
    }

    MatrixDimensions(Matrix M, Matrix N){
        Matrix1_N = M.sizey();
        Matrix1_M = M.sizex();

        Matrix2_N = N.sizex();
        Matrix2_M = N.sizey();
    }

}