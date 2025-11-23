package src;

public class DataRow {

    private double y;
    private double[] x;

    public DataRow(double[] x, double y){
        this.y = y;
        this.x = x;
    }

    public double getDependentVariable(){
        return y;
    }

    public double[] getIndependentVariables(){
        return x;
    }

    public int numIndependentVariables(){
        return x.length;
    }
}
