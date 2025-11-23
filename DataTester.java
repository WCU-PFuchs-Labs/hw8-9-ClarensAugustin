
public class DataTester {
        public static void main(String[] args) {
            double y = 7.0;
            double[] x = { 1.0, 2.0, 3.0, 5.0, 10.0 };
            DataRow dr = new DataRow(x, y);
            boolean matchesSoFar = true;
            if (y != dr.getDependentVariable()) {
                matchesSoFar = false;
                System.out.println("dependent variable doesn't match vaalue passed in:" +
                        y + " != " + dr.getDependentVariable());
            }
            double[] testInd = dr.getIndependentVariables();
            if (testInd != null) {
                if (testInd.length != x.length) {
                    matchesSoFar = false;
                    System.out.println("number of independent variables don't match number passed in:" +
                            x.length + " != " + testInd.length);

                }
                for (int i = 0; i < testInd.length && i < x.length; ++i) {
                    if (x[i] != testInd[i]) {
                        matchesSoFar = false;
                        System.out.println("independent variable doesn't match value passed in:" +
                                "x[" + i + "]:" + x[i] + " != " + testInd[i]
                                + ":testInd[" + i + "]");

                    }
                }
            } else {
                matchesSoFar = false;
                System.out.println("independent variables variable is null");
            }
            if (matchesSoFar) {
                System.out.println("Data Row Test 1: Passes");
            } else {
                System.out.println("Data Row Test 1: Fails, see above for what went wrong");
            }
        }
}

