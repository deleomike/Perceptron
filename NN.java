package com.company;

import com.company.Layer;

public class NN {
    private Layer InputLayer = null;
    private Layer HiddenLayer1 = null;
    private Layer HiddenLayer2 = null;
    private Matrix OutputLayer = null;

    //Save State constructor
    NN(Layer In, Layer Hid1, Layer Hid2, Matrix Out){
        InputLayer = In;
        HiddenLayer1 = Hid1;
        HiddenLayer2 = Hid2;
        OutputLayer = Out;
    }

    NN(Layer Hid1, Layer Hid2){
        HiddenLayer1 = Hid1;
        HiddenLayer2 = Hid2;
    }

    NN(){
        //TODO
    }


    /**
     *
     */
    public void Reset(){
        //TODO
    }

    /**
     *
     * @param Input
     */
    private void newInput(Matrix Input){
        //TODO Throw exception for improper size

        if (InputLayer != null){
            InputLayer.setNodes(Input);
        }
        else{
            //Has not been created yet
            Matrix W = new Matrix(Input.sizey()*16);
            InputLayer = new Layer(Input,W);
        }
    }

    /**
     *
     */
    private void FeedForward(){
        //Do the operation for the input layer
        Matrix Hidden = InputLayer.multiply();
        //Then set that matrix as the nodes of the first hidden layer
        HiddenLayer1.setNodes(Hidden);
        //Then propogate to the second layer
        Hidden = HiddenLayer1.multiply();

        HiddenLayer2.setNodes(Hidden);
        //now assign to the output
        OutputLayer = HiddenLayer2.multiply();

    }

    /**
     *
     * @param correctValue
     */
    private void backPropagate(int correctValue){
        //TODO
    }

    /**
     *
     * @param value
     * @return
     */
    private boolean IsOutput(int value){
        //look for the highest output. if it is it, then it is correct
        Double max = new Double(0);
        int index = 0;
        for (int i = 0; i < OutputLayer.sizey(); i++){
            if (OutputLayer.getValue(i,0) > max){
                max = OutputLayer.getValue(i,0);
                index = i;
            }
        }


        //If it is the highest value then it is true
        if (index == value){ return true;}
        else { return false;}
    }

    /**
     *
     * @param Input
     * @param correctValue
     */
    public void Train(Matrix Input, int correctValue){
        //Assign the input
        newInput(Input);
        //Propagate the data
        FeedForward();
        //Correct the mistakes
        backPropagate(correctValue);
    }

    /**
     *
     * @param Input
     * @param correctValue
     * @return
     */
    public boolean Test(Matrix Input, int correctValue){
        //Assign the input
        newInput(Input);
        //Propagate the data
        FeedForward();
        //See if it is correct
        return IsOutput(correctValue);
    }
}
