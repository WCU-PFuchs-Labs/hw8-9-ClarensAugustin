
public class Const extends Unop{

    // the constant value
    private double value;

    // @param d the value to set the constant to
    public Const(double d) {

        this.value = d;
    }

    // return value of constant
    @Override
    public double eval(double [] values) {

        return value;
    }

    @Override
    public String toString() {
        //return String.valueOf(value);
        return Double.toString(value);
    }


}

