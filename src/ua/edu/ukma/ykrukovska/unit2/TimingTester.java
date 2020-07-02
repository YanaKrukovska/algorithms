package ua.edu.ukma.ykrukovska.unit2;
import edu.princeton.cs.algs4.Stopwatch;
public class TimingTester {

    public static void main(String[] args) {

     /*   calculate(500);
        calculate(1000);
        calculate(2000);
        calculate(4000);
        calculate(8000);
        calculate(10000);
        calculate(12000);
        calculate(20000);
        calculate(35000);
        calculate(50000);
        calculate(75000);*/
        calculate(60000);

    }

    private static void calculate(int n){

        Stopwatch stopwatch = new Stopwatch();
        Timing.trial(n, 777280);
        //System.out.println("n = " + n + ", t = " + stopwatch.elapsedTime());
        System.out.println(stopwatch.elapsedTime());

    }
}
