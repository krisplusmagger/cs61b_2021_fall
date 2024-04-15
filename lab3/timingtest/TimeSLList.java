package timingtest;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;
import org.checkerframework.checker.units.qual.A;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {

        int[] N = new int[] {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        int[] opTimes = new int[] {10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000};
        AList<Integer> Ntime = new AList<Integer>();
        AList<Double> times = new AList<Double>();
        AList<Integer> ops = new AList<Integer>();
        for(int i = 0; i < N.length; i++) {
            int j = 0;
            SLList<Integer> al1 = new SLList<Integer>();
            while(j < N[i]) {
                al1.addLast(j);
                j += 1;
            }
            int index = 0;
            int get_from_ssl;
            ops.addLast(opTimes[i]);
            Ntime.addLast(N[i]);
            Stopwatch sw = new Stopwatch();
            while(index < opTimes[i]) {
                get_from_ssl = al1.getLast();
                index += 1;
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);

        }
        printTimingTable(Ntime, times, ops);
    }

}
