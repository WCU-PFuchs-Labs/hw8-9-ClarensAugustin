
public class Divide extends Binop {

    public double eval(double left, double right) {
        // if divide by zero
        if (right == 0.0 || Math.abs(right) < 1e-10) {
            return 1.0;
        }
        return left / right;
    }

    @Override
    public String toString() {
        return " / ";
    }

}


