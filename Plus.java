

public class Plus extends Binop {

    public double eval(double left, double right) {
        return left + right;
    }

    @Override
    public String toString() {
        return " + ";
    }

}

