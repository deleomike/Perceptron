package com.company;

import com.company.Matrix;

public class Layer {
    private Matrix nodes;
    private Matrix weights;

    Layer(){
        nodes = null;
        weights = null;
    }

    Layer(Matrix N, Matrix W){
        nodes = N;
        weights = W;
    }

    Layer(Matrix W){
        nodes = null;
        weights = W;
    }

    public Matrix getNodes(){ return nodes;}
    public Matrix getWeights(){ return weights;}

    public void setNodes(Matrix N){ nodes = N;}
    public void setWeights(Matrix W){ weights = W;}

    public Matrix multiply(){ return weights.multiply(nodes); }

}
