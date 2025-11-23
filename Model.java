

import java.util.ArrayList;

public abstract class Model {
    protected DataSet trainingData;

    public Model(DataSet training){
        trainingData = training;
    }

    protected abstract double predict(double[] x);

    public double[] predict(){
        ArrayList<DataRow> rows = trainingData.getRows();
        double[] pred;
        if(rows == null){
            pred = new double[0];
        }else {
            pred = new double[rows.size()];
        }
        for (int i = 0; i < pred.length; ++i){
            DataRow currRow = rows.get(i);
            double[] x = currRow.getIndependentVariables();
            pred[i] = predict(x);
        }
        return pred;
    }

    public double sumSquarederror() {
        // get prediction of each row by calling predict()
        double[] pred = predict();

        ArrayList<DataRow> rows = trainingData.getRows();
        double sum = 0;
        for(int j = 0; j < pred.length; ++j){
            DataRow row = rows.get(j);
            double err = pred[j] - row.getDependentVariable();
            sum += err * err;
        }
        return sum;
    }
}

