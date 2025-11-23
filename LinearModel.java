/*import java.util.ArrayList;

public class LinearModel extends Model {

    private double [] coeff;
    private double changeRate;
    private double intercept;

    public LinearModel(double rate, DataSet training){
        super(training);
        this.changeRate = rate;
        initCoefficient();

    }

    public void initCoefficient() {
        int numbers = trainingData.getNumIndependantVariables();
        coeff = new double[numbers];

        // initialize all coeffidcients to 0
        for (int i = 0; i < coeff.length; i++) {
            coeff[i] = 0.0;
        }
        intercept = 0.0;
    }
    public double predict(double[] x){
        double prediction = intercept;

        // Sum up: coeff[i] * x[i] for all i
        for (int i = 0; i < coeff.length &&  i < x.length ; i++){
            prediction += coeff[i] * x[i];
        }
        return prediction;
    }

    public String toSring(){
        StringBuilder string = new StringBuilder();
        string.append("y = ").append(intercept);

        for(int i = 0; i < coeff.length; i++){
            if (coeff[i] >= 0){
                string.append(" + ");
            } else {
                string.append(" - ");
            } string.append(Math.abs(coeff[i])).append(" * x").append(i);
        }

        return string.toString();
    }

    // Mthod to train model using gradient descent
    public void train(int iterations){
        for(int i = 0; i < iterations; i++){
            updateCoeff();
        }
    }


    private void updateCoeff(){
        double[] pred = predict();

        //get data rows
        ArrayList<DataRow> rows = trainingData.getRows();

        // for each coefficient
        // compute the sum of the error times the variable
        // then multiply by 2 and divide by the number of predictions
        for(int i = 0; i < coeff.length; ++i) {
            double sum = 0;
            for (int j = 0; j < pred.length; ++j) {
                DataRow row = rows.get(j);
                double x_i = row.getIndependentVariables()[i];
                sum += (pred[j] - row.getDependentVariable()) * x_i;

            }
            coeff[i] = coeff[i] - changeRate * sum * 2.0 / pred.length;
        }
        // for the intercept
        double sum = 0;
        for (int j = 0; j < pred.length; ++j) {
            DataRow row = rows.get(j);
            sum += pred[j] - row.getDependentVariable();
        }
        intercept = intercept - changeRate * sum * 2.0 / pred.length;
    }

} */


/**
 * Author:
 * Date:
 * Purpose:
 */

import java.util.ArrayList;

public class LinearModel extends Model{
    private double[] coeff;
    private double intercept;
    private double changeRate;

    /**
     *
     * @param rate - the update/learning rate
     * @param training - the training data
     */
    public LinearModel(double rate, DataSet training) {
        super(training);
        // initialize changeRate based on parameters
        this.changeRate = rate;

        // call initCoefficients to initialize coeff and intercept
        initCoefficients();
        // call updateCoeff() 1000 times
        for(int i = 0; i < 1000; i++){
            updateCoeff();
            // when testing this, you may want to print some
            // debugging output so that you can track the progress
            // of the regression.
            if(i % 50 == 0){
                System.out.println("Iteration " + i + ": Sum Squared Error = " + sumSquarederror());
            }
        }
        System.out.println("Model training complete");
        System.out.println("Final Sum Squared Error: " + sumSquarederror());
    }


    /**
     * helper function to initialize coeff and intercept
     */
    private void initCoefficients() {
        // instantiate an array of size n for coeff, where n is
        // the number of independent variables in the training data
        int n = trainingData.getNumIndependantVariables();
        coeff = new double[n];

        // initialize each value in coeff to be a random double
        // between -2 and 2
        for(int i = 0; i < coeff.length; i++){
            coeff[i] = (Math.random() * 4) -2;
        }

        // initialize intercept to be a random double
        // between -2 and 2
        intercept = (Math.random() * 4) - 2;
    }

    /**
     * This makes a prediction based on the current
     * coefficients and intercept
     * @param x
     * @return
     */
    public double predict(double[] x) {
        // compute the sum of each coefficient times
        // the corresponding x, then add the intercept
        // and return the result.
        double prediction = intercept;

        for(int i = 0; i < coeff.length && i < x.length; i++){
            prediction += coeff[i] * x[i];
        }

        //FIXME:
        return prediction;
    }

    /**
     * helper function that does one iteration of computing
     * the error and updating the coefficients and the intercept
     *
     */
    private void updateCoeff() {
        // get the predictions for each row by calling predict()
        double[] pred = predict();

        // get the data rows
        ArrayList<DataRow> rows = trainingData.getRows();

        // for each coefficient
        // compute the sum of the error times the variable
        // then multiply by 2 and divide by the number of predictions
        for(int i = 0; i < coeff.length; ++i) {
            double sum = 0;
            for(int j = 0; j < pred.length; ++j) {
                DataRow row = rows.get(j);
                double x_i = row.getIndependentVariables()[i];
                sum += (pred[j] - row.getDependentVariable()) * x_i;
            }
            coeff[i] = coeff[i] - changeRate * sum * 2.0 / pred.length;
        }

        // for the intercept
        double sum = 0;
        for(int j = 0; j < pred.length; ++j) {
            DataRow row = rows.get(j);
            sum += pred[j] - row.getDependentVariable();
        }
        intercept = intercept - changeRate * sum * 2.0 / pred.length;


    }


}

