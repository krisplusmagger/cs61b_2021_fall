package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> Ns = new AList<Integer>();
        AList<Double> times = new AList<Double>();
        AList<Integer> ops = new AList<Integer>();

        int[] N = new int[] {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        int i = 0;
        while(i < N.length) {
            Ns.addLast(N[i]);
            Stopwatch sw = new Stopwatch();
            AList<Integer> all1 = new AList<Integer>();
            for (int j = 0; j <= N[i]; j++) {
                all1.addLast(i);
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
            i += 1;
        }
        printTimingTable(Ns, times, Ns);


    }
}
